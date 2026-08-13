package br.univali.pdi;
public final class Contract {
    public record Result(int code, String message) { public boolean ok(){ return code==ExitCode.SUCCESS; } }
    private Contract() {}
    public static Result validate(CliOptions o) {
        if (o.operation==null || o.operation.isBlank()) return new Result(ExitCode.INVALID_ARGUMENTS,"Informe --operation.");
        if (!Operations.isKnown(o.operation)) return new Result(ExitCode.UNKNOWN_OPERATION,"Operacao desconhecida: "+o.operation);
        if (o.input==null || o.input.isBlank()) return new Result(ExitCode.INVALID_ARGUMENTS,"Informe --input.");
        if (!"inspect".equals(o.operation) && (o.output==null || o.output.isBlank())) return new Result(ExitCode.INVALID_ARGUMENTS,"Informe --output.");
        return new Result(ExitCode.SUCCESS,"");
    }
}
