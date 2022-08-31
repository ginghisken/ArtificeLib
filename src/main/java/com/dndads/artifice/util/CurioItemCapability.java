package com.dndads.artifice.util;


import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// Ripped from Curio.
// https://github.com/TheIllusiveC4/Curios/blob/1.16.x-forge/src/main/java/top/theillusivec4/curios/common/capability/CurioItemCapability.java
public class CurioItemCapability {

    public static ICapabilityProvider createProvider(final ICurio curio) {
        return new Provider(curio);
    }

    private static class CurioItemWrapper implements ICurio {

    }

    public static class Provider implements ICapabilityProvider {
        final LazyOptional<ICurio> capability;

        Provider(ICurio curio) {
            this.capability = LazyOptional.of(() -> curio);
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return CuriosCapability.ITEM.orEmpty(cap, this.capability);
        }
    }
}
