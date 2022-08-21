package dev.ebo2022.newworld.core.forge;

import dev.ebo2022.newworld.core.NewWorld;
import dev.ebo2022.newworld.core.NewWorldConfig;
import dev.ebo2022.newworld.core.registry.NWFeatures;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NewWorld.MOD_ID)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void onEvent(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        final NewWorldConfig.Server config = NewWorld.SERVER_CONFIG;

        if (config.firsInTaiga.get() && event.getCategory() == Biome.BiomeCategory.TAIGA)
            builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(NWFeatures.Configured.TREES_FIR_SCARCE.get()));

        if (config.firsInMeadow.get() && matchesKeys(event.getName(), Biomes.MEADOW))
            builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(NWFeatures.Configured.TREES_FIR_MEADOW.get()));
    }

    public static boolean matchesKeys(ResourceLocation location, ResourceKey<?>... keys) {
        for (ResourceKey<?> key : keys)
            if (key.location().equals(location))
                return true;
        return false;
    }
}
