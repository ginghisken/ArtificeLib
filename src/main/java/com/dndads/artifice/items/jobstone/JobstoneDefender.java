package com.dndads.artifice.items.jobstone;

import com.dndads.artifice.Artifice;
import com.dndads.artifice.util.CurioItemCapability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;

public class JobstoneDefender extends JobstoneBase {

    private static final String text = JobstoneHelper.getText("defender")[0];
    private static final String shift_text = JobstoneHelper.getText("defender")[1];

    public JobstoneDefender() {
        super(ItemTier.STONE, new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP),
                text, shift_text
        );
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        return CurioItemCapability.createProvider(new ICurio() {
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                ICurio.super.curioTick(identifier, index, livingEntity);
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

            @Override
            public boolean canEquipFromUse(SlotContext slot) {
                return true;
            }
        });
    }

}
