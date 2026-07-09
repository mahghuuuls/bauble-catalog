package com.mahghuuuls.baublecatalog.metadata;

import net.minecraft.item.ItemStack;

public class NoOpTooltipProvider implements TooltipProvider {

    @Override
    public String tooltipFor(ItemStack stack) {
        return "";
    }
}
