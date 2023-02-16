package dev.ebo2022.newworld.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.ebo2022.newworld.common.levelgen.feature.FallenLogFeature;
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
import net.minecraft.resources.ResourceKey;
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

    public static final PollinatedRegistry<Feature<?>> FEATURES = PollinatedRegistry.create(Registry.FEATURE, NewWorld.MOD_ID);

    public static final Supplier<Feature<BlockStateConfiguration>> FALLEN_LOG = FEATURES.register("fallen_log", () -> new FallenLogFeature(BlockStateConfiguration.CODEC));

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
        public static final Supplier<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_BERRY_BUSH_WOODED_MEADOW = CONFIGURED_FEATURES.register("patch_berry_bush_wooded_meadow", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SWEET_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 2))), VALID_BERRY_BUSH_BLOCKS, 60)));
        public static final Supplier<ConfiguredFeature<RandomPatchConfiguration, ?>> MOSS_CARPET_BUSH_WOODED_MEADOW = CONFIGURED_FEATURES.register("moss_carpet_bush_wooded_meadow", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.MOSS_CARPET.defaultBlockState())), VALID_MOSS_CARPET_BLOCKS, 60)));
        public static final Supplier<ConfiguredFeature<GlowLichenConfiguration, ?>> GLOW_LICHEN_WOODED_MEADOW = CONFIGURED_FEATURES.register("glow_lichen_wooded_meadow", () -> new ConfiguredFeature<>(Feature.GLOW_LICHEN, new GlowLichenConfiguration(20, true, true, true, 0.5f, HolderSet.direct(Block::builtInRegistryHolder, NWBlocks.FIR_LOG.get(), Blocks.MOSSY_COBBLESTONE))));
        public static final Supplier<PlacedFeature> FIR_CHECKED = PLACEMENTS.register("fir_checked", () -> new PlacedFeature(Holder.direct(FIR.get()), List.of(PlacementUtils.filteredByBlockSurvival(NWBlocks.FIR_SAPLING.get()))));
        public static final Supplier<PlacedFeature> FIR_BEES_005_CHECKED = PLACEMENTS.register("fir_bees_005_checked", () -> new PlacedFeature(Holder.direct(FIR_BEES_005.get()), List.of(PlacementUtils.filteredByBlockSurvival(NWBlocks.FIR_SAPLING.get()))));
        public static final Supplier<PlacedFeature> GROWN_FIR_CHECKED = PLACEMENTS.register("grown_fir_checked", () -> new PlacedFeature(Holder.direct(GROWN_FIR.get()), List.of(PlacementUtils.filteredByBlockSurvival(NWBlocks.FIR_SAPLING.get()))));
        public static final Supplier<PlacedFeature> GROWN_FIR_BEES_005_CHECKED = PLACEMENTS.register("grown_fir_bees_005_checked", () -> new PlacedFeature(Holder.direct(GROWN_FIR_BEES_005.get()), List.of(PlacementUtils.filteredByBlockSurvival(NWBlocks.FIR_SAPLING.get()))));
        public static final Supplier<ConfiguredFeature<RandomFeatureConfiguration, ?>> FIR_SPAWN = CONFIGURED_FEATURES.register("fir_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(getHolder(FIR_BEES_005_CHECKED, "fir_bees_005_checked"), 0.06f)), getHolder(FIR_CHECKED, "fir_checked"))));
        public static final Supplier<ConfiguredFeature<RandomFeatureConfiguration, ?>> FIR_TAIGA = CONFIGURED_FEATURES.register("fir_taiga", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(getHolder(GROWN_FIR_BEES_005_CHECKED, "grown_fir_bees_005_checked"), 0.0f)), getHolder(GROWN_FIR_CHECKED, "grown_fir_checked"))));
        public static final Supplier<ConfiguredFeature<RandomFeatureConfiguration, ?>> FIR_MEADOW = CONFIGURED_FEATURES.register("fir_meadow", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(getHolder(GROWN_FIR_BEES_005_CHECKED, "grown_fir_bees_005_checked"), 1.0f)), getHolder(GROWN_FIR_CHECKED, "grown_fir_checked"))));

        public static final Supplier<PlacedFeature> TREES_FIR = PLACEMENTS.register("trees_fir", () -> new PlacedFeature(Holder.direct(FIR_SPAWN.get()), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 5))));

        public static final Supplier<PlacedFeature> TREES_FIR_SCARCE = PLACEMENTS.register("trees_fir_scarce", () -> new PlacedFeature(Holder.direct(FIR_TAIGA.get()), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(1))));
        public static final ResourceKey<PlacedFeature> TREES_FIR_SCARCE_KEY = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, NewWorld.location("trees_fir_scarce"));
        public static final Supplier<PlacedFeature> TREES_FIR_MEADOW = PLACEMENTS.register("trees_fir_meadow", () -> new PlacedFeature(Holder.direct(FIR_MEADOW.get()), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(40))));
        public static final ResourceKey<PlacedFeature> TREES_FIR_MEADOW_KEY = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, NewWorld.location("trees_fir_meadow"));
        public static final Supplier<PlacedFeature> FALLEN_FIR_LOG_PLACEMENT = PLACEMENTS.register("fallen_fir_log", () -> new PlacedFeature(Holder.direct(FALLEN_FIR_LOG.get()), List.of(RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        public static final Supplier<PlacedFeature> PATCH_BERRY_WOODED_MEADOW_PLACEMENT = PLACEMENTS.register("patch_berry_wooded_meadow", () -> new PlacedFeature(Holder.direct(PATCH_BERRY_BUSH_WOODED_MEADOW.get()), List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        public static final ResourceKey<PlacedFeature> PATCH_BERRY_KEY = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, NewWorld.location("patch_berry_wooded_meadow"));
        public static final Supplier<PlacedFeature> MOSS_CARPET_WOODED_MEADOW_PLACEMENT = PLACEMENTS.register("moss_carpet_wooded_meadow", () -> new PlacedFeature(Holder.direct(MOSS_CARPET_BUSH_WOODED_MEADOW.get()), List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        public static final Supplier<PlacedFeature> GLOW_LICHEN_WOODED_MEADOW_PLACEMENT = PLACEMENTS.register("glow_lichen_wooded_meadow", () -> new PlacedFeature(Holder.direct(GLOW_LICHEN_WOODED_MEADOW.get()), List.of(CountPlacement.of(UniformInt.of(104, 157)), PlacementUtils.FULL_RANGE, RarityFilter.onAverageOnceEvery(2), SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, 30, 300), BiomeFilter.biome())));

        private static TreeConfiguration.TreeConfigurationBuilder naturalFirConfig() {
            return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(NWBlocks.FIR_LOG.get()), new StraightTrunkPlacer(6, 1, 2), BlockStateProvider.simple(NWBlocks.FIR_LEAVES.get()), new SpruceFoliagePlacer(UniformInt.of(1, 3), UniformInt.of(0, 1), UniformInt.of(3, 4)), new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).ignoreVines();
        }

        private static TreeConfiguration.TreeConfigurationBuilder grownFirConfig() {
            return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(NWBlocks.FIR_LOG.get()), new StraightTrunkPlacer(6, 1, 2), BlockStateProvider.simple(NWBlocks.FIR_LEAVES.get()), new SpruceFoliagePlacer(UniformInt.of(1, 3), UniformInt.of(0, 1), UniformInt.of(3, 4)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines();
        }

        // TODO: use registry values when Pollen updates. Classes need to stay the same for features to work
        @ExpectPlatform
        public static Holder<PlacedFeature> getHolder(Supplier<PlacedFeature> feature, String name) {
            return Platform.error();
        }
    }
}
