package com.dndads.artifice.items.jobstone;

import com.dndads.artifice.Artifice;
import com.dndads.artifice.util.CurioItemCapability;
import com.elenai.elenaidodge2.ElenaiDodge2;
import com.elenai.elenaidodge2.list.PotionList;
import com.elenai.elenaidodge2.potion.EnduranceEffect;
import com.elenai.elenaidodge2.api.FeathersHelper;
import com.elenai.elenaidodge2.potion.ReplenishmentEffect;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;

public class JobstoneScout extends JobstoneBase {
    private static String hover_text = "Job: Scout\n" +
            "Dodge faster, dodge better.";
    public JobstoneScout(IItemTier tier) {
        super(tier, new Item.Properties()
                    .stacksTo(1)
                    .tab(Artifice.ARTIFICE_GROUP),
            hover_text
        );
    }



    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        // Create the provider using a new ICurio (interface for Curio in particular).
        return CurioItemCapability.createProvider(new ICurio() {

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if(livingEntity instanceof PlayerEntity && livingEntity.tickCount % 19 == 0) {
                    PlayerEntity player = (PlayerEntity) livingEntity;

                    int duration = 20; // ticks
                    int effectiveness = (JobstoneScout.super.getTier().getLevel() - 1);
                    player.addEffect(new EffectInstance(PotionList.REPLENISHMENT_EFFECT, duration, effectiveness));
                }
            }

            // Change the drop type.
            @Nonnull
            @Override
            public DropRule getDropRule(LivingEntity livingEntity) {
                // Doesn't drop on death.
                return DropRule.ALWAYS_KEEP;
            }

            // Sound that plays when equipped. Optional, really.
            @Nonnull
            @Override
            public SoundInfo getEquipSound(SlotContext slotContext) {
                return new SoundInfo(SoundEvents.ARMOR_EQUIP_GOLD, 1.0f, 1.0f);
            }

            // Can you right-click to equip from selected slot?
            @Override
            public boolean canEquipFromUse(SlotContext slot) {
                return true;
            }
        });
    }

}
