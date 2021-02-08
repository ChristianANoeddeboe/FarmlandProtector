package net.fabricmc.angercraft.mixin;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FarmlandBlock.class)
public interface FarmlandBlockInvoker {

    @Invoker("isWaterNearby")
    public static boolean isWaterNearby(WorldView world, BlockPos pos) {
        throw new AssertionError();
    }
}
