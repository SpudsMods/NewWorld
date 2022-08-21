package dev.ebo2022.newworld.core.fabric;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {

    public static void registerBiomes() {
        register(TestBiomes.HOT_RED, TestOverworldBiomes.hotRed());
        register(TestBiomes.COLD_BLUE, TestOverworldBiomes.coldBlue());
    }

    private static void register(ResourceKey<Biome> key, Biome biome) {
        BuiltinRegistries.register(BuiltinRegistries.BIOME, key, biome);
    }
}