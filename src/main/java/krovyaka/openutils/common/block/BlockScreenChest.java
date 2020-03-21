package krovyaka.openutils.common.block;

import li.cil.oc.common.block.Screen;

public class BlockScreenChest extends Screen {

    public final boolean isPublic;

    public BlockScreenChest(boolean isPublic) {
        super(3);
        this.setHardness(4F);
        this.setHarvestLevel("pickaxe", 0);
        this.isPublic = isPublic;
    }


}
