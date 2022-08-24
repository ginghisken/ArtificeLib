package com.dndads.artifice.items;

import com.dndads.artifice.Artifice;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.world.World;

public abstract class MateriaBase extends Item {
    public MateriaBase(Item.Properties p) {
        super(new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP)
        );
    }

    @Override
    public abstract UseAction getUseAnimation(ItemStack stack);

    @Override
    public abstract int getUseDuration(ItemStack stack);

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

        ItemStack offhandItem = entityLiving.getOffhandItem();

        // Get the coordinates of the player.
        double xPos = entityLiving.getX();
        double yPos = entityLiving.getY();
        double zPos = entityLiving.getZ();

        // Get the player's offhand item's NBT tags.
        CompoundNBT offhandItemNbt = offhandItem.getTag();

        //Check the name of the offhand item and output as a string.
        ItemStack offhandItemStack = entityLiving.getOffhandItem();
        Item offhandItemTest = offhandItemStack.getItem();
        String offhandItemName = offhandItemTest.toString();

        // Check the name of the main hand item and output as a string.
        ItemStack mainHandItemStack = entityLiving.getMainHandItem();

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
            if (offhandItemName == "materia") {
                return offhandItemStack;
            } else {
                return mainHandItemStack;
            }
        }
    }
}
