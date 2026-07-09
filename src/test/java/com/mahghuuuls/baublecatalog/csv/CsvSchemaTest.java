package com.mahghuuuls.baublecatalog.csv;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvSchemaTest {

    @Test
    void containsApprovedHeadersInOrder() {
        assertEquals(
                Arrays.asList(
                        "registry_id",
                        "metadata",
                        "display_name",
                        "source_mod_id",
                        "bauble_type_id",
                        "source_mod_name",
                        "bauble_type_display_name",
                        "rarity",
                        "tooltip",
                        "creative_tab",
                        "max_stack_size",
                        "max_durability",
                        "ore_dictionary_names"
                ),
                CsvSchema.headers()
        );
    }
}
