package com.dndads.artifice.items.jobstone;

import com.dndads.artifice.Artifice;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

// Base template to expand on for all jobstones
public abstract class JobstoneBase extends TieredItem {
    // Text that appears when the item is hovered. Filled in by the constructor.
    protected String textOnHover;
    public JobstoneBase(IItemTier tier, Item.Properties p, String hover_text) {
        super(tier, new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP)
        );
        this.textOnHover = hover_text;
    }

    // Must override this method to have the item actually do something.
    public abstract ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused);


    // Adds the specified hover text to the item. Automatically splits the hover text on newline characters.
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        // Splits the hover_text string on any newline characters.
        String[] t = textOnHover.split("\n");
        // For each individual string, add it to the tooltip.
        for(String s : t) {
            tooltip.add(new StringTextComponent("\u00A77" + s));
        }
    }
}
