package dev.ebo2022.newworld.core.datagen;

import dev.ebo2022.newworld.core.registry.NWBlocks;
import gg.moonflower.pollen.api.datagen.provider.loot_table.PollinatedBlockLootGenerator;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class NewWorldBlockLootGenerator extends PollinatedBlockLootGenerator {
    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(
            ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)))
    );

    public NewWorldBlockLootGenerator(PollinatedModContainer container) {
        super(container);
    }

    @Override
    protected void run() {
        this.bookshelf(NWBlocks.FIR_BOOKSHELF.get());
        this.dropSelf(NWBlocks.FIR_CHEST.get());
        this.dropSelf(NWBlocks.TRAPPED_FIR_CHEST.get());
    }

    private void bookshelf(Block bookshelf) {
        this.add(bookshelf, block -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))));
    }
}