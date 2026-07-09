package com.mahghuuuls.baublecatalog.metadata;

import com.mahghuuuls.baublecatalog.discover.DiscoveredVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BaubleMetadataReader {

    public BaubleExportRow readRequiredRow(DiscoveredVariant variant, ResourceLocation baubleTypeId) {
        ItemStack stack = variant.getStack();
        ResourceLocation registryName = variant.getRegistryName();

        String registryId = registryName == null ? "" : registryName.toString();
        String sourceModId = registryName == null ? "" : registryName.getNamespace();
        String typeId = baubleTypeId == null ? "" : baubleTypeId.toString();

        return new BaubleExportRow(
                registryId,
                String.valueOf(stack.getMetadata()),
                stack.getDisplayName(),
                sourceModId,
                typeId
        );
    }
}
