package com.dndads.artifice.items.jobstone;

import com.dndads.artifice.Artifice;
import com.dndads.artifice.util.CurioItemCapability;
import com.elenai.elenaidodge2.list.PotionList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;

public class JobstoneScout extends JobstoneBase {
    private static final String text = JobstoneHelper.getText("scout")[0];
    private static final String shift_text = JobstoneHelper.getText("scout")[1];

    public JobstoneScout(IItemTier tier) {
        super(tier, new Item.Properties()
                    .stacksTo(1)
                    .tab(Artifice.ARTIFICE_GROUP),
            text, shift_text
        );
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        // Create the provider using a new ICurio (interface for Curio in particular.)
        return CurioItemCapability.createProvider(new ICurio() {

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if(livingEntity instanceof PlayerEntity && livingEntity.tickCount % 19 == 0) {
                    PlayerEntity player = (PlayerEntity) livingEntity;

                    // Add feather regen effect
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
