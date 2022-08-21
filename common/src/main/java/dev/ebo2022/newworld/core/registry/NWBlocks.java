package dev.ebo2022.newworld.core.registry;

import com.mojang.datafixers.util.Pair;
import dev.ebo2022.newworld.common.block.grower.FirTreeGrower;
import dev.ebo2022.newworld.common.item.TabInsertBlockItem;
import dev.ebo2022.newworld.core.registry.util.Woodset;
import gg.moonflower.pollen.api.block.PollinatedStandingSignBlock;
import gg.moonflower.pollen.api.block.PollinatedWallSignBlock;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class NWBlocks {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final PollinatedBlockRegistry BLOCKS = PollinatedRegistry.createBlock(NWItems.ITEMS);
    private static final Woodset FIR = new Woodset(MaterialColor.DEEPSLATE, MaterialColor.COLOR_BROWN);

    public static final Supplier<Block> STRIPPED_FIR_LOG = BLOCKS.registerWithItem("stripped_fir_log", FIR::stripped_log, followItem(Items.STRIPPED_WARPED_STEM, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Block> STRIPPED_FIR_WOOD = BLOCKS.registerWithItem("stripped_fir_wood", FIR::stripped_wood, followItem(Items.STRIPPED_WARPED_HYPHAE, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Block> FIR_LOG = BLOCKS.registerWithItem("fir_log", FIR::log, followItem(Items.WARPED_STEM, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Block> FIR_WOOD = BLOCKS.registerWithItem("fir_wood", FIR::wood, followItem(Items.WARPED_HYPHAE, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Block> FIR_PLANKS = BLOCKS.registerWithItem("fir_planks", FIR::planks, followItem(Items.WARPED_PLANKS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Block> FIR_SLAB = BLOCKS.registerWithItem("fir_slab", FIR::slab, followItem(Items.WARPED_SLAB, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Block> FIR_STAIRS = BLOCKS.registerWithItem("fir_stairs", () -> FIR.stairs(FIR_PLANKS), followItem(Items.WARPED_STAIRS, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Block> FIR_PRESSURE_PLATE = BLOCKS.registerWithItem("fir_pressure_plate", FIR::pressurePlate, followItem(Items.WARPED_PRESSURE_PLATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final Supplier<Block> FIR_BUTTON = BLOCKS.registerWithItem("fir_button", FIR::button, followItem(Items.WARPED_BUTTON, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final Supplier<Block> FIR_FENCE = BLOCKS.registerWithItem("fir_fence", FIR::fence, followItem(Items.WARPED_FENCE, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Block> FIR_FENCE_GATE = BLOCKS.registerWithItem("fir_fence_gate", FIR::fenceGate, followItem(Items.WARPED_FENCE_GATE, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final Supplier<Block> FIR_DOOR = BLOCKS.registerWithItem("fir_door", FIR::door, followItem(Items.WARPED_DOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final Supplier<Block> FIR_TRAPDOOR = BLOCKS.registerWithItem("fir_trapdoor", FIR::trapdoor, followItem(Items.WARPED_TRAPDOOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final Pair<Supplier<PollinatedStandingSignBlock>, Supplier<PollinatedWallSignBlock>> FIR_SIGN = BLOCKS.registerSign("fir", Material.WOOD, MaterialColor.COLOR_BROWN);
    public static final Supplier<Block> FIR_LEAVES = BLOCKS.registerWithItem("fir_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)), followItem(Items.FLOWERING_AZALEA_LEAVES, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Block> FIR_SAPLING = BLOCKS.registerWithItem("fir_sapling", () -> new SaplingBlock(new FirTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), followItem(Items.DARK_OAK_SAPLING, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Block> POTTED_FIR_SAPLING = BLOCKS.register("potted_fir_sapling", createFlowerPot(FIR_SAPLING));

    public static final Supplier<Block> FIR_BOOKSHELF = BLOCKS.registerWithItem("fir_bookshelf", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).sound(SoundType.WOOD).color(MaterialColor.COLOR_BROWN)), new Item.Properties().tab(Platform.isModLoaded("charm") ? CreativeModeTab.TAB_DECORATIONS : null));

    private static Supplier<Block> createFlowerPot(Supplier<Block> block) {
        return () -> new FlowerPotBlock(block.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM));
    }

    private static Function<Block, Item> followItem(Item insertAfter, Item.Properties properties) {
        return object -> new TabInsertBlockItem(insertAfter, object, properties);
    }

    public static void load(Platform platform) {
        LOGGER.debug("Registered to platform");
        BLOCKS.register(platform);
    }
}
