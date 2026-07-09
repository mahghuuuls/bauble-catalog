package com.mahghuuuls.baublecatalog.metadata;

import java.util.Arrays;
import java.util.List;

public final class BaubleExportRow {

    private final String registryId;
    private final String metadata;
    private final String displayName;
    private final String sourceModId;
    private final String baubleTypeId;

    public BaubleExportRow(
            String registryId,
            String metadata,
            String displayName,
            String sourceModId,
            String baubleTypeId
    ) {
        this.registryId = registryId;
        this.metadata = metadata;
        this.displayName = displayName;
        this.sourceModId = sourceModId;
        this.baubleTypeId = baubleTypeId;
    }

    public List<String> toCsvFields() {
        return Arrays.asList(
                registryId,
                metadata,
                displayName,
                sourceModId,
                baubleTypeId,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        );
    }
}
