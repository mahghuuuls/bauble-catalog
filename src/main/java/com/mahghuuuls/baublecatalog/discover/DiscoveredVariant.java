package com.mahghuuuls.baublecatalog.discover;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public final class DiscoveredVariant {

    private final ItemStack stack;
    private final ResourceLocation registryName;
    private final String discoverySource;

    public DiscoveredVariant(ItemStack stack, ResourceLocation registryName, String discoverySource) {
        this.stack = stack.copy();
        this.registryName = registryName;
        this.discoverySource = discoverySource;
    }

    public ItemStack getStack() {
        return stack.copy();
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }

    public String getDiscoverySource() {
        return discoverySource;
    }

    public String describe() {
        String id = registryName == null ? "<unregistered>" : registryName.toString();
        return id + " meta=" + stack.getMetadata() + " source=" + discoverySource;
    }
}
