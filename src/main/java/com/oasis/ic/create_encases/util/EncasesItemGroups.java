package com.oasis.ic.create_encases.util;

import java.util.Collections;
import java.util.List;

import com.oasis.ic.create_encases.CreateEncases;
import com.oasis.ic.create_encases.EncasesAllBlocks;
import com.oasis.ic.create_encases.EncasesAllItems;
import com.simibubi.create.AllBlocks;
import com.tterrag.registrate.util.entry.ItemProviderEntry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class EncasesItemGroups {
    public static final ItemGroup CREATORS_DECORS = FabricItemGroupBuilder
            .create(
                    new Identifier(CreateEncases.ID, "general"))
                    .icon(EncasesAllBlocks.SHADOW_STEEL_ENCASED_COGWHEEL::asStack)
                    .appendItems(itemStacks -> {
                        itemStacks.addAll(Collections.nCopies(54, ItemStack.EMPTY));

                        // Line 1
                        itemStacks.set(0, AllBlocks.SHADOW_STEEL_CASING.asStack());
                        itemStacks.set(1, AllBlocks.REFINED_RADIANCE_CASING.asStack());

                        itemStacks.set(3, EncasesAllBlocks.ZINC_CASING.asStack());

                        itemStacks.set(5, EncasesAllBlocks.SHADOW_STEEL_LADDER.asStack());
                        itemStacks.set(6, EncasesAllBlocks.REFINED_RADIANCE_LADDER.asStack());
                        itemStacks.set(7, EncasesAllBlocks.RAILWAY_LADDER.asStack());
                        itemStacks.set(8, EncasesAllBlocks.ZINC_LADDER.asStack());

                        // Line 2
                        itemStacks.set(9, EncasesAllItems.SHADOW_STEEL.asStack());
                        itemStacks.set(10, EncasesAllItems.REFINED_RADIANCE.asStack());
            })
            .build();

    private static <T> void makeStacks(List<ItemStack> itemStacks, List<T> entries) {
        for (T entry : entries) {
            if (entry instanceof ItemProviderEntry<?>) {
                itemStacks.add(new ItemStack(((ItemProviderEntry<?>) entry).get()));
            }
        }
    }
}
