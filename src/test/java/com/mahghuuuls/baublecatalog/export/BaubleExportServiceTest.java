package com.mahghuuuls.baublecatalog.export;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaubleExportServiceTest {

    @TempDir
    File configDirectory;

    @Test
    void writesTimestampedHeaderOnlyCsvUnderModConfigDirectory() throws Exception {
        BaubleExportService service = new BaubleExportService();

        ExportResult result = service.exportHeadersOnly(
                configDirectory,
                LocalDateTime.of(2026, 7, 8, 17, 5, 6)
        );

        File expectedFile = new File(
                new File(configDirectory, "baublecatalog"),
                "baublecatalog-baubles-2026-07-08-17-05-06.csv"
        );
        String contents = new String(Files.readAllBytes(expectedFile.toPath()), StandardCharsets.UTF_8);

        assertTrue(result.isSuccess());
        assertEquals(expectedFile, result.getOutputFile());
        assertEquals(0, result.getRowCount());
        assertEquals(
                "registry_id,metadata,display_name,source_mod_id,bauble_type_id,"
                        + "source_mod_name,bauble_type_display_name,rarity,tooltip,"
                        + "creative_tab,max_stack_size,max_durability,ore_dictionary_names\r\n",
                contents
        );
    }
}
