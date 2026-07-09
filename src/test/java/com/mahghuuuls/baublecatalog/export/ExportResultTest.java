package com.mahghuuuls.baublecatalog.export;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExportResultTest {

    @Test
    void successCarriesProblemRowCount() {
        File outputFile = new File("export.csv");

        ExportResult result = ExportResult.success(outputFile, 4, 2);

        assertTrue(result.isSuccess());
        assertEquals(outputFile, result.getOutputFile());
        assertEquals(4, result.getRowCount());
        assertEquals(2, result.getProblemRowCount());
    }
}
