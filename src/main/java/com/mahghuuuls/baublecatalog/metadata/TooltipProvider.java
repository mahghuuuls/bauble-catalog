package com.mahghuuuls.baublecatalog.metadata;

import net.minecraft.item.ItemStack;

public interface TooltipProvider {

    String tooltipFor(ItemStack stack);
}
