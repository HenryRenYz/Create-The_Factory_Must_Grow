package com.drmangotea.tfmg.content.items;

import com.drmangotea.tfmg.content.machines.pipes.normal.LockablePipeBlockEntity;
import com.drmangotea.tfmg.content.machines.pipes.normal.steel.SteelPipeBlock;
import com.drmangotea.tfmg.registry.TFMGBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;


public class ScrewdriverItem extends Item {




    public ScrewdriverItem(Properties p_40566_) {
        super( p_40566_);

    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();

        BlockPos positionClicked = pContext.getClickedPos();

        Level level = pContext.getLevel();




        if(level.getBlockState(positionClicked).is(TFMGBlocks.STEEL_PIPE.get())) {
            level.playSound(player, positionClicked, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 0.3f,0.5f);
            ((LockablePipeBlockEntity)level.getBlockEntity(positionClicked)).toggleLock(player);

        }



        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (playerr) -> playerr.broadcastBreakEvent(playerr.getUsedItemHand()));


        return InteractionResult.SUCCESS;

    }
}