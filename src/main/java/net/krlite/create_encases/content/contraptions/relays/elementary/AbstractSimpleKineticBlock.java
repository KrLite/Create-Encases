package net.krlite.create_encases.content.contraptions.relays.elementary;

import com.simibubi.create.content.contraptions.relays.elementary.AbstractShaftBlock;
import com.simibubi.create.content.contraptions.wrench.IWrenchableWithBracket;

public abstract class AbstractSimpleKineticBlock extends AbstractShaftBlock implements IWrenchableWithBracket {
    public AbstractSimpleKineticBlock(Settings settings) {
        super(settings);
    }

    /*
    @Override
    public ActionResult onWrenched(BlockState state, ItemUsageContext context) {
        return IWrenchableWithBracket.super.onWrenched(state, context);
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.NORMAL;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state != newState && !isMoving)
            removeBracket(world, pos, true).ifPresent(stack -> Block.dropStack(world, pos, stack));
        super.onStateReplaced(state, world, pos, newState, isMoving);
    }

    @Override
    public Optional<ItemStack> removeBracket(BlockView world, BlockPos pos, boolean inOnReplacedContext) {
        BracketedTileEntityBehaviour behaviour = TileEntityBehaviour.get(world, pos, BracketedTileEntityBehaviour.TYPE);
        if (behaviour == null)
            return Optional.empty();
        BlockState bracket = behaviour.removeBracket(inOnReplacedContext);
        if (bracket == null)
            return Optional.empty();
        return Optional.of(new ItemStack(bracket.getBlock()));
    }

    @Override
    public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
        return CDAllTileEntities.CD_BRACKETED_KINETIC.get();
    }

     */
}
