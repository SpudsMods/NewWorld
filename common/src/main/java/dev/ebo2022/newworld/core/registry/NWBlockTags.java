package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.pollen.api.registry.resource.TagRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class NWBlockTags {
    public static final TagKey<Block> MINEABLE_WITH_MATTOCK = TagRegistry.bindBlock(NewWorld.location("mineable/mattock"));
}
