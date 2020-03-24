package krovyaka.openutils.common.tileentity;

import krovyaka.openutils.TileEntityOCBase;
import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Visibility;
import net.minecraft.block.BlockContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;


public class TileEntitySecureChest extends TileEntityOCBase {
    ItemStackHandler inventory = new ItemStackHandler(9);
    public Set<String> names = new HashSet<>();

    public TileEntitySecureChest() {
        super("secure_chest");
        node = Network.newNode(this, Visibility.Network).withComponent(getComponentName()).withConnector(32).create();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        names = new HashSet<>(Arrays.asList(compound.getString("names").split(",")));
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setString("names", String.join(",", names));
        return super.writeToNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
    }

    @Callback(doc = "function(String:name):boolean; Gives access to chest for provided user")
    public Object[] giveAccess(Context context, Arguments args) {
        String name = args.checkString(0);
        names.add(name);
        return new Object[]{ true };
    }

    @Callback(doc = "function(String:name):boolean; Takes away access to chest for provided user")
    public Object[] takeAccess(Context context, Arguments args) {
        String name = args.checkString(0);
        names.remove(name);
        return new Object[]{ true };
    }
}
