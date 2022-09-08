package com.dndads.artifice.event;

import com.elenai.elenaidodge2.api.SpendFeatherEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import shadows.apotheosis.deadly.affix.Affix;
import shadows.apotheosis.deadly.affix.AffixHelper;
import shadows.apotheosis.deadly.affix.Affixes;

import java.util.Map;

public class MateriaHandler {



    @SubscribeEvent
    public static void dodgeMateriaUpdate(SpendFeatherEvent event) {
        PlayerEntity player = event.getPlayer();
        ItemStack i = player.getMainHandItem();
        if (i.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsKey(Attributes.ATTACK_DAMAGE)) {
            CompoundNBT tags = i.getTag();
            if (tags != null && tags.contains("easy_dodge")) {
                event.setCost(event.getCost()/(int)tags.getFloat("easy_dodge"));
            }

        }
    }


    // WIP to modify arrow damage.
    @SubscribeEvent
    public static void modifyArrowDamage(ProjectileImpactEvent.Arrow event) {
        // Get the arrow
        AbstractArrowEntity firedArrow = event.getArrow();
        // Check if the Entity who fired the arrow is a player
        if (firedArrow.getOwner() instanceof PlayerEntity) {
            // Get the PlayerEntity and check their mainhand
            PlayerEntity player = (PlayerEntity) firedArrow.getOwner();
            ItemStack i = player.getMainHandItem();
            // Get all Affixes on the mainhand and check if it has the snipe damage one.
            Map<Affix, Float> affixes = AffixHelper.getAffixes(i);
            if (affixes.containsKey(Affixes.SNIPE_DAMAGE)) {
                double additionalDamage = 8.0;

                firedArrow.setBaseDamage(firedArrow.getBaseDamage() + additionalDamage);
                System.out.println("Damage was added to arrow on hit.");
            }
        }
    }
}
