package com.mahghuuuls.baublecatalog.csv;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CsvSchema {

    private static final List<String> HEADERS = Collections.unmodifiableList(Arrays.asList(
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
    ));

    private CsvSchema() {
    }

    public static List<String> headers() {
        return HEADERS;
    }
}
