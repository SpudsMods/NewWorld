package dev.ebo2022.newworld.core.forge;

import com.mojang.datafixers.util.Pair;
import dev.ebo2022.newworld.core.registry.NWBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.List;
import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

public class NWRegion extends Region {

    public NWRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            List<Climate.ParameterPoint> meadowPoints = new ParameterPointListBuilder()
                    .temperature(Temperature.COOL, Temperature.NEUTRAL, Temperature.UNFROZEN, Temperature.ICY)
                    .humidity(Humidity.ARID, Humidity.NEUTRAL, Humidity.WET, Humidity.HUMID)
                    .continentalness(Continentalness.span(Continentalness.FAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1, Erosion.EROSION_2)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_NORMAL_ASCENDING)
                    .build();
            meadowPoints.forEach(point -> builder.replaceBiome(point, NWBiomes.WOODED_MEADOW));
        });
    }
}