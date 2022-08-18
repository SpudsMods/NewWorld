package dev.ebo2022.newworld.core;

import dev.ebo2022.newworld.core.other.NewWorldData;
import dev.ebo2022.newworld.core.registry.NWBlocks;
import dev.ebo2022.newworld.core.registry.NWBoatTypes;
import dev.ebo2022.newworld.core.registry.NWItems;
import gg.moonflower.pollen.api.platform.Platform;
import net.minecraft.resources.ResourceLocation;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class NewWorld {

    public static final String MOD_ID = "newworld";
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> NewWorld::clientInit)
            .clientPostInit(() -> NewWorld::clientPostInit)
            .commonInit(NewWorld::commonInit)
            .commonPostInit(NewWorld::commonPostInit)
            .build();

    public static void clientInit() {
    }

    public static void clientPostInit(Platform.ModSetupContext ctx) {
    }

    public static void commonInit() {
        NWBlocks.load(PLATFORM);
        NWItems.load(PLATFORM);
        NWBoatTypes.load(PLATFORM);
    }

    public static void commonPostInit(Platform.ModSetupContext ctx) {
        ctx.enqueueWork(NewWorldData::init);
    }

    public static ResourceLocation location(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
