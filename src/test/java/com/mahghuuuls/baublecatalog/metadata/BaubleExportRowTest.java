package com.mahghuuuls.baublecatalog.metadata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaubleExportRowTest {

    @Test
    void writesRequiredFieldsAndLeavesOptionalFieldsBlank() {
        BaubleExportRow row = new BaubleExportRow(
                "examplemod:ring",
                "3",
                "Example Ring",
                "examplemod",
                "baubles:ring"
        );

        assertEquals(
                Arrays.asList(
                        "examplemod:ring",
                        "3",
                        "Example Ring",
                        "examplemod",
                        "baubles:ring",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                ),
                row.toCsvFields()
        );
    }
}
