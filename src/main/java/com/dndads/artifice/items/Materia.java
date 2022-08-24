package com.dndads.artifice.items;

import com.dndads.artifice.Artifice;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class Materia extends Item {
    public Materia() {
        super(new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP)
        );
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

    // The effect of using the item.
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {

        ItemStack mainHandItem = playerIn.getMainHandItem();

        // Run the actual use animation.
        playerIn.startUsingItem(hand);

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, mainHandItem);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        ItemStack mainHandItem = entityLiving.getMainHandItem();
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

        // If the offhand item doesn't have null tags and also doesn't have the 'melded' tag, add the melded tag with a value of true.
        // Also checks if the offhand item contains the ATTACK_DAMAGE attribute in the first place.
        // ALSO checks that you don't have materia in your offhand.
        if (offhandItemNbt != null && offhandItemNbt.contains("melded") == false
                && offhandItem.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsKey(Attributes.ATTACK_DAMAGE)
                && offhandItemName != "materia") {
            offhandItemNbt.putBoolean("melded", true);

            // Play a success sound.
            if (worldIn.isClientSide) {
                worldIn.playLocalSound(xPos, yPos, zPos, SoundEvents.ANVIL_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            }

            // Get and record the offhand weapon's base damage.
            AttributeModifier testAttrib = (AttributeModifier) offhandItem.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(Attributes.ATTACK_DAMAGE).toArray()[0];
            double baseDamageAmount = testAttrib.getAmount();
            System.out.println("base: " + baseDamageAmount);

            // Add 5 to the offhand weapon's base damage.
            double newDamageAmount = baseDamageAmount + 5;

            // Add a new modifier that overwrites the old damage with the new higher value.
            offhandItem.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("extraDamage", newDamageAmount, AttributeModifier.Operation.ADDITION), EquipmentSlotType.MAINHAND);

            // Get the player's main hand and move the offhand item into it.
            // >>>>>(THIS DOES NOT WORK BUT IT WAS A NICE TRY, revisit later)<<<<<
            // Hand mainhand = entityLiving.getUsedItemHand();
            // entityLiving.setItemInHand(mainhand, offhandItem);
            // PlayerInventory playerInventory = ((PlayerEntity)entityLiving).inventory;
            // ItemStack selectedItem = playerInventory.getSelected();
            // int offhandItemSlot = playerInventory.findSlotMatchingItem(offhandItem);
            // playerInventory.setItem(offhandItemSlot, ItemStack.EMPTY);

            // Consume the materia.
            return ItemStack.EMPTY;

        } else {

            // Play a failure sound.
            if (worldIn.isClientSide) {
                worldIn.playLocalSound(xPos, yPos, zPos, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            }

            // Do not consume the materia.
            return stack;

        }
    }

    // Add a tooltip.
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("\u00A77" + "Hold an item in your off-hand to meld!"));
    }

    // Make it sparkle.
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
