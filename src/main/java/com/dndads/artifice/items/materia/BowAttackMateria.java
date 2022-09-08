package com.dndads.artifice.items.materia;

import com.dndads.artifice.Artifice;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shadows.apotheosis.deadly.affix.AffixHelper;
import shadows.apotheosis.deadly.affix.Affixes;

public class BowAttackMateria extends MateriaBase {
    private static String hover_text = "Hold a bow in your off-hand to meld.\n" +
            "Adds bonus damage.";
    public BowAttackMateria() {
        super(new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP),
                hover_text
        );
    }

    @Override
    boolean isValidItemForMeld(ItemStack stack) {
        return MateriaHelper.itemIsBow(stack);
    }

    @Override
    void addAttribute(ItemStack stack) {

        //APOTHEOSIS AGAIN!!
        AffixHelper.applyAffix(stack, Affixes.SNIPE_DAMAGE, 3.0F);

    }
}
