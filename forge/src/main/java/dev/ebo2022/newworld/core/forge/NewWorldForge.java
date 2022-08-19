package dev.ebo2022.newworld.core.forge;

import dev.ebo2022.newworld.core.NewWorld;
import net.minecraftforge.fml.common.Mod;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
@Mod(NewWorld.MOD_ID)
public class NewWorldForge {
    public NewWorldForge() {
        NewWorld.PLATFORM.setup();
    }
}