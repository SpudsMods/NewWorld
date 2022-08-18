package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class NWItems {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final PollinatedRegistry<Item> ITEMS = PollinatedRegistry.create(Registry.ITEM, NewWorld.MOD_ID);

    public static void load(Platform platform) {
        LOGGER.debug("Registered to platform");
        ITEMS.register(platform);
    }
}
