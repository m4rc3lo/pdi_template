package br.univali.pdi;

import java.util.Set;

/**
 * Registro e ponto inicial das operações avaliadas.
 *
 * <p>A classe permanece deliberadamente sem os algoritmos. Ela mostra onde
 * começa a responsabilidade do estudante depois que a infraestrutura já
 * validou a linha de comando.</p>
 */
public final class Operations {
    private static final Set<String> KNOWN = Set.of(
        // M1.1 — representação, canais e níveis de cinza
        "inspect",
        "copy",
        "channel_b",
        "channel_g",
        "channel_r",
        "grayscale_average",
        "grayscale_weighted",
        "quantize",

        // M1.2 — transformações de intensidade
        "brightness",
        "contrast",
        "negative",
        "threshold",
        "histogram",

        // M1.3 — convolução e filtragem espacial
        "convolution",
        "mean_filter",
        "weighted_mean",
        "laplacian",
        "sobel"
    );

    private Operations() {}

    public static boolean isKnown(String operation) {
        return KNOWN.contains(operation);
    }

    public static int run(CliOptions options) {
        // Stub intencional: substitua o despacho por funções/classes que
        // implementem as operações solicitadas. Não coloque os algoritmos em
        // Main.java, que deve continuar apenas coordenando a aplicação.
        System.err.printf(
            "Operacao '%s' reconhecida, mas ainda nao implementada no projeto-base.%n",
            options.operation
        );
        return ExitCode.GENERAL_ERROR;
    }
}
