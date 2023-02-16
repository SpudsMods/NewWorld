package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.core.NewWorld;
import dev.ebo2022.newworld.core.other.NWGeneration;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class NWBiomes {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final PollinatedRegistry<Biome> BIOMES = PollinatedRegistry.create(BuiltinRegistries.BIOME, NewWorld.MOD_ID);

    public static final ResourceKey<Biome> WOODED_MEADOW = createBiome("wooded_meadow", NWGeneration::woodedMeadow);

    public static ResourceKey<Biome> createBiome(String name, Supplier<Biome> biomeSupplier) {
        ResourceLocation id = NewWorld.location(name);
        BIOMES.register(name, biomeSupplier);
        return ResourceKey.create(Registry.BIOME_REGISTRY, id);
    }
}
