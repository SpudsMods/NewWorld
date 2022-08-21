package dev.ebo2022.newworld.core.fabric;

import dev.ebo2022.newworld.core.NewWorld;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class TestBiomes {
    public static final ResourceKey<Biome> HOT_RED = register("hot_red");
    public static final ResourceKey<Biome> COLD_BLUE = register("cold_blue");

    private static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(NewWorld.MOD_ID, name));
    }
}
