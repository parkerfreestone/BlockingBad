package dev.blockingbad.menus;

import dev.blockingbad.common.blockentities.MortarAndPestleBlockEntity;
import dev.blockingbad.init.BlockInit;
import dev.blockingbad.init.MenuInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class MortarAndPestleMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    protected MortarAndPestleMenu(int id, Inventory playerInv, IItemHandler slots, BlockPos pos, ContainerData data) {
        super(MenuInit.MORTAR_AND_PESTLE.get(), id);

        this.levelAccess = ContainerLevelAccess.create(playerInv.player.getLevel(), pos);
        this.data = data;

        // Mortar and Pestle menu
        addSlot(new SlotItemHandler(slots, 0, 20, 20));
        addSlot(new SlotWithRestriction(slots, 1, 80, 20));

        // Players Inventory
        final int slotSizePlusTwo = 18;
        final int startX = 8;
        final int startY = 84;
        final int hotbarY = 142;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInv, col + row * 9 + 9, startX + col * slotSizePlusTwo,
                        startY + row * slotSizePlusTwo));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col, startX + col * slotSizePlusTwo, hotbarY));
        }

        this.addDataSlots(this.data);
    }

    public static MortarAndPestleMenu getClientMenu(int id, Inventory playerInv) {
        return new MortarAndPestleMenu(id, playerInv, new ItemStackHandler(2), BlockPos.ZERO, new SimpleContainerData(2));
    }

    public static MenuConstructor getServerMenu(MortarAndPestleBlockEntity blockEntity, BlockPos pos) {
        return (id, playerInv, player) -> new MortarAndPestleMenu(id, playerInv, blockEntity.getInventory(), pos, blockEntity.getContainerData());
    }
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack current = slot.getItem();
            itemStack = current.copy();

            if (index < 2) {
                if (!this.moveItemStackTo(current, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(current, 0, 2, false)) {
                return ItemStack.EMPTY;
            }

            if (current.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.levelAccess, player, BlockInit.MORTAR_AND_PESTLE.get());
    }

    public ContainerData getData() {
        return data;
    }
}
