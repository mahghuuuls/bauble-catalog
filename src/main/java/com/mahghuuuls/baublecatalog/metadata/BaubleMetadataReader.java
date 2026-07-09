package com.mahghuuuls.baublecatalog.metadata;

import com.mahghuuuls.baublecatalog.BaubleCatalogMod;
import com.mahghuuuls.baublecatalog.bubbles.BaubleClassification;
import com.mahghuuuls.baublecatalog.discover.DiscoveredVariant;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class BaubleMetadataReader {

    private final TooltipProvider tooltipProvider;

    public BaubleMetadataReader() {
        this(new NoOpTooltipProvider());
    }

    public BaubleMetadataReader(TooltipProvider tooltipProvider) {
        this.tooltipProvider = tooltipProvider;
    }

    public BaubleExportRow readRow(DiscoveredVariant variant, BaubleClassification classification) {
        ItemStack stack = variant.getStack();
        ResourceLocation registryName = variant.getRegistryName();

        String registryId = registryName == null ? "" : registryName.toString();
        String sourceModId = registryName == null ? "" : registryName.getNamespace();
        String typeId = classification == null ? "" : classification.getBaubleTypeId();
        String typeTranslationKey = classification == null ? "" : classification.getBaubleTypeTranslationKey();

        return new BaubleExportRow(
                registryId,
                String.valueOf(stack.getMetadata()),
                stack.getDisplayName(),
                sourceModId,
                typeId,
                optional(variant, "source_mod_name", new OptionalReader() {
                    @Override
                    public String read() {
                        return sourceModName(sourceModId);
                    }
                }),
                optional(variant, "bauble_type_display_name", new OptionalReader() {
                    @Override
                    public String read() {
                        return baubleTypeDisplayName(typeTranslationKey);
                    }
                }),
                optional(variant, "rarity", new OptionalReader() {
                    @Override
                    public String read() {
                        return stack.getRarity().getName();
                    }
                }),
                optional(variant, "tooltip", new OptionalReader() {
                    @Override
                    public String read() {
                        return tooltipProvider.tooltipFor(stack);
                    }
                }),
                optional(variant, "creative_tab", new OptionalReader() {
                    @Override
                    public String read() {
                        return creativeTab(stack);
                    }
                }),
                optional(variant, "max_stack_size", new OptionalReader() {
                    @Override
                    public String read() {
                        return String.valueOf(stack.getMaxStackSize());
                    }
                }),
                optional(variant, "max_durability", new OptionalReader() {
                    @Override
                    public String read() {
                        return String.valueOf(stack.getMaxDamage());
                    }
                }),
                optional(variant, "ore_dictionary_names", new OptionalReader() {
                    @Override
                    public String read() {
                        return oreDictionaryNames(stack);
                    }
                })
        );
    }

    public BaubleExportRow readRequiredRow(DiscoveredVariant variant, ResourceLocation baubleTypeId) {
        String typeId = baubleTypeId == null ? "" : baubleTypeId.toString();
        return readRow(variant, new BaubleClassification(typeId, ""));
    }

    private String sourceModName(String sourceModId) {
        if (sourceModId == null || sourceModId.isEmpty()) {
            return "";
        }

        ModContainer container = Loader.instance().getIndexedModList().get(sourceModId);
        return container == null ? "" : container.getName();
    }

    private String baubleTypeDisplayName(String translationKey) {
        if (translationKey == null || translationKey.isEmpty()) {
            return "";
        }

        String translated = I18n.translateToLocal(translationKey);
        return translated == null || translated.equals(translationKey) ? translationKey : translated;
    }

    private String creativeTab(ItemStack stack) {
        CreativeTabs tab = stack.getItem().getCreativeTab();
        return tab == null ? "" : tab.getTabLabel();
    }

    private String oreDictionaryNames(ItemStack stack) {
        int[] oreIds = OreDictionary.getOreIDs(stack);
        List<String> names = new ArrayList<String>();
        for (int oreId : oreIds) {
            names.add(OreDictionary.getOreName(oreId));
        }
        return join(names, ";");
    }

    private String optional(DiscoveredVariant variant, String fieldName, OptionalReader reader) {
        try {
            String value = reader.read();
            return value == null ? "" : value;
        } catch (RuntimeException e) {
            BaubleCatalogMod.LOGGER.warn(
                    "Leaving optional bauble catalog field '{}' blank for {}",
                    fieldName,
                    variant.describe(),
                    e
            );
            return "";
        }
    }

    private String join(List<String> values, String separator) {
        StringBuilder result = new StringBuilder();
        for (String value : values) {
            if (result.length() > 0) {
                result.append(separator);
            }
            result.append(value);
        }
        return result.toString();
    }

    private interface OptionalReader {

        String read();
    }
}
