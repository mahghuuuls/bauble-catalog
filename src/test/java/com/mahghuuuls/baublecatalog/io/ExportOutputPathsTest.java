package com.mahghuuuls.baublecatalog.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExportOutputPathsTest {

    @TempDir
    File tempDirectory;

    @Test
    void buildsModScopedTimestampedCsvPath() {
        ExportOutputPaths paths = new ExportOutputPaths();

        File outputFile = paths.timestampedCsvFile(
                tempDirectory,
                LocalDateTime.of(2026, 7, 8, 17, 5, 6)
        );

        File expectedDirectory = new File(tempDirectory, "baublecatalog");
        File expectedFile = new File(expectedDirectory, "baublecatalog-baubles-2026-07-08-17-05-06.csv");
        assertEquals(expectedFile, outputFile);
    }
}
