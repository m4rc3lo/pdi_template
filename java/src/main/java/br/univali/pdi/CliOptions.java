package br.univali.pdi;

import java.util.LinkedHashMap;
import java.util.Map;

/** Dados já separados da linha de comando antes da validação do contrato. */
public final class CliOptions {
    public boolean help;
    public boolean version;
    public String operation = "";
    public String input;
    public String output;
    public final Map<String, String> parameters = new LinkedHashMap<>();
}
