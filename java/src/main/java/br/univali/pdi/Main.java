package br.univali.pdi;

/** Ponto de entrada: coordena a infraestrutura sem conter algoritmos de PDI. */
public final class Main {
    private Main() {}

    public static void main(String[] args) {
        int code = execute(args);
        if (code != ExitCode.SUCCESS) {
            System.exit(code);
        }
    }

    static int execute(String[] args) {
        try {
            CliOptions options = CliParser.parse(args);

            if (options.help) {
                CliParser.printHelp();
                return ExitCode.SUCCESS;
            }
            if (options.version) {
                CliParser.printVersion();
                return ExitCode.SUCCESS;
            }

            Contract.Result validation = Contract.validate(options);
            if (!validation.ok()) {
                System.err.println("Erro: " + validation.message());
                return validation.code();
            }

            return Operations.run(options);
        } catch (PdiException error) {
            System.err.println("Erro: " + error.getMessage());
            return error.code();
        } catch (IllegalArgumentException error) {
            System.err.println("Erro de argumentos: " + error.getMessage());
            return ExitCode.INVALID_ARGUMENTS;
        } catch (Exception error) {
            System.err.println("Erro: " + error.getMessage());
            return ExitCode.GENERAL_ERROR;
        }
    }
}
