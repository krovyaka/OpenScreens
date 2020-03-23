package krovyaka.openutils.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityScreenChest extends TileEntity implements ISidedInventory {

    private final ItemStack[] items = new ItemStack[9];

    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack item : items) {
            if (item != null && !item.isEmpty())
                return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return items[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        return new ItemStack(items[i].getItem(), items[i].getCount() - i1);
    }

    @Override
    public ItemStack removeStackFromSlot(int i) {
        items[i] = null;
        return items[i];
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    @Override
    public int getField(int i) {
        return 0;
    }

    @Override
    public void setField(int i, int i1) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return "test";
    }
    

    @Override
    public boolean hasCustomName() {
        return false;
    }


    @Override
    public int[] getSlotsForFace(EnumFacing enumFacing) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, EnumFacing enumFacing) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, EnumFacing enumFacing) {
        return false;
    }
}
