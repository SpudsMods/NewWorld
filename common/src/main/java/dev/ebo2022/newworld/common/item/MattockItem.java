package dev.ebo2022.newworld.common.item;

import dev.ebo2022.newworld.core.registry.NWBlockTags;
import net.minecraft.world.item.Tier;

public class MattockItem extends NonMagicDiggerItem {



    public MattockItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(attackDamage, attackSpeed, tier, NWBlockTags.MINEABLE_WITH_MATTOCK, properties);
    }
//    public InteractionResult useOn(UseOnContext useOnContext) {
//        float boomPower = 100.0F;
//        Level level = useOnContext.getLevel();
//        Player player = useOnContext.getPlayer();
//
//        level.explode(player, player.getX(), player.getY(1.0D), player.getZ(), boomPower, Explosion.BlockInteraction.BREAK);
//        return InteractionResult.SUCCESS;
//    }
}
