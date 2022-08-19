package dev.ebo2022.newworld.core.registry;

import com.google.common.collect.ImmutableList;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.ebo2022.newworld.common.worldgen.feature.FallenLogFeature;
import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Supplier;

public class NWFeatures {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final PollinatedRegistry<Feature<?>> FEATURES = PollinatedRegistry.create(Registry.FEATURE, NewWorld.MOD_ID);

    public static final Supplier<Feature<BlockStateConfiguration>> FALLEN_LOG = FEATURES.register("fallen_log", () -> new FallenLogFeature(BlockStateConfiguration.CODEC));

    public static void load(Platform platform) {
        LOGGER.debug("Registered to platform");
        FEATURES.register(platform);
    }

    public static class Configured {

        private static final Logger LOGGER = LogManager.getLogger();
        public static final PollinatedRegistry<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = PollinatedRegistry.create(BuiltinRegistries.CONFIGURED_FEATURE, NewWorld.MOD_ID);
        public static final PollinatedRegistry<PlacedFeature> PLACEMENTS = PollinatedRegistry.create(BuiltinRegistries.PLACED_FEATURE, NewWorld.MOD_ID);

        private static final BeehiveDecorator BEES_05 = new BeehiveDecorator(1.0F);
        private static final BeehiveDecorator BEES_02 = new BeehiveDecorator(0.02F);
        public static List<Block> VALID_BERRY_BUSH_BLOCKS = List.of(Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT, Blocks.MOSS_BLOCK);
        public static List<Block> VALID_MOSS_CARPET_BLOCKS = List.of(Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT, Blocks.MOSS_BLOCK, Blocks.STONE, Blocks.COBBLESTONE, Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.MOSSY_COBBLESTONE);

        public static final Supplier<ConfiguredFeature<BlockStateConfiguration, ?>> FALLEN_FIR_LOG = CONFIGURED_FEATURES.register("fallen_fir_log", () -> new ConfiguredFeature<>(FALLEN_LOG.get(), new BlockStateConfiguration(NWBlocks.FIR_LOG.get().defaultBlockState())));
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> GROWN_FIR = CONFIGURED_FEATURES.register("grown_fir", () -> new ConfiguredFeature<>(Feature.TREE, grownFirConfig().build()));
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> GROWN_FIR_BEES_005 = CONFIGURED_FEATURES.register("grown_fir_bees_005", () -> new ConfiguredFeature<>(Feature.TREE, grownFirConfig().decorators(List.of(BEES_05)).build()));
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> GROWN_FIR_BEES_002 = CONFIGURED_FEATURES.register("grown_fir_bees_002", () -> new ConfiguredFeature<>(Feature.TREE, grownFirConfig().decorators(List.of(BEES_02)).build()));
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FIR = CONFIGURED_FEATURES.register("fir", () -> new ConfiguredFeature<>(Feature.TREE, naturalFirConfig().build()));
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FIR_BEES_005 = CONFIGURED_FEATURES.register("fir_bees_005", () -> new ConfiguredFeature<>(Feature.TREE, naturalFirConfig().decorators(List.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)), BEES_05)).build()));
        public static final Supplier<PlacedFeature> FIR_CHECKED = PLACEMENTS.register("fir_checked", () -> new PlacedFeature(Holder.direct(FIR.get()), List.of(PlacementUtils.filteredByBlockSurvival(NWBlocks.FIR_SAPLING.get()))));
        public static final Supplier<PlacedFeature> FIR_BEES_005_CHECKED = PLACEMENTS.register("fir_bees_005_checked", () -> new PlacedFeature(Holder.direct(FIR_BEES_005.get()), List.of(PlacementUtils.filteredByBlockSurvival(NWBlocks.FIR_SAPLING.get()))));

        public static final Supplier<ConfiguredFeature<RandomFeatureConfiguration, ?>> FIR_SPAWN = CONFIGURED_FEATURES.register("fir_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placementHolder(FIR_BEES_005_CHECKED), 0.06f)), placementHolder(FIR_CHECKED))));

        private static TreeConfiguration.TreeConfigurationBuilder naturalFirConfig() {
            return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(NWBlocks.FIR_LOG.get()), new StraightTrunkPlacer(6, 1, 2), BlockStateProvider.simple(NWBlocks.FIR_LEAVES.get()), new SpruceFoliagePlacer(UniformInt.of(1, 3), UniformInt.of(0, 1), UniformInt.of(3, 4)), new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).ignoreVines();
        }

        private static TreeConfiguration.TreeConfigurationBuilder grownFirConfig() {
            return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(NWBlocks.FIR_LOG.get()), new StraightTrunkPlacer(6, 1, 2), BlockStateProvider.simple(NWBlocks.FIR_LEAVES.get()), new SpruceFoliagePlacer(UniformInt.of(1, 3), UniformInt.of(0, 1), UniformInt.of(3, 4)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines();
        }

        // Keep forge from combusting because the placed feature isn't present
        @ExpectPlatform
        public static Holder<PlacedFeature> placementHolder(Supplier<PlacedFeature> feature) {
            return Platform.error();
        }

        public static void load(Platform platform) {
            LOGGER.debug("Registered to platform");
            CONFIGURED_FEATURES.register(platform);
            PLACEMENTS.register(platform);
        }
    }
}
