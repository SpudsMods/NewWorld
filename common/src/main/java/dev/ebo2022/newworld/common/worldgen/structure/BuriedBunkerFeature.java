package dev.ebo2022.newworld.common.worldgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class BuriedBunkerFeature extends StructureFeature<JigsawConfiguration> {


    public static final Codec<JigsawConfiguration> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawConfiguration::startPool),
                            Codec.intRange(0, 30).fieldOf("size").forGetter(JigsawConfiguration::maxDepth)
                    )
                    .apply(instance, JigsawConfiguration::new)
    );

    public BuriedBunkerFeature() {
        super(CODEC, BuriedBunkerFeature::createPieceGenerator, PostPlacementProcessor.NONE);
    }
    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        return true;
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPieceGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        if (!BuriedBunkerFeature.isFeatureChunk(context)) {
            return Optional.empty();
        }
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
        blockpos = blockpos.below(6);
        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator = JigsawPlacement.addPieces(context, PoolElementStructurePiece::new, blockpos, true, true);
//        if(structurePiecesGenerator.isPresent()) {
//            NewWorld.LOGGER.log(Level.DEBUG, "Buried Bunker at {}", blockpos);
//        }
        return structurePiecesGenerator;
    }
}
