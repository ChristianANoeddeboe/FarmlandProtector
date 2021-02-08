package net.fabricmc.angercraft.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static net.minecraft.block.FarmlandBlock.MOISTURE;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin extends Block {

	public FarmlandBlockMixin(Settings settings) {
		super(settings);
		this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(MOISTURE, 0));
	}

	@Override
	public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance) {
		super.onLandedUpon(world, pos, entity, distance);
		System.out.println("Landed!");
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		int i = (Integer)state.get(MOISTURE);
		if (!FarmlandBlockInvoker.isWaterNearby(world, pos) && !world.hasRain(pos.up())) {
			if (i > 0) {
				world.setBlockState(pos, (BlockState)state.with(MOISTURE, i - 1), 2);
			}
		} else if (i < 7) {
			world.setBlockState(pos, (BlockState)state.with(MOISTURE, 7), 2);
		}
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!state.canPlaceAt(world, pos)) {
			//setToDirt(state, world, pos);
		}
	}
}
