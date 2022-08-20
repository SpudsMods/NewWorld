package dev.ebo2022.newworld.core.registry.fabric;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import java.util.function.Supplier;

public class NWFeaturesConfiguredImpl {

    public static Holder<PlacedFeature> getHolder(Supplier<PlacedFeature> feature) {
        return Holder.direct(feature.get());
    }
}
