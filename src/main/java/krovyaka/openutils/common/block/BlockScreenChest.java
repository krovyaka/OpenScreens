package krovyaka.openutils.common.block;

import krovyaka.openutils.common.tileentity.TileEntityScreenChest;
import li.cil.oc.common.GuiType;
import li.cil.oc.common.block.SimpleBlock;
import li.cil.oc.common.block.traits.GUI;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockScreenChest extends SimpleBlock implements GUI {

    public final boolean isPublic;

    private static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockScreenChest(boolean isPublic) {
        super(Material.ROCK);
        this.isPublic = isPublic;
        setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public GuiType.EnumVal guiType() {
        return GuiType.Screen();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos blockPos, EnumFacing enumFacing, float p_getStateForPlacement_4_, float p_getStateForPlacement_5_, float p_getStateForPlacement_6_, int p_getStateForPlacement_7_, EntityLivingBase placer, EnumHand enumHand) {
        EnumFacing facing = EnumFacing.getDirectionFromEntityLiving(blockPos, placer);
        if (facing.equals(EnumFacing.DOWN) || facing.equals(EnumFacing.UP)) {
            facing = EnumFacing.NORTH;
        }
        return getDefaultState().withProperty(FACING, facing);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityScreenChest) {
            player.displayGUIChest((TileEntityScreenChest) tileEntity);
        }
        return true;
    }
}
