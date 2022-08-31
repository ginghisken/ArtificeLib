package com.dndads.artifice.util;

import com.dndads.artifice.Artifice;
import com.dndads.artifice.items.jobstone.CharmTestJobstone;
import com.dndads.artifice.items.jobstone.JobstoneBase;
import com.dndads.artifice.items.materia.AttackMateria;
import com.dndads.artifice.blocks.BlockItemBase;
import com.dndads.artifice.blocks.MateriaBlock;
import com.dndads.artifice.items.ItemBase;
import com.dndads.artifice.items.materia.Materia;
import com.dndads.artifice.items.materia.MateriaBase;
import com.dndads.artifice.tools.ArtificeItemTier;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Artifice.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Artifice.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Items
    // Materia
    public static final RegistryObject<Materia> MATERIA = ITEMS.register("materia", Materia::new);
    public static final RegistryObject<Item> MATERIA2 = ITEMS.register("materia2", ItemBase::new);

    public static final RegistryObject<MateriaBase> MATERIA_ATTACK = ITEMS.register("materia_attack", AttackMateria::new);
    // Jobstones
    public static final RegistryObject<JobstoneBase> JOBSTONE_TEST = ITEMS.register("jobstone_test", CharmTestJobstone::new);

    // Tools
    public static final RegistryObject<SwordItem> MATERIA_SWORD = ITEMS.register("materia_sword", () ->
            new SwordItem(ArtificeItemTier.MATERIA,2, -2.4F, new Item.Properties().tab(Artifice.ARTIFICE_GROUP)));

    public static final RegistryObject<PickaxeItem> MATERIA_PICKAXE = ITEMS.register("materia_pickaxe", () ->
            new PickaxeItem(ArtificeItemTier.MATERIA,0, -2.8F, new Item.Properties().tab(Artifice.ARTIFICE_GROUP)));

    // Blocks
    public static final RegistryObject<Block> MATERIA_BLOCK = BLOCKS.register("materia_block", MateriaBlock::new);

    // Block Items
    public static final RegistryObject<Item> MATERIA_BLOCK_ITEM = ITEMS.register("materia_block", () -> new BlockItemBase(MATERIA_BLOCK.get()));


}
