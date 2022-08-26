package dev.ebo2022.newworld.core.forge;

import dev.ebo2022.newworld.core.NewWorld;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.Regions;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
@Mod(NewWorld.MOD_ID)
public class NewWorldForge {
    public NewWorldForge() {
        NewWorld.PLATFORM.setup();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> Regions.register(new NWRegion(NewWorld.location("overworld"), 4)));
    }
}