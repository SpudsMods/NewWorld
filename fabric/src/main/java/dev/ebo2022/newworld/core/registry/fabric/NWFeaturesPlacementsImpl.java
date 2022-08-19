package dev.ebo2022.newworld.core.registry.fabric;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.function.Supplier;

public class NWFeaturesPlacementsImpl {
    public static Holder<ConfiguredFeature<?, ?>> configuredHolder(Supplier<ConfiguredFeature<?, ?>> feature) {
        return Holder.direct(feature.get());
    }
}
