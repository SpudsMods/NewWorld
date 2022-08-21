package dev.ebo2022.newworld.common.worldgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.ebo2022.newworld.core.NewWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.apache.logging.log4j.Level;

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
//        BlockPos place = context.chunkPos().getCenterAtY(0).down(4);
//        int width = 5;
//        int height = 5;
//        for (int x = place.getX() - width; x < place.getX() + width; x++){
//            for (int z = place.getZ() - width; z < place.getZ() + width; z++){
//                for (int y = place.getY() - height; y < place.getY() + height; y++){
//
//                }
//            }
//        }
        return true;
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPieceGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        if (!BuriedBunkerFeature.isFeatureChunk(context)) {
            return Optional.empty();
        }
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
        blockpos = blockpos.below(6);
        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator = JigsawPlacement.addPieces(context, PoolElementStructurePiece::new, blockpos, true, true);
        if(structurePiecesGenerator.isPresent()) {
            NewWorld.LOGGER.log(Level.DEBUG, "Buried Bunker at {}", blockpos);
        }
        return structurePiecesGenerator;
    }
}
