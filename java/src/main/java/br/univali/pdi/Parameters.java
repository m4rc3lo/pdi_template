package br.univali.pdi;

import java.nio.file.Path;

/**
 * Conversões de parâmetros da linha de comando para tipos úteis ao algoritmo.
 *
 * <p>O projeto-base faz esta conversão para que o estudante não precise repetir
 * parsing de strings em cada operação de Processamento de Imagens.</p>
 */
public final class Parameters {
    public enum BorderStrategy { COPY, REPLICATE }

    private Parameters() {}

    public static boolean has(CliOptions options, String name) {
        String value = options.parameters.get(name);
        return value != null && !value.isBlank();
    }

    private static String require(CliOptions options, String name) {
        String value = options.parameters.get(name);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Parametro ausente: --" + name);
        }
        return value;
    }

    public static int asInt(CliOptions options, String name) {
        String text = require(options, name);
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("Valor invalido para --" + name + ": " + text, error);
        }
    }

    public static double asDouble(CliOptions options, String name) {
        String text = require(options, name);
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("Valor invalido para --" + name + ": " + text, error);
        }
    }

    public static Path asPath(CliOptions options, String name) {
        return Path.of(require(options, name));
    }

    public static BorderStrategy asBorder(CliOptions options) {
        String text = require(options, "border");
        return switch (text) {
            case "copy" -> BorderStrategy.COPY;
            case "replicate" -> BorderStrategy.REPLICATE;
            default -> throw new IllegalArgumentException(
                "Valor invalido para --border: " + text + ". Use copy ou replicate."
            );
        };
    }
}
