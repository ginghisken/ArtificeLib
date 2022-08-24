package com.dndads.artifice.items;

import com.dndads.artifice.Artifice;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.world.World;

public abstract class EngraverBase extends Item {

    public EngraverBase(Item.Properties p) {
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

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {

        ItemStack mainHandItem = playerIn.getMainHandItem();
        ItemStack offHandItem = playerIn.getOffhandItem();

        if (hand == Hand.OFF_HAND) return new ActionResult<ItemStack>(ActionResultType.FAIL, offHandItem);

        // Run the actual use animation.
        playerIn.startUsingItem(hand);

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, mainHandItem);
    }

    public abstract void engraveItem (ItemStack stack, String engraveType);

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {

        // Get the coordinates of the player.
        double xPos = entityLiving.getX();
        double yPos = entityLiving.getY();
        double zPos = entityLiving.getZ();

        // Get the offhand item stack
        ItemStack offhandItem = entityLiving.getOffhandItem();
        // Get the main hand item stack.
        ItemStack mainHandItemStack = entityLiving.getMainHandItem();

        String engraveType = EngraverHelper.itemEngraveCategory(offhandItem);

        if (engraveType != "Not Engravable") {



            return mainHandItemStack;
        }
        else {
            // Play a failure sound.
            if (worldIn.isClientSide) {
                worldIn.playLocalSound(xPos, yPos, zPos, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            }

            // Do not consume the engraver.
            return mainHandItemStack;
        }

    }

}
