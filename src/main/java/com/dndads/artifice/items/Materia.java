package com.dndads.artifice.items;

import com.dndads.artifice.Artifice;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.swing.*;
import java.util.Objects;

public class Materia extends Item {
    public Materia() {
        super(new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP)
        );
    }

    // sets the animation that plays on use to that of a bow.
    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    // number of ticks it takes to activate in ticks, 20 ticks per second.
    @Override
    public int getUseDuration(ItemStack stack) {
        return 30;
    }

    // the actual effect of using the item.
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {

        ItemStack mainHandItem = playerIn.getMainHandItem();
        // ItemStack offhandItem = playerIn.getOffhandItem();

        // get the player's offhand item's NBT tags.
        // CompoundNBT offhandItemNbt = offhandItem.getTag();

        // Run the actual use animation.
        playerIn.startUsingItem(hand);

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, mainHandItem);
    }

/*

        // get offhand item's NBT tags.
        CompoundNBT offhandItemNbt = offhandItem.getTag();

        if (offhandItemNbt != null && offhandItemNbt.contains("melded") == false) {
            offhandItemNbt.putBoolean("melded", true);
        }

        /*

        else {
            if (worldIn.isClientSide == false) {
                playerIn.sendMessage(new StringTextComponent("Weapon is already melded!"),
                        null);
            }
        }

         */
        /*

        // get offhand item's attack damage in the form of a string (CURRENTLY BROKEN, breaks because it would fire on stuff like air).
        // AttributeModifier testAttrib = (AttributeModifier) offhandItem.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(Attributes.ATTACK_DAMAGE).toArray()[0];

        System.out.println(mainHandItem);
        System.out.println(offhandItem);
        // System.out.println("Offhand Item Base Damage: " + testAttrib.getAmount());

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, mainHandItem);
    }
    */

    // returns an empty itemstack at the end of use.
    // add conditionals here for not deleting if there is no valid item to apply a modifier to?
    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        ItemStack mainHandItem = entityLiving.getMainHandItem();
        ItemStack offhandItem = entityLiving.getOffhandItem();

        // Gets the coordinates of the player.
        double xPos = entityLiving.getX();
        double yPos = entityLiving.getY();
        double zPos = entityLiving.getZ();

        // get the player's offhand item's NBT tags.
        CompoundNBT offhandItemNbt = offhandItem.getTag();

        // If the offhand item doesn't have null tags and also doesn't have the 'melded' tag, add the melded tag with a value of true.
        if (offhandItemNbt != null && offhandItemNbt.contains("melded") == false) {
            offhandItemNbt.putBoolean("melded", true);

            // Play a success sound.
            if (worldIn.isClientSide) {
                worldIn.playLocalSound(xPos, yPos, zPos, SoundEvents.ANVIL_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            }

            // Get the player's main hand and move the offhand item into it.
            // (THIS DOES NOT WORK BUT IT WAS A NICE TRY, revisit later)

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

    /* Maybe useful?
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();

        if (!world.isClientSide) {
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());
        }

        return super.onItemUseFirst(stack, context);

    }

     */

}
