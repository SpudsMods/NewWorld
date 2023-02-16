package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.common.item.TabInsertBlockItem;
import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.carpenter.api.v1.registry.ChestRegistry;
import gg.moonflower.carpenter.common.block.CarpenterBookshelfBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Objects;
import java.util.function.Supplier;

public class NWChests {

    public static final ChestRegistry CHESTS = NewWorld.carpenterOnly(() -> ChestRegistry.get(NewWorld.MOD_ID));

    public static final Supplier<Block> FIR_CHEST = NewWorld.carpenterOnly(() -> Objects.requireNonNull(CHESTS).registerChest("fir_chest"));
    public static final Supplier<Block> TRAPPED_FIR_CHEST = NewWorld.carpenterOnly(() -> Objects.requireNonNull(CHESTS).registerTrappedChest("fir_chest"));
    public static final Supplier<Block> FIR_BOOKSHELF = NewWorld.carpenterOnly(() -> NWBlocks.BLOCKS.registerWithItem("fir_bookshelf", () -> new CarpenterBookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)), (block) -> new TabInsertBlockItem(Items.BOOKSHELF, block, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS))));

    private static Supplier<Block> registerBookshelf(String wood) {
        return NWBlocks.BLOCKS.registerWithItem(wood + "_bookshelf", () -> new CarpenterBookshelfBlock(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)), (block) -> new TabInsertBlockItem(Items.BOOKSHELF, block, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    }
}
