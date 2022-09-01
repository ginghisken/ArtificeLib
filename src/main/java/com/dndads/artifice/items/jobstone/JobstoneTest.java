package com.dndads.artifice.items.jobstone;

import com.dndads.artifice.Artifice;
import com.dndads.artifice.util.CurioItemCapability;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.UUID;

public class JobstoneTest extends JobstoneBase {
    // Text that appears when the item is hovered.
    private static String hover_text = "";

    // Constructor
    public JobstoneTest() {
        super(ItemTier.STONE, new Item.Properties()
                .stacksTo(1)
                .tab(Artifice.ARTIFICE_GROUP),
                hover_text
        );
    }

    // Initialize the capabilities associated with this item.
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused) {
        // Create the provider using a new ICurio (interface for Curio in particular.
        return CurioItemCapability.createProvider(new ICurio() {
            // Called every tick when the item is equipped.
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                ICurio.super.curioTick(identifier, index, livingEntity);
            }
            // Gets a map of attribute modifiers on this curio.
            @Override
            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
                // LinkedHashMultimap: Multimap with no duplicate key-value entries; returns collection whose iterators follow the ordering in which the data was added to the multimap.
                Multimap<Attribute, AttributeModifier> attributes = LinkedHashMultimap.create();
                // Put a new attribute mod (this is what the item actually changes)
                attributes.put(Attributes.ATTACK_SPEED,     // What attribute is being modified
                        // Attribute Modifier: UUID (whatever slot this jobstone is in), name of the change, value of the modification, modification type
                        new AttributeModifier(uuid, "artifice:attack_speed_bonus", 0.5,
                                AttributeModifier.Operation.MULTIPLY_TOTAL));

                // Return this map.
                return attributes;
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
