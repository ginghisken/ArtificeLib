package com.dndads.artifice;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {

    @SubscribeEvent
    public static void updateItemAttributes(ItemAttributeModifierEvent event) {

        //System.out.println("The ItemAttributeModifierEvent Fired!");

        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();

        EquipmentSlotType slot = event.getSlotType();
        AttributeModifier testAttrib;
        //AttributeModifier bepis = new AttributeModifier("extraDamage", 100.0D, AttributeModifier.Operation.ADDITION);

        // more testing commands
        //System.out.println(itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND));
        //if (itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND){}
        // itemStack.getOrCreateTagElement("rarity");
        // itemStack.addAttributeModifier();

        CompoundNBT checkedTag = itemStack.getTag();


/*
        if (checkedTag != null) {
            if (item == Items.DIAMOND_SWORD && slot == EquipmentSlotType.MAINHAND) {
                if (!checkedTag.contains("Ismodified")) {
                    //System.out.println("A modifier was applied to a diamond sword!");
                    //testAttrib = (AttributeModifier) itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(Attributes.ATTACK_DAMAGE).toArray()[0];
                    //double damageAmount = testAttrib.getAmount();
                    //System.out.println("Base weapon damage: " + damageAmount);
                    //damageAmount += 5.0D;
                    //System.out.println(checkedTag);

                    //itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, bepis, EquipmentSlotType.MAINHAND);
                    //itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("extraDamage", damageAmount, AttributeModifier.Operation.ADDITION), EquipmentSlotType.MAINHAND);
                    //itemStack.getOrCreateTagElement("Ismodified");
                    //System.out.println("A modifier was applied to a diamond sword!");
                    //System.out.println(itemStack.getDamageValue()); //this outputs the durability damage on an item, not the item's damage output. ben is retarded for suggesting otherwise




                }
            }
        }

 */

        /* old weird stuff

        if (checkedTag.contains("common")) {

            if (item == Items.DIAMOND_SWORD && slot == EquipmentSlotType.MAINHAND) {

                // itemStack.getOrCreateTagElement("common");
                // itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, bepis, EquipmentSlotType.MAINHAND);

                System.out.println("A Modifier Was Applied!");
            }
        }

         */
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void modifierTooltips(ItemTooltipEvent e) {
        ItemStack stack = e.getItemStack();
        if (stack.hasTag() && stack.getTag().contains("melded")) {
            List<ITextComponent> tips = new ArrayList<>();
            tips.add(new TranslationTextComponent("tooltip.test"));
            e.getToolTip().addAll(1, tips);
        }
    }
}


