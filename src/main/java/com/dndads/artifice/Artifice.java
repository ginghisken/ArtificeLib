package com.dndads.artifice;

import com.dndads.artifice.config.ConfigHandler;
import com.dndads.artifice.event.EventHandler;
import com.dndads.artifice.event.MateriaHandler;
import com.dndads.artifice.event.WeaponHandler;
import com.dndads.artifice.items.jobstone.JobstoneScout;
import com.dndads.artifice.util.RegistryHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod("artifice")
public class Artifice
{
    // Norp was here but he was cooler
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "artifice";
    //Orie was here >:)

    public Artifice() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        RegistryHandler.init();

        MinecraftForge.EVENT_BUS.register(this);
        // Event handlers
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
        MinecraftForge.EVENT_BUS.register(WeaponHandler.class);
        MinecraftForge.EVENT_BUS.register(MateriaHandler.class);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.SPEC, "artifice-config.toml");
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Create the curio slots this mod wants the player to have.
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("ring").size(2).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("charm").size(1).build());
    }

    private void doClientStuff(final FMLClientSetupEvent event) { }

    public static final ItemGroup ARTIFICE_GROUP = new ItemGroup("artificeTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RegistryHandler.MATERIA.get());
        }
    };
}
