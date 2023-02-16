package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.common.item.MattockItem;
import dev.ebo2022.newworld.common.item.NWTiers;
import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.pollen.api.item.PollinatedBoatItem;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
@SuppressWarnings("unused")
public class NWItems {

    public static final PollinatedRegistry<Item> ITEMS = PollinatedRegistry.create(Registry.ITEM, NewWorld.MOD_ID);

    public static final Supplier<Item> FIR_BOAT = ITEMS.register("fir_boat", () -> new PollinatedBoatItem(NWBoatTypes.FIR_BOAT_TYPE, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
    public static final Supplier<Item> ANCIENT_MATTOCK = ITEMS.register("ancient_mattock", () -> new MattockItem(NWTiers.ANCIENT, 0.0F, -3.0F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).rarity(Rarity.RARE)));
}
