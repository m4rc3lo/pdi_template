package br.univali.pdi;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;

public class InfrastructureTest {
    @Test
    public void validatesTypedParameters() {
        CliOptions options = new CliOptions();
        options.operation = "quantize";
        options.input = "in.png";
        options.output = "out.png";
        options.parameters.put("levels", "8");

        assertTrue(Contract.validate(options).ok());
        assertEquals(8, Parameters.asInt(options, "levels"));

        options.parameters.put("levels", "3");
        assertFalse(Contract.validate(options).ok());
    }

    @Test
    public void readsKernelAndWritesResultFiles() throws Exception {
        Path root = Files.createTempDirectory("pdi-template-test");
        try {
            Path kernelPath = root.resolve("identity.txt");
            Files.writeString(kernelPath, "0 0 0\n0 1 0\n0 0 0\n");

            Kernel kernel = KernelIO.read(kernelPath);
            assertEquals(3, kernel.size());
            assertEquals(1, kernel.radius());
            assertEquals(1.0, kernel.at(1, 1), 0.0);

            long[] histogram = new long[256];
            histogram[0] = 2;
            histogram[255] = 1;
            Path csvPath = root.resolve("nested/histogram.csv");
            ResultIO.writeHistogramCsv(csvPath, histogram);
            assertTrue(Files.exists(csvPath));

            Map<String, String> metadata = new LinkedHashMap<>();
            metadata.put("operation", "test");
            metadata.put("status", "ok");
            Path jsonPath = root.resolve("nested/metadata.json");
            ResultIO.writeJsonObject(jsonPath, metadata);
            assertTrue(Files.exists(jsonPath));
        } finally {
            try (var paths = Files.walk(root)) {
                paths.sorted((a, b) -> b.compareTo(a)).forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (Exception ignored) {
                        // Limpeza de arquivo temporário do teste.
                    }
                });
            }
        }
    }
}
