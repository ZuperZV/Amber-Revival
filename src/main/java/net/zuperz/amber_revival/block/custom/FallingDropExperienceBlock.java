package net.zuperz.amber_revival.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class FallingDropExperienceBlock extends FallingBlock {
    public static final MapCodec<FallingDropExperienceBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    IntProvider.codec(0, 10).fieldOf("experience").forGetter(block -> block.xpRange),
                    propertiesCodec()
            ).apply(instance, FallingDropExperienceBlock::new)
    );

    private final IntProvider xpRange;

    @Override
    public MapCodec<FallingDropExperienceBlock> codec() {
        return CODEC;
    }

    public FallingDropExperienceBlock(IntProvider xpRange, BlockBehaviour.Properties properties) {
        super(properties);
        this.xpRange = xpRange;
    }

    @Override
    protected void spawnAfterBreak(BlockState pState, ServerLevel pLevel, BlockPos pPos, ItemStack pStack, boolean pDropExperience) {
        super.spawnAfterBreak(pState, pLevel, pPos, pStack, pDropExperience);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.level.LevelAccessor level, BlockPos pos,
                          @org.jetbrains.annotations.Nullable net.minecraft.world.level.block.entity.BlockEntity blockEntity,
                          @org.jetbrains.annotations.Nullable net.minecraft.world.entity.Entity breaker, ItemStack tool) {
        return this.xpRange.sample(level.getRandom());
    }
}
