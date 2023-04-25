package dev.blockingbad.common.block;

import dev.blockingbad.common.blockentities.MortarAndPestleBlockEntity;
import dev.blockingbad.init.BlockEntityInit;
import dev.blockingbad.menus.MortarAndPestleMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class MortarAndPestleBlock extends Block implements EntityBlock {

    public MortarAndPestleBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityInit.MORTAR_AND_PESTLE.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : ($0, $1, $2, blockEntity) -> {
            if (blockEntity instanceof MortarAndPestleBlockEntity mortarAndPestle) {
                mortarAndPestle.tick();
            }
        };
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            if (level.getBlockEntity(pos) instanceof MortarAndPestleBlockEntity mortarAndPestle) {
                MenuConstructor menuConstructor = MortarAndPestleMenu.getServerMenu(mortarAndPestle, pos);
                SimpleMenuProvider provider = new SimpleMenuProvider(menuConstructor, MortarAndPestleBlockEntity.TITLE);
                NetworkHooks.openScreen((ServerPlayer) player, provider, pos);
            }
        }

        return InteractionResult.sidedSuccess(!level.isClientSide());
    }
}
