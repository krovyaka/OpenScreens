package krovyaka.openutils.common.container;

import krovyaka.openutils.common.tileentity.TileEntitySecureChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by Toby on 27/06/2017.
 */
public class ContainerSecureChest extends Container {

    private final TileEntitySecureChest tileEntitySecureChest;

    public ContainerSecureChest(InventoryPlayer inventoryPlayer, TileEntitySecureChest tileEntitySecureChest) {

        this.tileEntitySecureChest = tileEntitySecureChest;
        if (tileEntitySecureChest.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)) {
            IItemHandler inventory = tileEntitySecureChest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

            // CONTAINER INVENTORY
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    addSlotToContainer(new SlotForSecureChest(inventory, x + (y * 3), 62 + x * 18, 17 + y * 18));
                }
            }

            // MAIN PLAYER INVENTORY
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 9; x++) {
                    addSlotToContainer(new Slot(inventoryPlayer, x + (y * 9) + 9, 8 + x * 18, 84 + y * 18));
                }
            }

            // PLAYER HOTBAR INVENTORY
            for (int i = 0; i < 9; i++) {
                addSlotToContainer(new Slot(inventoryPlayer, i, 8 + (i * 18), 142));
            }
        }

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            stack = stackInSlot.copy();

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(stackInSlot, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(stackInSlot, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (stackInSlot.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            slot.onTake(player, stackInSlot);

        }
        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntitySecureChest.names.isEmpty() || tileEntitySecureChest.names.contains(player.getName());
    }
}
