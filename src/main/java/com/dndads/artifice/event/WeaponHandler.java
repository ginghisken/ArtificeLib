package com.dndads.artifice.event;


import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WeaponHandler {

    @SubscribeEvent
    public static void TestJobstoneOnAttackUpdate(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        ItemStack i = player.getMainHandItem();
        // Check if main hand item is a weapon, loosely
        if (i.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsKey(Attributes.ATTACK_DAMAGE)) {
//            CuriosApi.getCuriosHelper().getEquippedCurios(player).map()

        }
    }
}
