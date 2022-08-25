package dev.ebo2022.newworld.common.item;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum NWTiers implements Tier {
    ANCIENT(4, 3086, 8.0F, 4.0F, 10, () -> null);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    NWTiers(int miningLevel, int durability, float attackSpeed, float attackDamage, int enchantibility, Supplier<Ingredient> repairIngredient) {
        this.level = miningLevel;
        this.uses = durability;
        this.speed = attackSpeed;
        this.damage = attackDamage;
        this.enchantmentValue = enchantibility;
        this.repairIngredient = new LazyLoadedValue(repairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
