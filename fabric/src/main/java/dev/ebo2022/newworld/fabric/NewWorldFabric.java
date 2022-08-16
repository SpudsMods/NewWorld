package dev.ebo2022.newworld.fabric;

import dev.ebo2022.newworld.core.NewWorld;
import net.fabricmc.api.ModInitializer;

public class NewWorldFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NewWorld.PLATFORM.setup();
    }
}
