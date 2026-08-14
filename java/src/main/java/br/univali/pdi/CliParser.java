package br.univali.pdi;

/** Leitura simples e uniforme da linha de comando. */
public final class CliParser {
    private CliParser() {}

    public static CliOptions parse(String[] args) {
        CliOptions options = new CliOptions();

        for (int i = 0; i < args.length; i++) {
            String argument = args[i];

            if ("--help".equals(argument) || "-h".equals(argument)) {
                options.help = true;
                continue;
            }
            if ("--version".equals(argument)) {
                options.version = true;
                continue;
            }
            if (!argument.startsWith("--")) {
                throw new IllegalArgumentException("Argumento inesperado: " + argument);
            }
            if (i + 1 >= args.length) {
                throw new IllegalArgumentException("Falta valor para: " + argument);
            }

            String value = args[++i];
            String name = argument.substring(2);

            switch (name) {
                case "operation" -> options.operation = value;
                case "input" -> options.input = value;
                case "output" -> options.output = value;
                default -> options.parameters.put(name, value);
            }
        }

        return options;
    }

    public static void printHelp() {
        System.out.println("""
            pdi_lab - projeto-base dos laboratorios M1

            Uso geral:
              java -jar target/pdi-lab.jar --operation <operacao> --input <arquivo> --output <arquivo> [parametros]
              java -jar target/pdi-lab.jar --help
              java -jar target/pdi-lab.jar --version

            Parametros comuns:
              --value <inteiro>          brilho
              --levels <2|4|8|16>        quantizacao
              --threshold <0..255>       limiarizacao
              --alpha <real>             contraste
              --kernel <arquivo>         convolucao generica
              --border <copy|replicate>  estrategia de borda
              --size <3|5>               tamanho do filtro de media

            Operacoes M1.1:
              inspect, copy, channel_b, channel_g, channel_r,
              grayscale_average, grayscale_weighted, quantize

            Operacoes M1.2:
              brightness, contrast, negative, threshold, histogram

            Operacoes M1.3:
              convolution, mean_filter, weighted_mean, laplacian, sobel

            Exemplo:
              java -jar target/pdi-lab.jar --operation quantize --input in.png --output out.png --levels 8
            """);
    }

    public static void printVersion() {
        System.out.println("pdi_lab 0.2.0 contract=2");
    }
}
