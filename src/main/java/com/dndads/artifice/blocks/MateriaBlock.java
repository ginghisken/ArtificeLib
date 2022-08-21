package com.dndads.artifice.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class MateriaBlock extends Block {
    
    public MateriaBlock() {
        super(AbstractBlock.Properties.of(Material.HEAVY_METAL)
                .strength(5.0f, 6.0f)
                .sound(SoundType.METAL)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops());
    }
}
