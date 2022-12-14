package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.pollen.api.PollenRegistries;
import gg.moonflower.pollen.api.entity.PollinatedBoatType;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class NWBoatTypes {

    public static final PollinatedRegistry<PollinatedBoatType> BOAT_TYPES = PollinatedRegistry.create(PollenRegistries.BOAT_TYPE_REGISTRY, NewWorld.MOD_ID);
    public static final Supplier<PollinatedBoatType> FIR_BOAT_TYPE = BOAT_TYPES.register("fir_boat", () -> new PollinatedBoatType(NewWorld.location("textures/entity/boat/fir.png")));
}
