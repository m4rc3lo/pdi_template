package br.univali.pdi;

/** Exceção de infraestrutura associada a um código de saída padronizado. */
public final class PdiException extends RuntimeException {
    private final int code;

    public PdiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public PdiException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int code() {
        return code;
    }
}
