package dev.ebo2022.newworld.core.hook;

import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.carpenter.core.registry.CarpenterChestType;
import gg.moonflower.carpenter.core.registry.CarpenterChests;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public final class CarpenterCompatHooks {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final boolean CARPENTER_LOADED = Platform.isModLoaded("carpenter");
    public static final PollinatedRegistry<CarpenterChestType> CHEST_TYPES = PollinatedRegistry.create(CarpenterChests.REGISTRY, NewWorld.MOD_ID);

    private CarpenterCompatHooks() {
    }

    public static void load(Platform platform) {
        if (CARPENTER_LOADED) {
            LOGGER.debug("Registered compat chest types");
            CHEST_TYPES.register(platform);
        }
    }

    public static Supplier<CarpenterChestType> buildChestType(String chestType) {
        return CHEST_TYPES.register(chestType, () -> new CarpenterChestType(
                NewWorld.location("block/" + chestType + "/" + chestType + "_base"),
                NewWorld.location("block/" + chestType + "/" + chestType + "_base_left"),
                NewWorld.location("block/" + chestType + "/" + chestType + "_base_right"),
                NewWorld.location("block/" + chestType + "/" + chestType + "_lid"),
                NewWorld.location("block/" + chestType + "/" + chestType + "_lid_left"),
                NewWorld.location("block/" + chestType + "/" + chestType + "_lid_right"),
                NewWorld.location("block/" + chestType + "/" + chestType + "_knob")
        ));
    }
}
