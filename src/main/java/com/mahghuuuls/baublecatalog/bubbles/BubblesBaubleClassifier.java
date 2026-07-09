package com.mahghuuuls.baublecatalog.bubbles;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.IBaubleType;
import com.mahghuuuls.baublecatalog.BaubleCatalogMod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BubblesBaubleClassifier {

    public BaubleClassification classify(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }

        IBauble bauble = BaublesApi.getBauble(stack);
        if (bauble == null) {
            return null;
        }

        IBaubleType type = bauble.getType(stack);
        if (type == null || type.getRegistryName() == null) {
            return null;
        }

        return new BaubleClassification(
                type.getRegistryName().toString(),
                translationKeyFor(stack, type)
        );
    }

    public ResourceLocation getBaubleTypeId(ItemStack stack) {
        BaubleClassification classification = classify(stack);
        return classification == null ? null : new ResourceLocation(classification.getBaubleTypeId());
    }

    private String translationKeyFor(ItemStack stack, IBaubleType type) {
        try {
            String translationKey = type.getTranslationKey();
            return translationKey == null ? "" : translationKey;
        } catch (RuntimeException e) {
            BaubleCatalogMod.LOGGER.warn(
                    "Leaving optional bauble catalog field 'bauble_type_display_name' blank for {}",
                    stack.getItem().getRegistryName(),
                    e
            );
            return "";
        }
    }
}
