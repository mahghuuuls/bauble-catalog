package com.mahghuuuls.baublecatalog.client;

import com.mahghuuuls.baublecatalog.metadata.TooltipProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class ClientTooltipProvider implements TooltipProvider {

    @Override
    public String tooltipFor(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "";
        }

        List<String> lines = stack.getTooltip(Minecraft.getMinecraft().player, ITooltipFlag.TooltipFlags.NORMAL);
        StringBuilder tooltip = new StringBuilder();
        for (String line : lines) {
            if (tooltip.length() > 0) {
                tooltip.append('\n');
            }
            tooltip.append(TextFormatting.getTextWithoutFormattingCodes(line));
        }
        return tooltip.toString();
    }
}
