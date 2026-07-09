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
                "baubles:ring",
                "Example Mod",
                "Ring",
                "Rare",
                "Example Ring\nA useful bauble",
                "misc",
                "1",
                "0",
                "oreRingExample;oreBaubleExample"
        );

        assertEquals(
                Arrays.asList(
                        "examplemod:ring",
                        "3",
                        "Example Ring",
                        "examplemod",
                        "baubles:ring",
                        "Example Mod",
                        "Ring",
                        "Rare",
                        "Example Ring\nA useful bauble",
                        "misc",
                        "1",
                        "0",
                        "oreRingExample;oreBaubleExample"
                ),
                row.toCsvFields()
        );
    }
}
