package krovyaka.openutils;

import com.google.common.collect.Lists;
import krovyaka.openutils.common.block.BlockScreenChest;
import krovyaka.openutils.common.tileentity.TileEntityScreenChest;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

@Mod.EventBusSubscriber
@Mod(modid = OpenUtils.MODID, name = OpenUtils.NAME, version = OpenUtils.VERSION, acceptedMinecraftVersions = "[1.12,1.12.2]", dependencies = "after:opencomputers")
public class OpenUtils {
    public static final String MODID = "openutils";
    public static final String VERSION = "@VERSION@";
    public static final String NAME = "OpenUtilsClient";

    public static final Block BLOCK_SCREEN_CHEST_PUBLIC = new BlockScreenChest(true).setRegistryName(MODID + ":screenchestpublic").setTranslationKey(MODID + ".screenchestpublic");
    public static final Block BLOCK_SCREEN_CHEST_PRIVATE = new BlockScreenChest(false).setRegistryName(MODID + ":screenchestprivate").setTranslationKey(MODID + ".screenchestprivate");

    public static final List<Block> BLOCKS = Lists.newArrayList(
            BLOCK_SCREEN_CHEST_PRIVATE,
            BLOCK_SCREEN_CHEST_PUBLIC
    );

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
        GameRegistry.registerTileEntity(TileEntityScreenChest.class, new ResourceLocation(MODID + ":screenchest"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Block block : BLOCKS) {
            //noinspection ConstantConditions
            event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }

}
