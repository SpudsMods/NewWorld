package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.core.registry.util.Woodset;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.world.level.material.MaterialColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class NWBlocks {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final PollinatedBlockRegistry BLOCKS = PollinatedRegistry.createBlock(NWItems.ITEMS);
    private static final Woodset FIR = new Woodset(MaterialColor.DEEPSLATE, MaterialColor.COLOR_BROWN);

    public static void load(Platform platform) {
        LOGGER.debug("Registered to platform");
        BLOCKS.register(platform);
    }
}
