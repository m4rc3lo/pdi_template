package br.univali.pdi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Leitura de kernel fornecida como infraestrutura do projeto-base. */
public final class KernelIO {
    private KernelIO() {}

    private static PdiException invalidKernel(String message) {
        return new PdiException(ExitCode.INVALID_PARAMETER, message);
    }

    public static Kernel read(Path path) {
        final List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException error) {
            throw new PdiException(
                ExitCode.READ_ERROR,
                "Nao foi possivel abrir o kernel: " + path,
                error
            );
        }

        List<double[]> rows = new ArrayList<>();
        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] tokens = line.split("\\s+");
            double[] row = new double[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                try {
                    row[i] = Double.parseDouble(tokens[i]);
                } catch (NumberFormatException error) {
                    throw invalidKernel("Coeficiente invalido no kernel: " + tokens[i]);
                }
            }
            rows.add(row);
        }

        if (rows.isEmpty()) {
            throw invalidKernel("O arquivo de kernel esta vazio.");
        }

        int size = rows.get(0).length;
        if (size == 0 || rows.size() != size || size % 2 == 0) {
            throw invalidKernel("O kernel deve ser quadrado e possuir dimensao impar.");
        }

        double[] values = new double[size * size];
        int index = 0;
        for (double[] row : rows) {
            if (row.length != size) {
                throw invalidKernel("Todas as linhas do kernel devem ter o mesmo tamanho.");
            }
            for (double value : row) {
                values[index++] = value;
            }
        }

        return new Kernel(size, values);
    }
}
