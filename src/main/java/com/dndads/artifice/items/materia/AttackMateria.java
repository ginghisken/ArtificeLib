package com.dndads.artifice.items.materia;

import com.dndads.artifice.Artifice;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import shadows.apotheosis.deadly.affix.Affix;
import shadows.apotheosis.deadly.affix.AffixHelper;
import shadows.apotheosis.deadly.affix.Affixes;
import shadows.apotheosis.deadly.affix.impl.melee.FireDamageAffix;

import javax.annotation.Nullable;
import java.util.List;

public class AttackMateria extends MateriaBase {
    private static String hover_text = "Hold a weapon in your off-hand to meld.\n" +
            "Adds bonus fire and physical damage.";
    public AttackMateria() {
        super(new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP),
                hover_text
        );
    }

    // Attack Materia can be used only on weapons.
    @Override
    boolean isValidItemForMeld(ItemStack stack) {
        return MateriaHelper.itemIsWeapon(stack);
    }

    @Override
    void addAttribute(ItemStack stack) {
        // Get the weapon's base damage.
        AttributeModifier attackAttribute = (AttributeModifier) stack.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(Attributes.ATTACK_DAMAGE).toArray()[0];

        // Add 5 damage to weapon's base damage.
        double baseDamage = attackAttribute.getAmount();
        double newDamage = baseDamage + 5;

        // Set new value.
        stack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("extra_damage", newDamage, AttributeModifier.Operation.ADDITION), EquipmentSlotType.MAINHAND);
        // APOTHEOSIS!
        AffixHelper.applyAffix(stack, Affixes.FIRE_DAMAGE, 3.0F);
    }

}
