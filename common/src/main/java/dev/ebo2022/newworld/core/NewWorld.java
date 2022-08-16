package dev.ebo2022.newworld.core;

import gg.moonflower.pollen.api.platform.Platform;

public class NewWorld {

    public static final String MOD_ID = "assembly";
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
    }

    public static void commonPostInit(Platform.ModSetupContext ctx) {
    }
}
