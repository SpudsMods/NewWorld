package dev.ebo2022.newworld.core.other;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;
import dev.ebo2022.newworld.core.NewWorld;
import dev.ebo2022.newworld.core.registry.NWBiomes;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FogType;

public final class NWClientEvents {

    private NWClientEvents() {
    }

    public static void mistyMeadow(GameRenderer renderer, Camera camera, float distance, float partialTicks) {
        Level world = camera.getEntity().level;
        BlockPos pos = camera.getBlockPosition();
        Holder<Biome> biome = world.getBiome(pos);

        float mistDensity = 24f;
        float f4 = Mth.clamp(mistDensity / 10.0F, 4.0F, 64.0F);
        float actual = mistDensity - f4;

        if (NewWorld.CLIENT_CONFIG.woodedMeadowFog.get() && biome.is(NWBiomes.WOODED_MEADOW)) {
            RenderSystem.setShaderFogStart(actual);
            RenderSystem.setShaderFogEnd(mistDensity * 4);
        }
    }
}
