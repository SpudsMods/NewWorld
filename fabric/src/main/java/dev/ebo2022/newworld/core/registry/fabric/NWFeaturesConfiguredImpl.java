package dev.ebo2022.newworld.core.registry.fabric;

import dev.ebo2022.newworld.core.NewWorld;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import java.util.function.Supplier;

public class NWFeaturesConfiguredImpl {

    public static Holder<PlacedFeature> getHolder(Supplier<PlacedFeature> feature, String name) {
        final ResourceKey<PlacedFeature> key = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, NewWorld.location(name));
        return BuiltinRegistries.PLACED_FEATURE.getOrCreateHolder(key);
    }
}
