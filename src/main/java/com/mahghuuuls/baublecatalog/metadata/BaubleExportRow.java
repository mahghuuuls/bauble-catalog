package com.mahghuuuls.baublecatalog.metadata;

import java.util.Arrays;
import java.util.List;

public final class BaubleExportRow {

    private final String registryId;
    private final String metadata;
    private final String displayName;
    private final String sourceModId;
    private final String baubleTypeId;
    private final String sourceModName;
    private final String baubleTypeDisplayName;
    private final String rarity;
    private final String tooltip;
    private final String creativeTab;
    private final String maxStackSize;
    private final String maxDurability;
    private final String oreDictionaryNames;

    public BaubleExportRow(
            String registryId,
            String metadata,
            String displayName,
            String sourceModId,
            String baubleTypeId,
            String sourceModName,
            String baubleTypeDisplayName,
            String rarity,
            String tooltip,
            String creativeTab,
            String maxStackSize,
            String maxDurability,
            String oreDictionaryNames
    ) {
        this.registryId = registryId;
        this.metadata = metadata;
        this.displayName = displayName;
        this.sourceModId = sourceModId;
        this.baubleTypeId = baubleTypeId;
        this.sourceModName = sourceModName;
        this.baubleTypeDisplayName = baubleTypeDisplayName;
        this.rarity = rarity;
        this.tooltip = tooltip;
        this.creativeTab = creativeTab;
        this.maxStackSize = maxStackSize;
        this.maxDurability = maxDurability;
        this.oreDictionaryNames = oreDictionaryNames;
    }

    public List<String> toCsvFields() {
        return Arrays.asList(
                registryId,
                metadata,
                displayName,
                sourceModId,
                baubleTypeId,
                sourceModName,
                baubleTypeDisplayName,
                rarity,
                tooltip,
                creativeTab,
                maxStackSize,
                maxDurability,
                oreDictionaryNames
        );
    }
}
