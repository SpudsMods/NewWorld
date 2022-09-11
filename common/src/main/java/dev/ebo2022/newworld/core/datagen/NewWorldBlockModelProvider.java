package dev.ebo2022.newworld.core.datagen;

import com.google.gson.JsonElement;
import dev.ebo2022.newworld.core.registry.NWBlocks;
import gg.moonflower.carpenter.core.Carpenter;
import gg.moonflower.pollen.api.datagen.provider.model.PollinatedBlockModelGenerator;
import net.minecraft.core.Registry;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NewWorldBlockModelProvider extends PollinatedBlockModelGenerator {
    public static final ModelTemplate CHEST_BASE_TEMPLATE = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_chest_base")), Optional.empty(), TextureSlot.TEXTURE);
    public static final ModelTemplate CHEST_BASE_LEFT_TEMPLATE = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_double_chest_left_base")), Optional.empty(), TextureSlot.TEXTURE);
    public static final ModelTemplate CHEST_BASE_RIGHT_TEMPLATE = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_double_chest_right_base")), Optional.empty(), TextureSlot.TEXTURE);

    public static final ModelTemplate CHEST_LID_TEMPLATE = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_chest_lid")), Optional.empty(), TextureSlot.TEXTURE);
    public static final ModelTemplate CHEST_LID_LEFT_TEMPLATE = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_double_chest_left_lid")), Optional.empty(), TextureSlot.TEXTURE);
    public static final ModelTemplate CHEST_LID_RIGHT_TEMPLATE = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_double_chest_right_lid")), Optional.empty(), TextureSlot.TEXTURE);

    public static final ModelTemplate CHEST_KNOB_TEMPLATE = new ModelTemplate(Optional.of(Carpenter.carpenter("block/template_chest_knob")), Optional.empty(), TextureSlot.TEXTURE);
    public static final ModelTemplate CHEST_ITEM_TEMPLATE = new ModelTemplate(Optional.of(new ResourceLocation("item/chest")), Optional.empty(), TextureSlot.PARTICLE);

    public NewWorldBlockModelProvider(Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput, Consumer<Item> skippedAutoModelsOutput) {
        super(blockStateOutput, modelOutput, skippedAutoModelsOutput);
    }

    @Override
    public void run() {
        this.createBookshelf(NWBlocks.FIR_BOOKSHELF.get(), NWBlocks.FIR_PLANKS.get());
        this.createChest(NWBlocks.FIR_CHEST.get(), NWBlocks.FIR_PLANKS.get());
        this.createDefaultChestKnob(NWBlocks.FIR_CHEST.get());
        this.createTrappedChest(NWBlocks.TRAPPED_FIR_CHEST.get(), NWBlocks.FIR_PLANKS.get());
    }

    private void createBookshelf(Block bookshelf, Block planks) {
        TextureMapping textureMapping = TextureMapping.column(TextureMapping.getBlockTexture(bookshelf), TextureMapping.getBlockTexture(planks));
        ResourceLocation resourceLocation = ModelTemplates.CUBE_COLUMN.create(bookshelf, textureMapping, this.getModelOutput());
        this.getBlockStateOutput().accept(createSimpleBlock(bookshelf, resourceLocation));
    }

    private void createChest(Block chest, Block planks) {
        BlockEntityModelGenerator generator = new BlockEntityModelGenerator(ModelLocationUtils.getModelLocation(chest), planks);
        generator.createWithoutBlockItem(chest);

        CHEST_ITEM_TEMPLATE.create(ModelLocationUtils.getModelLocation(chest.asItem()), TextureMapping.particle(planks), this.getModelOutput());

        createChestBase(chest);
        createChestLid(chest);
    }

    private void createTrappedChest(Block chest, Block planks) {
        BlockEntityModelGenerator generator = new BlockEntityModelGenerator(ModelLocationUtils.getModelLocation(chest), planks);
        generator.createWithoutBlockItem(chest);

        CHEST_ITEM_TEMPLATE.create(ModelLocationUtils.getModelLocation(chest.asItem()), TextureMapping.particle(planks), this.getModelOutput());
    }

    public ResourceLocation createWithSuffix(ModelTemplate template, Block modelBlock, String modelLocationSuffix, TextureMapping textureMapping, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        ResourceLocation resourceLocation = Registry.BLOCK.getKey(modelBlock);
        String namespace = resourceLocation.getNamespace();
        String path = resourceLocation.getPath();

        return template.create(new ResourceLocation(namespace, "block/" + path + "/" + path + modelLocationSuffix), textureMapping, modelOutput);
    }

    public void createChestBase(Block chest) {
        createWithSuffix(CHEST_BASE_TEMPLATE, chest, "_base", TextureMapping.defaultTexture(chest), getModelOutput());
        final ResourceLocation doubleMapping = TextureMapping.getBlockTexture(chest, "_double");
        createWithSuffix(CHEST_BASE_LEFT_TEMPLATE, chest, "_base_left", TextureMapping.defaultTexture(doubleMapping), getModelOutput());
        createWithSuffix(CHEST_BASE_RIGHT_TEMPLATE, chest, "_base_right", TextureMapping.defaultTexture(doubleMapping), getModelOutput());
    }


    public void createChestLid(Block chest) {
        createWithSuffix(CHEST_LID_TEMPLATE, chest, "_lid", TextureMapping.defaultTexture(chest), getModelOutput());
        final ResourceLocation doubleMapping = TextureMapping.getBlockTexture(chest, "_double");
        createWithSuffix(CHEST_LID_LEFT_TEMPLATE, chest, "_lid_left", TextureMapping.defaultTexture(doubleMapping), getModelOutput());
        createWithSuffix(CHEST_LID_RIGHT_TEMPLATE, chest, "_lid_right", TextureMapping.defaultTexture(doubleMapping), getModelOutput());
    }

    public void createDefaultChestKnob(Block chest) {
        createWithSuffix(CHEST_KNOB_TEMPLATE, chest, "_knob", TextureMapping.defaultTexture(chest), getModelOutput());
    }
}