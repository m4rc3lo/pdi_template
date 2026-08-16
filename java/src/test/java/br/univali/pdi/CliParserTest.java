package br.univali.pdi;

import static org.junit.Assert.*;

import org.junit.Test;

public class CliParserTest {
    @Test
    public void parsesAndValidatesKnownOperation() {
        CliOptions options = CliParser.parse(new String[] {
            "--operation", "negative",
            "--input", "images/input/m1_gray_ramp_256.png",
            "--output", "images/output/out.png"
        });

        assertEquals("negative", options.operation);
        assertTrue(Operations.isKnown(options.operation));
        assertTrue(Contract.validate(options).ok());
        assertFalse(Operations.isKnown("x"));
    }
}
