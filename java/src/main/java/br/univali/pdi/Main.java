package br.univali.pdi;
public final class Main {
    private Main() {}
    public static void main(String[] args) { int c=execute(args); if(c!=0) System.exit(c); }
    static int execute(String[] args) {
        try {
            CliOptions o=CliParser.parse(args);
            if(o.help){ CliParser.printHelp(); return 0; }
            if(o.version){ CliParser.printVersion(); return 0; }
            Contract.Result v=Contract.validate(o);
            if(!v.ok()){ System.err.println("Erro: "+v.message()); return v.code(); }
            return Operations.run(o);
        } catch(IllegalArgumentException e){ System.err.println("Erro de argumentos: "+e.getMessage()); return 2; }
        catch(Exception e){ System.err.println("Erro: "+e.getMessage()); return 1; }
    }
}
