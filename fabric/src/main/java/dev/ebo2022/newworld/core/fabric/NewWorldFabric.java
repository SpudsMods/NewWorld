package dev.ebo2022.newworld.core.fabric;

import dev.ebo2022.newworld.core.NewWorld;
import net.fabricmc.api.ModInitializer;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class NewWorldFabric implements ModInitializer, TerraBlenderApi {

    @Override
    public void onInitialize() {
        NewWorld.PLATFORM.setup();
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new NWRegion(NewWorld.location("overworld"), 2));
    }
}
