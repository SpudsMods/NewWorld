package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.common.levelgen.structure.BuriedBunkerFeature;
import dev.ebo2022.newworld.core.NewWorld;
import dev.ebo2022.newworld.core.mixin.StructureFeatureAccessor;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class NWStructures {

    public static final PollinatedRegistry<StructureFeature<?>> STRUCTURES = PollinatedRegistry.create(Registry.STRUCTURE_FEATURE, NewWorld.MOD_ID);

    public static final Supplier<StructureFeature<?>> BURIED_BUNKER = STRUCTURES.register("buried_bunker", BuriedBunkerFeature::new);

    public static void postLoad() {
        StructureFeatureAccessor.getSTEP().put(BURIED_BUNKER.get(), GenerationStep.Decoration.SURFACE_STRUCTURES);
    }
}
