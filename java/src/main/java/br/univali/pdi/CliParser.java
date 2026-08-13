package br.univali.pdi;
public final class CliParser {
    private CliParser() {}
    public static CliOptions parse(String[] args) {
        CliOptions o = new CliOptions();
        for (int i=0;i<args.length;i++) {
            String a=args[i];
            if ("--help".equals(a)||"-h".equals(a)) { o.help=true; continue; }
            if ("--version".equals(a)) { o.version=true; continue; }
            if (!a.startsWith("--")) throw new IllegalArgumentException("Argumento inesperado: "+a);
            if (i+1>=args.length) throw new IllegalArgumentException("Falta valor para: "+a);
            String v=args[++i], k=a.substring(2);
            switch(k) { case "operation" -> o.operation=v; case "input" -> o.input=v; case "output" -> o.output=v; default -> o.parameters.put(k,v); }
        }
        return o;
    }
    public static void printHelp() {
        System.out.println("pdi_lab - projeto-base M1\n\nUso: java -jar target/pdi-lab.jar --input <arquivo> --output <arquivo> --operation <operacao> [opcoes]\n     java -jar target/pdi-lab.jar --help\n     java -jar target/pdi-lab.jar --version");
    }
    public static void printVersion() { System.out.println("pdi_lab 0.2.0 contract=2"); }
}
