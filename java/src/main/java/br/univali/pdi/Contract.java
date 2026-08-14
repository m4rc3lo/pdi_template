package br.univali.pdi;

import java.util.Set;

/** Validação comum da interface externa das três linguagens. */
public final class Contract {
    public record Result(int code, String message) {
        public boolean ok() { return code == ExitCode.SUCCESS; }
    }

    private static final Set<String> SPATIAL_WITH_BORDER = Set.of(
        "weighted_mean", "laplacian", "sobel"
    );

    private Contract() {}

    public static Result validate(CliOptions options) {
        if (options.operation == null || options.operation.isBlank()) {
            return new Result(ExitCode.INVALID_ARGUMENTS, "Informe --operation.");
        }
        if (!Operations.isKnown(options.operation)) {
            return new Result(
                ExitCode.UNKNOWN_OPERATION,
                "Operacao desconhecida: " + options.operation
            );
        }
        if (options.input == null || options.input.isBlank()) {
            return new Result(ExitCode.INVALID_ARGUMENTS, "Informe --input.");
        }
        if (!"inspect".equals(options.operation) &&
            (options.output == null || options.output.isBlank())) {
            return new Result(ExitCode.INVALID_ARGUMENTS, "Informe --output.");
        }

        try {
            return validateParameters(options);
        } catch (IllegalArgumentException error) {
            return new Result(ExitCode.INVALID_PARAMETER, error.getMessage());
        }
    }

    private static Result require(CliOptions options, String name) {
        if (!Parameters.has(options, name)) {
            return new Result(
                ExitCode.INVALID_ARGUMENTS,
                "A operacao '" + options.operation + "' exige --" + name + "."
            );
        }
        return new Result(ExitCode.SUCCESS, "");
    }

    private static Result validateParameters(CliOptions options) {
        switch (options.operation) {
            case "quantize" -> {
                Result required = require(options, "levels");
                if (!required.ok()) return required;
                int levels = Parameters.asInt(options, "levels");
                if (levels != 2 && levels != 4 && levels != 8 && levels != 16) {
                    return new Result(ExitCode.INVALID_PARAMETER, "--levels deve ser 2, 4, 8 ou 16.");
                }
            }
            case "brightness" -> {
                Result required = require(options, "value");
                if (!required.ok()) return required;
                Parameters.asInt(options, "value");
            }
            case "contrast" -> {
                Result required = require(options, "alpha");
                if (!required.ok()) return required;
                if (Parameters.asDouble(options, "alpha") <= 0.0) {
                    return new Result(ExitCode.INVALID_PARAMETER, "--alpha deve ser maior que zero.");
                }
            }
            case "threshold" -> {
                Result required = require(options, "threshold");
                if (!required.ok()) return required;
                int threshold = Parameters.asInt(options, "threshold");
                if (threshold < 0 || threshold > 255) {
                    return new Result(ExitCode.INVALID_PARAMETER, "--threshold deve estar entre 0 e 255.");
                }
            }
            case "convolution" -> {
                Result required = require(options, "kernel");
                if (!required.ok()) return required;
                required = require(options, "border");
                if (!required.ok()) return required;
                Parameters.asPath(options, "kernel");
                Parameters.asBorder(options);
            }
            case "mean_filter" -> {
                Result required = require(options, "size");
                if (!required.ok()) return required;
                required = require(options, "border");
                if (!required.ok()) return required;
                int size = Parameters.asInt(options, "size");
                if (size != 3 && size != 5) {
                    return new Result(ExitCode.INVALID_PARAMETER, "--size deve ser 3 ou 5 para mean_filter.");
                }
                Parameters.asBorder(options);
            }
            default -> {
                if (SPATIAL_WITH_BORDER.contains(options.operation)) {
                    Result required = require(options, "border");
                    if (!required.ok()) return required;
                    Parameters.asBorder(options);
                }
            }
        }
        return new Result(ExitCode.SUCCESS, "");
    }
}
