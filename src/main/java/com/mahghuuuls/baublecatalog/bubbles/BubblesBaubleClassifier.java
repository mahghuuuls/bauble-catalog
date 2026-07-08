package com.mahghuuuls.baublecatalog.bubbles;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.IBaubleType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public final class BubblesBaubleClassifier {

    private BubblesBaubleClassifier() {
    }

    public static ResourceLocation getBaubleTypeId(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }

        IBauble bauble = BaublesApi.getBauble(stack);
        if (bauble == null) {
            return null;
        }

        IBaubleType type = bauble.getType(stack);
        return type == null ? null : type.getRegistryName();
    }
}
