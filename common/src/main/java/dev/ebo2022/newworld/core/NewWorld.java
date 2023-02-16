package dev.ebo2022.newworld.core;


import dev.ebo2022.newworld.core.other.NWClientEvents;
import dev.ebo2022.newworld.core.registry.*;
import gg.moonflower.carpenter.core.registry.CarpenterChests;
import gg.moonflower.pollen.api.config.ConfigManager;
import gg.moonflower.pollen.api.config.PollinatedConfigType;
import gg.moonflower.pollen.api.event.events.client.render.FogEvents;
import gg.moonflower.pollen.api.event.events.entity.ModifyTradesEvents;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.StrippingRegistry;
import gg.moonflower.pollen.api.registry.client.RenderTypeRegistry;
import gg.moonflower.pollen.api.registry.content.CompostablesRegistry;
import gg.moonflower.pollen.api.registry.content.FlammabilityRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class NewWorld {

    public static final String MOD_ID = "newworld";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final NewWorldConfig.Server SERVER_CONFIG = ConfigManager.register(MOD_ID, PollinatedConfigType.SERVER, NewWorldConfig.Server::new);
    public static final NewWorldConfig.Client CLIENT_CONFIG = ConfigManager.register(MOD_ID, PollinatedConfigType.CLIENT, NewWorldConfig.Client::new);
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> NewWorld::onClient)
            .clientPostInit(() -> NewWorld::onClientPost)
            .commonInit(NewWorld::onCommon)
            .commonPostInit(NewWorld::onCommonPost)
            .build();

    public static void onClient() {
        FogEvents.FOG_DENSITY.register(NWClientEvents::mistyMeadow);
    }

    public static void onClientPost(Platform.ModSetupContext ctx) {
        RenderTypeRegistry.register(NWBlocks.FIR_SAPLING.get(), RenderType.cutout());
        RenderTypeRegistry.register(NWBlocks.POTTED_FIR_SAPLING.get(), RenderType.cutout());
    }

    public static void onCommon() {
        NWBlocks.BLOCKS.register(PLATFORM);
        try {
            Class.forName("gg.moonflower.carpenter.core.Carpenter", false, NewWorld.class.getClassLoader());
            Objects.requireNonNull(NWChests.CHESTS).register();
        } catch (ClassNotFoundException | NullPointerException ignored) {
        }
        NWBoatTypes.BOAT_TYPES.register(PLATFORM);
        NWItems.ITEMS.register(PLATFORM);
        NWFeatures.FEATURES.register(PLATFORM);
        NWFeatures.Configured.CONFIGURED_FEATURES.register(PLATFORM);
        NWFeatures.Configured.PLACEMENTS.register(PLATFORM);
        NWBiomes.BIOMES.register(PLATFORM);
        NWStructures.STRUCTURES.register(PLATFORM);
        ModifyTradesEvents.WANDERER.register(event -> event.getGeneric().add(NWBlocks.FIR_SAPLING, 5 , 1, 8, 1, 0.15F, true));
    }

    public static void onCommonPost(Platform.ModSetupContext ctx) {
        ctx.enqueueWork(() -> {
            NWStructures.postLoad();
            CompostablesRegistry.register(NWBlocks.FIR_SAPLING.get(), 0.3F);
            CompostablesRegistry.register(NWBlocks.FIR_LEAVES.get(), 0.3F);
            StrippingRegistry.register(NWBlocks.FIR_LOG.get(), NWBlocks.STRIPPED_FIR_LOG.get());
            StrippingRegistry.register(NWBlocks.FIR_WOOD.get(), NWBlocks.STRIPPED_FIR_WOOD.get());
            FlammabilityRegistry.register(NWBlocks.FIR_PLANKS.get(), 5, 20);
            FlammabilityRegistry.register(NWBlocks.FIR_SLAB.get(), 5, 20);
            FlammabilityRegistry.register(NWBlocks.FIR_FENCE_GATE.get(), 5, 20);
            FlammabilityRegistry.register(NWBlocks.FIR_FENCE.get(), 5, 20);
            FlammabilityRegistry.register(NWBlocks.FIR_STAIRS.get(), 5, 20);
            FlammabilityRegistry.register(NWBlocks.FIR_LOG.get(), 5, 5);
            FlammabilityRegistry.register(NWBlocks.STRIPPED_FIR_LOG.get(), 5, 5);
            FlammabilityRegistry.register(NWBlocks.STRIPPED_FIR_WOOD.get(), 5, 5);
            FlammabilityRegistry.register(NWBlocks.FIR_WOOD.get(), 5, 5);
            FlammabilityRegistry.register(NWBlocks.FIR_LEAVES.get(), 30, 60);
        });
    }


    public static ResourceLocation location(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static <T> T carpenterOnly(Supplier<T> supplier) {
        try {
            Class.forName("gg.moonflower.carpenter.core.Carpenter", false, NewWorld.class.getClassLoader());
            return supplier.get();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
