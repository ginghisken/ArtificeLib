package com.dndads.artifice.items.materia;

import com.dndads.artifice.Artifice;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class MateriaBase extends Item {
    protected String textOnHover;
    public MateriaBase(Item.Properties p, String hover_text) {
        super(new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP)
        );
        this.textOnHover = hover_text;
    }

    // Sets the animation that plays on use to that of a bow.
    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    // Number of ticks it takes to activate, 20 ticks per second.
    @Override
    public int getUseDuration(ItemStack stack) {
        return 30;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {

        ItemStack mainHandItem = playerIn.getMainHandItem();
        ItemStack offHandItem = playerIn.getOffhandItem();

        if (hand == Hand.OFF_HAND) return new ActionResult<ItemStack>(ActionResultType.FAIL, offHandItem);

        // Run the actual use animation.
        playerIn.startUsingItem(hand);

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, mainHandItem);
    }

    abstract boolean isValidItemForMeld(ItemStack stack);

    abstract void addAttribute(ItemStack stack);

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        // Get the coordinates of the player.
        double xPos = entityLiving.getX();
        double yPos = entityLiving.getY();
        double zPos = entityLiving.getZ();

        // Get the offhand item stack, as well as its associated item and name.
        ItemStack offhandItemStack = entityLiving.getOffhandItem();
        Item offhandItemTest = offhandItemStack.getItem();
        String offhandItemName = offhandItemTest.toString();

        // Get the main hand item stack.
        ItemStack mainHandItemStack = entityLiving.getMainHandItem();

        // Get the player's offhand item's NBT tags.
        CompoundNBT offhandItemNbt = offhandItemStack.getTag();

        // Checks if valid item to use materia on.
        if (this.isValidItemForMeld(offhandItemStack)) {
            offhandItemNbt.putBoolean("melded", true);

            // Play a success sound.
            if (worldIn.isClientSide) {
                worldIn.playLocalSound(xPos, yPos, zPos, SoundEvents.ANVIL_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            }

            this.addAttribute(offhandItemStack);

            return ItemStack.EMPTY;

        } else {

            // Play a failure sound.
            if (worldIn.isClientSide) {
                worldIn.playLocalSound(xPos, yPos, zPos, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            }

            // Do not consume the materia.
            return mainHandItemStack;
        }
    }

    // Make it sparkle.
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    // Adds the specified hover text to the item. Automatically splits the hover text on newline characters.
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        String[] t = textOnHover.split("\n");
        for(String s : t) {
            tooltip.add(new StringTextComponent("\u00A77" + s));
        }
    }
}
