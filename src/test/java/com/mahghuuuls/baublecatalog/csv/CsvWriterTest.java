package com.mahghuuuls.baublecatalog.csv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvWriterTest {

    @TempDir
    File tempDirectory;

    @Test
    void writesHeaderAndEscapesCsvFields() throws Exception {
        File outputFile = new File(tempDirectory, "export.csv");
        CsvWriter writer = new CsvWriter();

        writer.write(
                outputFile,
                Arrays.asList("plain", "comma", "quote", "newline", "carriage", "empty"),
                Collections.singletonList(Arrays.asList(
                        "value",
                        "amulet, ring",
                        "marked \"rare\"",
                        "line one\nline two",
                        "carriage\rreturn",
                        null
                ))
        );

        String contents = new String(Files.readAllBytes(outputFile.toPath()), StandardCharsets.UTF_8);

        assertEquals(
                "plain,comma,quote,newline,carriage,empty\r\n"
                        + "value,\"amulet, ring\",\"marked \"\"rare\"\"\",\"line one\nline two\",\"carriage\rreturn\",\r\n",
                contents
        );
    }
}
