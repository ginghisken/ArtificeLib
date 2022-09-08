package com.dndads.artifice.event;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.*;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import shadows.apotheosis.deadly.affix.Affix;
import shadows.apotheosis.deadly.affix.AffixHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventHandler {

    @SubscribeEvent
    public static void updateItemAttributes(ItemAttributeModifierEvent event) {
        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();

        EquipmentSlotType slot = event.getSlotType();
        AttributeModifier testAttrib;

        CompoundNBT checkedTag = itemStack.getTag();
    }

    // Display all tooltips based on materia additions.
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void modifierTooltips(ItemTooltipEvent e) {
        ItemStack stack = e.getItemStack();
        if (stack.hasTag() && stack.getTag().contains("melded")) {
            // All Attribute Modifiers
            Multimap<Attribute, AttributeModifier> atts = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
            // All tags
            CompoundNBT tags = stack.getTag();
            // All Affixes
            Map<Affix, Float> affixes = AffixHelper.getAffixes(stack);

            // Tooltip additions
            List<ITextComponent> tips = new ArrayList<>();

            // Add text for AttributeModifiers
            for (AttributeModifier attributeModifier : atts.values()) {
                String tag = attributeModifier.getName();
                switch (tag) {
                    case "extra_damage":
                        addMateriaTooltip(tips, tag, TextFormatting.DARK_RED);
                        break;
                    default:
                        break;
                }
            }
            // Add text for Tags
            for (String tag : tags.getAllKeys()) {
                switch (tag) {
                    case "easy_dodge":
                        addMateriaTooltip(tips, tag, tags.get(tag).getAsString(), TextFormatting.AQUA, TextFormatting.ITALIC);
                        break;
                    default:
                        break;
                }
            }
            // Add text for Affixes
            // Add a little separating line and text.
            if (!affixes.isEmpty()) {
                // Add empty line to split the affixes
                tips.add(new StringTextComponent(""));
                addTooltip(tips, "affix.list.tooltip", TextFormatting.UNDERLINE, TextFormatting.ITALIC);
            }
            for (Affix affix : affixes.keySet()) {
                String tag = affix.getRegistryName().toString();
                switch (tag) {
                    case "apotheosis:fire_damage":
                        addMateriaTooltip(tips, tag, TextFormatting.RED, TextFormatting.ITALIC);
                        break;
                    case "apotheosis:snipe_damage":
                        addMateriaTooltip(tips, tag, TextFormatting.YELLOW, TextFormatting.ITALIC);
                        break;

                    default:
                        break;
                }
            }

            e.getToolTip().addAll(1, tips);
        }
    }





    // Add non-materia-based tooltips.
    private static void addTooltip(List<ITextComponent> tips, String tag, TextFormatting ... format) {
        TranslationTextComponent t = new TranslationTextComponent(tag);
        for (TextFormatting f : format) t.withStyle(f);

        tips.add(t);
    }

    // Automatically format the tooltips' text. Four different overloaded methods for flexibility.
    private static void addMateriaTooltip(List<ITextComponent> tips, String tag) {
        String tooltip_text = "materia.".concat(tag).concat(".name");
        TranslationTextComponent t = new TranslationTextComponent(tooltip_text);
        tips.add(t);
    }
    private static void addMateriaTooltip(List<ITextComponent> tips, String tag, String tag_level) {
        String tooltip_text = "materia.".concat(tag).concat(".name");
        TranslationTextComponent t = new TranslationTextComponent(tooltip_text);
        t.append(" ".concat(tag_level));
        tips.add(t);
    }
    private static void addMateriaTooltip(List<ITextComponent> tips, String tag, TextFormatting ... format) {
        String tooltip_text = "materia.".concat(tag).concat(".name");
        TranslationTextComponent t = new TranslationTextComponent(tooltip_text);
        for (TextFormatting f : format) t.withStyle(f);

        tips.add(t);
    }
    private static void addMateriaTooltip(List<ITextComponent> tips, String tag, String tag_level, TextFormatting ... format) {
        String tooltip_text = "materia.".concat(tag).concat(".name");
        TranslationTextComponent t = new TranslationTextComponent(tooltip_text);
        t.append(" ".concat(tag_level));
        for (TextFormatting f : format) t.withStyle(f);

        tips.add(t);
    }
}


