package dev.ebo2022.newworld.forge;

import dev.ebo2022.newworld.core.NewWorld;
import net.minecraftforge.fml.common.Mod;

@Mod(NewWorld.MOD_ID)
public class NewWorldForge {
    public NewWorldForge() {
        NewWorld.PLATFORM.setup();
    }
}