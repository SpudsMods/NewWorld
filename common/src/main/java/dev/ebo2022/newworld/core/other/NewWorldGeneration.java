package dev.ebo2022.newworld.core.other;

import dev.ebo2022.newworld.core.registry.NWFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.Nullable;

public class NewWorldGeneration {

    protected static int calculateSkyColor(float f) {
        float g = f / 3.0F;
        g = Mth.clamp(g, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - g * 0.05F, 0.5F + g * 0.1F, 1.0F);
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, MobSpawnSettings.Builder spawnSettings, BiomeGenerationSettings.Builder generationSettings, @Nullable Music music) {
        return biome(precipitation, category, temperature, downfall, 4159204, spawnSettings, generationSettings, music);
    }
    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, MobSpawnSettings.Builder spawnSettings, BiomeGenerationSettings.Builder generationSettings, @Nullable Music music) {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    public static Biome woodedMeadow() {
        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder();
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 2));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 2, 6));
        spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 2, 2, 4));
        BiomeDefaultFeatures.commonSpawns(spawns);

        globalOverworldGeneration(gen);
        withWoodedMeadowFoliage(gen);
        BiomeDefaultFeatures.addForestFlowers(gen);
        BiomeDefaultFeatures.addWarmFlowers(gen);
        gen.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.FOREST_ROCK);
        gen.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, NWFeatures.Configured.getHolder(NWFeatures.Configured.FALLEN_FIR_LOG_PLACEMENT));

        BiomeDefaultFeatures.addPlainGrass(gen);
        BiomeDefaultFeatures.addDefaultOres(gen);
        BiomeDefaultFeatures.addDefaultSoftDisks(gen);
        BiomeDefaultFeatures.addMeadowVegetation(gen);
        BiomeDefaultFeatures.addExtraEmeralds(gen);
        BiomeDefaultFeatures.addInfestedStone(gen);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_MEADOW);
        return biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.FOREST, 0.5F, 0.8F, 937679, spawns, gen, music);
    }

    public static void withWoodedMeadowFoliage(BiomeGenerationSettings.Builder gen) {
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NWFeatures.Configured.getHolder(NWFeatures.Configured.TREES_FIR));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NWFeatures.Configured.getHolder(NWFeatures.Configured.GLOW_LICHEN_WOODED_MEADOW_PLACEMENT));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NWFeatures.Configured.getHolder(NWFeatures.Configured.PATCH_BERRY_WOODED_MEADOW_PLACEMENT));
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NWFeatures.Configured.getHolder(NWFeatures.Configured.MOSS_CARPET_WOODED_MEADOW_PLACEMENT));
    }
}
