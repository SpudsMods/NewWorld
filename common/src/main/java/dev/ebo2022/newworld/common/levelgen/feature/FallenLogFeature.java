package dev.ebo2022.newworld.common.levelgen.feature;

import com.mojang.serialization.Codec;
import dev.ebo2022.newworld.core.registry.NWBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

import java.util.Random;

public class FallenLogFeature extends Feature<BlockStateConfiguration> {

    public FallenLogFeature(Codec<BlockStateConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        Direction direction = getDirection(context.random());
        BlockPos blockPos = context.origin();
        WorldGenLevel worldGenLevel = context.level();
        BlockStateConfiguration blockStateConfiguration = context.config();
        int logLength = context.random().nextInt(2,5);

        if ((worldGenLevel.canSeeSky(blockPos)) || worldGenLevel.getBlockState(blockPos.below()).isRedstoneConductor(worldGenLevel, blockPos)) {
            if (!worldGenLevel.getBlockState(blockPos.below()).is(Blocks.WATER) && !worldGenLevel.getBlockState(blockPos.below()).is(Blocks.LAVA)) {
                placeBlock(logLength, 0, worldGenLevel, blockPos, direction, blockStateConfiguration.state);
            }
        }
        return true;
    }

    public static void placeBlock(int maxLength, int currentLoop, WorldGenLevel worldGenLevel, BlockPos blockPos, Direction direction, BlockState state){
        if (currentLoop <= maxLength){
            generateLog(worldGenLevel, blockPos, state, direction);
            placeBlock(maxLength, currentLoop + 1, worldGenLevel, blockPos.relative(direction), direction, state);
        }
    }

    public static void generateLog(WorldGenLevel world, BlockPos pos, BlockState state, Direction direction){
        for (int j = 0; j >= -10; --j) {
            if (!isAir(world, pos.below(j)) && !world.getBlockState(pos).is(NWBlocks.FIR_LOG.get())){
                world.setBlock(pos, state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()), Block.UPDATE_INVISIBLE);
            } else {
                break;
            }
        }
    }

    public static Direction getDirection(Random random){
        Direction output = Direction.getRandom(random);
        if (output.getAxis().isVertical()){
            return Direction.EAST;
        } else {
            return output;
        }
    }
}