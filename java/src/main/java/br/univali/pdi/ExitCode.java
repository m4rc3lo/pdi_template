package br.univali.pdi;

/** Códigos de saída padronizados entre C++, Java e Python. */
public final class ExitCode {
    public static final int SUCCESS = 0;
    public static final int GENERAL_ERROR = 1;
    public static final int INVALID_ARGUMENTS = 2;
    public static final int READ_ERROR = 3;
    public static final int WRITE_ERROR = 4;
    public static final int INVALID_PARAMETER = 5;
    public static final int UNKNOWN_OPERATION = 6;

    private ExitCode() {}
}
