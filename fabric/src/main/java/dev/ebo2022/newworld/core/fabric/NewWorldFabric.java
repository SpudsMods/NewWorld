package dev.ebo2022.newworld.core.fabric;

import dev.ebo2022.newworld.core.NewWorld;
import dev.ebo2022.newworld.core.NewWorldConfig;
import dev.ebo2022.newworld.core.registry.NWFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class NewWorldFabric implements ModInitializer, TerraBlenderApi {

    @Override
    public void onInitialize() {
        NewWorld.PLATFORM.setup();
        final NewWorldConfig.Server config = NewWorld.SERVER_CONFIG;

        if (config.firsInMeadow.get())
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.MEADOW), GenerationStep.Decoration.VEGETAL_DECORATION, NWFeatures.Configured.TREES_FIR_MEADOW_KEY);

        if (config.firsInTaiga.get())
            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_TAIGA), GenerationStep.Decoration.VEGETAL_DECORATION, NWFeatures.Configured.TREES_FIR_SCARCE_KEY);
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new NWRegion(NewWorld.location("overworld"), 2));
    }
}
