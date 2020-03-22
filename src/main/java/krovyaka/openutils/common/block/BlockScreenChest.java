package krovyaka.openutils.common.block;

import krovyaka.openutils.common.tileentity.TileEntityScreenChest;
import li.cil.oc.common.block.SimpleBlock;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockScreenChest extends SimpleBlock {

    public final boolean isPublic;

    public BlockScreenChest(boolean isPublic) {
        super(Material.ROCK);
        this.isPublic = isPublic;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityScreenChest();
    }
}
