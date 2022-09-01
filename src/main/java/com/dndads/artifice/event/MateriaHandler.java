package com.dndads.artifice.event;

import com.elenai.elenaidodge2.api.SpendFeatherEvent;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
}
