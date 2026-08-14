package br.univali.pdi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Serialização de resultados fornecida pelo projeto-base.
 *
 * <p>O estudante continua responsável por calcular os dados. Esta classe
 * apenas grava valores já calculados em CSV ou JSON.</p>
 */
public final class ResultIO {
    private ResultIO() {}

    public static void ensureParentDirectory(Path path) {
        Path parent = path.getParent();
        if (parent == null) {
            return;
        }

        try {
            Files.createDirectories(parent);
        } catch (IOException error) {
            throw new PdiException(
                ExitCode.WRITE_ERROR,
                "Nao foi possivel criar o diretorio de saida: " + parent,
                error
            );
        }
    }

    public static void writeHistogramCsv(Path path, long[] histogram) {
        if (histogram == null || histogram.length != 256) {
            throw new IllegalArgumentException("O histograma deve possuir 256 posicoes.");
        }

        ensureParentDirectory(path);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("intensity,count");
            writer.newLine();
            for (int i = 0; i < histogram.length; i++) {
                writer.write(i + "," + histogram[i]);
                writer.newLine();
            }
        } catch (IOException error) {
            throw new PdiException(
                ExitCode.WRITE_ERROR,
                "Nao foi possivel criar o CSV: " + path,
                error
            );
        }
    }

    public static void writeJsonObject(Path path, Map<String, String> values) {
        ensureParentDirectory(path);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("{");
            writer.newLine();
            int index = 0;
            for (Map.Entry<String, String> entry : values.entrySet()) {
                writer.write("  \"" + escape(entry.getKey()) + "\": \"" + escape(entry.getValue()) + "\"");
                if (++index < values.size()) {
                    writer.write(",");
                }
                writer.newLine();
            }
            writer.write("}");
            writer.newLine();
        } catch (IOException error) {
            throw new PdiException(
                ExitCode.WRITE_ERROR,
                "Nao foi possivel criar o JSON: " + path,
                error
            );
        }
    }

    private static String escape(String text) {
        return text
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
    }
}
