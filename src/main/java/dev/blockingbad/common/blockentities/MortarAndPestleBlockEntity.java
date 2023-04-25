package dev.blockingbad.common.blockentities;

import dev.blockingbad.BlockingBad;
import dev.blockingbad.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MortarAndPestleBlockEntity extends BlockEntity {
    private int progress;
    private static final int MAX_PROGRESS = 100;
    private final ItemStackHandler inventory = new ItemStackHandler(2);
    private final LazyOptional<IItemHandlerModifiable> optional = LazyOptional.of(() -> this.inventory);

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> MortarAndPestleBlockEntity.this.progress;
                case 1 -> MortarAndPestleBlockEntity.MAX_PROGRESS;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> MortarAndPestleBlockEntity.this.progress = value;
                default -> {}
            };
        }

        @Override
        public int getCount() {
            return 2;
        }
    };
    public static final Component TITLE = Component.translatable("container." + BlockingBad.MODID + ".mortar_and_pestle");

    public MortarAndPestleBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.MORTAR_AND_PESTLE.get(), pos, state);
    }

    public void tick() {
        if (level.isClientSide()) {
            return;
        }

        progress++;

        if (progress > MAX_PROGRESS) {
            progress = 0;

            var pig = new Pig(EntityType.PIG, this.level);
            pig.setPos(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());

            this.level.addFreshEntity(pig);
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.progress = nbt.getInt("Progress");
        this.inventory.deserializeNBT(nbt.getCompound("Inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);

        // Save Progress of current process
        nbt.putInt("Progress", this.progress);

        // Save items currently inside the mortar and pestle
        nbt.put("Inventory", this.inventory.serializeNBT());
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? this.optional.cast() : super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        this.optional.invalidate();
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public ContainerData getContainerData() {
        return this.data;
    }
}
