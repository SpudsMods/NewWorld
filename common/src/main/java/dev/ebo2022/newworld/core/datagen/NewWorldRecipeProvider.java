package dev.ebo2022.newworld.core.datagen;

import dev.ebo2022.newworld.core.registry.NWBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.pollen.api.datagen.provider.PollinatedRecipeProvider;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class NewWorldRecipeProvider extends PollinatedRecipeProvider {
    public NewWorldRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        createBookshelf(NWBlocks.FIR_BOOKSHELF.get(), NWBlocks.FIR_PLANKS.get(), consumer);
        createChest(CarpenterBlocks.OAK_CHEST.get(), Blocks.OAK_PLANKS, consumer);
        createTrappedChest(CarpenterBlocks.TRAPPED_OAK_CHEST.get(), CarpenterBlocks.OAK_CHEST.get(), consumer);
    }

    private static void createBookshelf(Block bookshelf, Block planks, Consumer<FinishedRecipe> saveConsumer) {
        ShapedRecipeBuilder.shaped(bookshelf)
                .group("bookshelves")
                .define('#', planks)
                .define('X', Items.BOOK)
                .pattern("###")
                .pattern("XXX")
                .pattern("###")
                .unlockedBy("has_book", has(Items.BOOK))
                .save(saveConsumer);
    }

    private static void createChest(Block chest, Block planks, Consumer<FinishedRecipe> saveConsumer) {
        ShapedRecipeBuilder.shaped(chest)
                .group("chests")
                .define('#', planks)
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0]))
                .save(saveConsumer);
    }

    private static void createTrappedChest(Block chest, Block from, Consumer<FinishedRecipe> saveConsumer) {
        ShapelessRecipeBuilder.shapeless(chest)
                .group("trapped_chests")
                .requires(from)
                .requires(Blocks.TRIPWIRE_HOOK)
                .unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK))
                .save(saveConsumer);
    }
}