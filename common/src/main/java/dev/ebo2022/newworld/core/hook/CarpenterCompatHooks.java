package dev.ebo2022.newworld.core.hook;

import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.carpenter.core.registry.CarpenterChestType;
import gg.moonflower.carpenter.core.registry.CarpenterChests;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;

import java.util.function.Supplier;

public final class CarpenterCompatHooks {

    public static final PollinatedRegistry<CarpenterChestType> CHEST_TYPES = PollinatedRegistry.create(CarpenterChests.REGISTRY, NewWorld.MOD_ID);

    private CarpenterCompatHooks() {
    }

    private static Supplier<CarpenterChestType> buildChestType(String chestType) {
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
