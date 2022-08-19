package dev.ebo2022.newworld.core.registry.forge;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class NWFeaturesPlacementsImpl {
    public static Holder<ConfiguredFeature<?, ?>> configuredHolder(Supplier<ConfiguredFeature<?, ?>> feature) {
        return ((RegistryObject<ConfiguredFeature<? ,?>>) feature).getHolder().get();
    }
}
