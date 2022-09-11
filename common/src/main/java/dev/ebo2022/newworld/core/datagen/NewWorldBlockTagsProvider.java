package dev.ebo2022.newworld.core.datagen;

import dev.ebo2022.newworld.core.registry.NWBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterBlocks;
import gg.moonflower.carpenter.core.registry.CarpenterBlockTags;
import gg.moonflower.pollen.api.datagen.provider.tags.PollinatedBlockTagsProvider;
import gg.moonflower.pollen.api.util.PollinatedModContainer;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class NewWorldBlockTagsProvider extends PollinatedBlockTagsProvider {
    public NewWorldBlockTagsProvider(DataGenerator dataGenerator, PollinatedModContainer container) {
        super(dataGenerator, container);
    }

    @Override
    protected void addTags() {
        this.tag(CarpenterBlockTags.BOOKSHELVES).add(NWBlocks.FIR_BOOKSHELF.get());
        this.tag(CarpenterBlockTags.TRAPPED_CHESTS).add(NWBlocks.TRAPPED_FIR_CHEST.get());
        this.tag(CarpenterBlockTags.CHESTS).add(NWBlocks.FIR_CHEST.get());
    }
}