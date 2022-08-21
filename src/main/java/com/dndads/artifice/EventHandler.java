
package com.dndads.artifice;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {

    @SubscribeEvent
    public static void updateItemAttributes(ItemAttributeModifierEvent event) {

        System.out.println("The ItemAttributeModifierEvent Fired!");

        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();

        EquipmentSlotType slot = event.getSlotType();

        AttributeModifier bepis = new AttributeModifier("damage", 4.0D, AttributeModifier.Operation.MULTIPLY_BASE);


        //System.out.println(itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND));

        //if (itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND) {

        // itemStack.getOrCreateTagElement("rarity");
        // itemStack.addAttributeModifier();

        CompoundNBT checkedTag = itemStack.getTag();

        System.out.println(checkedTag);

        if (checkedTag.contains("common")) {

            if (item == Items.DIAMOND_SWORD && slot == EquipmentSlotType.MAINHAND) {

                //itemStack.getOrCreateTagElement("common");
                //itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, bepis, EquipmentSlotType.MAINHAND);

                System.out.println("A Modifier Was Applied!");
            }
        }
    }
}

