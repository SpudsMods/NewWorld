package dev.ebo2022.newworld.common.block.grower;

import dev.ebo2022.newworld.core.registry.NWFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FirTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean bl) {
        return bl ? Holder.direct(NWFeatures.Configured.GROWN_FIR_BEES_002.get()) : Holder.direct(NWFeatures.Configured.GROWN_FIR.get());
    }
}
