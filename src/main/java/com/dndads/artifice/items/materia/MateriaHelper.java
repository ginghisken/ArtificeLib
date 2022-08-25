package com.dndads.artifice.items.materia;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;


public class MateriaHelper {
    // Return true if an item can be melded and false if it cannot.
    public static boolean itemCanBeMelded(ItemStack stack) {
        CompoundNBT tags = stack.getTag();
        return (tags == null || !tags.contains("melded")) && !stack.getItem().toString().contains("materia");
    }

    // Return true if an item is a weapon and false if it isn't.
    public static boolean itemIsWeapon(ItemStack stack) {
//        CompoundNBT tags = stack.getTag();
        // First check if it can be melded to begin with, then if there is an Attack Damage key
        if (itemCanBeMelded(stack) && stack.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsKey(Attributes.ATTACK_DAMAGE)) {
            return true;
        }
        return false;
    }

}
