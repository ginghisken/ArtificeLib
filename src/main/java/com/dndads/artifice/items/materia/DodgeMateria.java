package com.dndads.artifice.items.materia;

import com.dndads.artifice.Artifice;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.elenai.elenaidodge2.api.*;
import top.theillusivec4.curios.api.SlotTypePreset;

public class DodgeMateria extends MateriaBase {
    private static String hover_text = "Hold an item in your off-hand to meld.";

    public DodgeMateria() {
        super(new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP),
                hover_text
        );
    }

    @Override
    boolean isValidItemForMeld(ItemStack stack) {
        return MateriaHelper.itemIsWeapon(stack);
    }

    @Override
    void addAttribute(ItemStack stack) {
        stack.getOrCreateTag().putInt("easy_dodge", 2);
    }
}
