package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.common.item.TabInsertBlockItem;
import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.carpenter.common.block.CarpenterBookshelfBlock;
import gg.moonflower.carpenter.common.block.CarpenterChestBlock;
import gg.moonflower.carpenter.common.block.CarpenterTrappedChestBlock;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterChestType;
import gg.moonflower.carpenter.core.registry.CarpenterChests;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Objects;
import java.util.function.Supplier;

public class NWCarpenterCompat {

    public static final PollinatedRegistry<CarpenterChestType> CHEST_TYPES = PollinatedRegistry.create(CarpenterChests.REGISTRY, NewWorld.MOD_ID);

    public static final Supplier<CarpenterChestType> FIR_CHEST_TYPE = CHEST_TYPES.register("fir_chest", () -> new CarpenterChestType(
            NewWorld.location("block/fir_chest/fir_chest_base"),
            NewWorld.location("block/fir_chest/fir_chest_base_left"),
            NewWorld.location("block/fir_chest/fir_chest_base_right"),
            NewWorld.location("block/fir_chest/fir_chest_lid"),
            NewWorld.location("block/fir_chest/fir_chest_lid_left"),
            NewWorld.location("block/fir_chest/fir_chest_lid_right"),
            NewWorld.location("block/fir_chest/fir_chest_knob")
    ));

    public static final Supplier<Block> FIR_BOOKSHELF = NWBlocks.BLOCKS.registerWithItem("fir_bookshelf", () -> new CarpenterBookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)), (block) -> new TabInsertBlockItem(block, Items.BOOKSHELF, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final Supplier<Block> FIR_CHEST = NWBlocks.BLOCKS.registerWithItem("fir_chest", () -> new CarpenterChestBlock(FIR_CHEST_TYPE, BlockBehaviour.Properties.copy(Blocks.CHEST), () -> CarpenterBlocks.CARPENTER_CHEST_BE.get()), (block) -> new TabInsertBlockItem(block, Items.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final Supplier<Block> FIR_TRAPPED_CHEST = NWBlocks.BLOCKS.registerWithItem("trapped_fir_chest", () -> new CarpenterTrappedChestBlock(FIR_CHEST_TYPE, BlockBehaviour.Properties.copy(Blocks.TRAPPED_CHEST), () -> CarpenterBlocks.CARPENTER_TRAPPED_CHEST_BE.get()), (block) -> new TabInsertBlockItem(block, Items.TRAPPED_CHEST, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    private static <T> T create(Supplier<T> factory) {
        return !isAbleToLoad() ? null : factory.get();
    }

    private static boolean isAbleToLoad() {
        try {
            Class.forName("gg.moonflower.carpenter.core.Carpenter", false, NWCarpenterCompat.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }
}
