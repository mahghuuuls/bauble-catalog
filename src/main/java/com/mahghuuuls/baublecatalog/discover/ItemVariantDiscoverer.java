package com.mahghuuuls.baublecatalog.discover;

import com.mahghuuuls.baublecatalog.BaubleCatalogMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ItemVariantDiscoverer {

    public List<DiscoveredVariant> discover() {
        Map<String, DiscoveredVariant> variants = new LinkedHashMap<String, DiscoveredVariant>();
        for (Item item : ForgeRegistries.ITEMS) {
            try {
                discoverItemVariants(item, variants);
            } catch (RuntimeException e) {
                BaubleCatalogMod.LOGGER.warn(
                        "Skipping item after bauble catalog discovery failure: {}",
                        describeItem(item),
                        e
                );
            }
        }
        return new ArrayList<DiscoveredVariant>(variants.values());
    }

    private void discoverItemVariants(Item item, Map<String, DiscoveredVariant> variants) {
        if (item == null || item == Items.AIR) {
            return;
        }

        ResourceLocation registryName = item.getRegistryName();
        NonNullList<ItemStack> subItems = NonNullList.create();
        collectSubItems(item, CreativeTabs.SEARCH, subItems);

        CreativeTabs[] tabs = item.getCreativeTabs();
        if (tabs != null) {
            for (CreativeTabs tab : tabs) {
                collectSubItems(item, tab, subItems);
            }
        }

        if (subItems.isEmpty()) {
            addVariant(variants, new ItemStack(item), registryName, "default");
            return;
        }

        for (ItemStack stack : subItems) {
            addVariant(variants, stack, registryName, "creative-tab");
        }
    }

    private void collectSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (tab == null) {
            return;
        }

        try {
            item.getSubItems(tab, subItems);
        } catch (RuntimeException e) {
            BaubleCatalogMod.LOGGER.warn(
                    "Skipping subitem discovery for {} in creative tab {}",
                    item.getRegistryName(),
                    tab.getTabLabel(),
                    e
            );
        }
    }

    private void addVariant(
            Map<String, DiscoveredVariant> variants,
            ItemStack stack,
            ResourceLocation registryName,
            String discoverySource
    ) {
        if (stack == null || stack.isEmpty()) {
            return;
        }

        ResourceLocation stackRegistryName = stack.getItem().getRegistryName();
        ResourceLocation effectiveRegistryName = stackRegistryName == null ? registryName : stackRegistryName;
        String key = keyFor(stack, effectiveRegistryName);
        if (!variants.containsKey(key)) {
            variants.put(key, new DiscoveredVariant(stack, effectiveRegistryName, discoverySource));
        }
    }

    private String keyFor(ItemStack stack, ResourceLocation registryName) {
        String id = registryName == null ? "<unregistered>" : registryName.toString();
        String tag = stack.hasTagCompound() ? stack.getTagCompound().toString() : "";
        return id + "|" + stack.getMetadata() + "|" + tag;
    }

    private String describeItem(Item item) {
        if (item == null) {
            return "<null item>";
        }

        ResourceLocation registryName = item.getRegistryName();
        return registryName == null ? "<unregistered item>" : registryName.toString();
    }
}
