package com.dndads.artifice.items;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;


public class EngraverHelper {

    // Returns the category of weapon engravings this item falls into
    // If the weapon has been engraved or cannot be engraved, returns "Not Engravable"
    public static String itemEngraveCategory(ItemStack stack) {
        CompoundNBT tags = stack.getTag();
        if (tags != null) {
            if (!tags.contains("Engraved")) {
                return getCategory(stack);
            }
            else {
                return "Not Engravable";
            }
        }
        else {
            return getCategory(stack);
        }
    }

    public static String getCategory(ItemStack stack) {
        if (stack.getItem().toString().contains("sword")) {
            return "Sword";
        }
        else if (stack.getItem().toString().contains("axe")) {
            return "Axe";
        }
        else {
            return "Not Engravable";
        }
    }
}
