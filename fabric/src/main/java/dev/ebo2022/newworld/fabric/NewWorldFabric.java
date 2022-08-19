package dev.ebo2022.newworld.fabric;

import dev.ebo2022.newworld.core.NewWorld;
import dev.ebo2022.newworld.core.registry.NWFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class NewWorldFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NewWorld.PLATFORM.setup();
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.BiomeCategory.TAIGA), GenerationStep.Decoration.VEGETAL_DECORATION, NWFeatures.Configured.TREES_FIR_SCARCE_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.MEADOW), GenerationStep.Decoration.VEGETAL_DECORATION, NWFeatures.Configured.TREES_FIR_MEADOW_KEY);
    }
}
