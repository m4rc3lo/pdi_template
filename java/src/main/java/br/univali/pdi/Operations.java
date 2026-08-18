package br.univali.pdi;

import java.util.Set;

/**
 * Registro e ponto inicial das operações avaliadas.
 *
 * <p>A classe permanece deliberadamente sem os algoritmos. Ela mostra onde
 * começa a responsabilidade do estudante depois que a infraestrutura já
 * validou a linha de comando, os arquivos de entrada e os parâmetros.</p>
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
        // A partir deste método começa a parte avaliada do laboratório.
        //
        // TODO(aluno) M1.1: implementar inspect, copy, channel_b, channel_g,
        // channel_r, grayscale_average, grayscale_weighted e quantize.
        //
        // TODO(aluno) M1.2: implementar brightness, contrast, negative,
        // threshold e histogram. A infraestrutura apenas grava o CSV; a
        // construção do histograma de 256 posições deve ser implementada.
        //
        // TODO(aluno) M1.3: implementar convolution, mean_filter,
        // weighted_mean, laplacian e sobel. KernelIO apenas lê e valida o
        // kernel; aplicar a convolução continua sendo parte do laboratório.
        //
        // Crie métodos/classes auxiliares para os algoritmos e mantenha aqui
        // somente o despacho por operação. Main.java deve continuar dedicado
        // à coordenação da aplicação. Não substitua os percursos manuais
        // exigidos no roteiro por chamadas prontas equivalentes do OpenCV.

        System.err.printf(
            "Operacao '%s' reconhecida, mas ainda nao implementada no projeto-base.%n",
            options.operation
        );
        return ExitCode.GENERAL_ERROR;
    }
}
