package dev.ebo2022.newworld.core.registry;

import dev.ebo2022.newworld.common.item.MattockItem;
import dev.ebo2022.newworld.common.item.NWTiers;
import dev.ebo2022.newworld.core.NewWorld;
import gg.moonflower.pollen.api.datagen.provider.PollinatedRecipeProvider;
import gg.moonflower.pollen.api.item.PollinatedBoatItem;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
@SuppressWarnings("unused")
public class NWItems {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final PollinatedRegistry<Item> ITEMS = PollinatedRegistry.create(Registry.ITEM, NewWorld.MOD_ID);

    public static final Supplier<Item> FIR_BOAT = ITEMS.register("fir_boat", () -> new PollinatedBoatItem(NWBoatTypes.FIR_BOAT_TYPE, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
    public static final Supplier<Item> ANCIENT_MATTOCK = ITEMS.register("ancient_mattock", () ->  new MattockItem(NWTiers.ANCIENT, 7.0F, -3.5F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).rarity(Rarity.RARE)));

    public static void load(Platform platform) {

        LOGGER.debug("Registered to platform");
        ITEMS.register(platform);
    }
}
