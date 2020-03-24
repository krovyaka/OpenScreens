package krovyaka.openutils;

import com.google.common.collect.Lists;
import krovyaka.openutils.client.gui.GuiHandler;
import krovyaka.openutils.common.block.BlockScreenChest;
import krovyaka.openutils.common.block.BlockSecureChest;
import krovyaka.openutils.common.tileentity.TileEntityScreenChest;
import krovyaka.openutils.common.tileentity.TileEntitySecureChest;
import krovyaka.openutils.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Mod.EventBusSubscriber
@Mod(modid = OpenUtils.MODID, name = OpenUtils.NAME, version = OpenUtils.VERSION, acceptedMinecraftVersions = "[1.12,1.12.2]", dependencies = "after:opencomputers")
public class OpenUtils {
    public static final String MODID = "openutils";
    public static final String VERSION = "@VERSION@";
    public static final String NAME = "OpenUtilsClient";

    @SidedProxy(clientSide = "krovyaka.openutils.proxy.ClientProxy", serverSide = "krovyaka.openutils.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static OpenUtils instance;

    public static final Block BLOCK_SCREEN_CHEST_PUBLIC = new BlockScreenChest(true).setRegistryName(MODID + ":screen_chest_public").setTranslationKey(MODID + ".screen_chest_public");
    public static final Block BLOCK_SCREEN_CHEST_PRIVATE = new BlockScreenChest(false).setRegistryName(MODID + ":screen_chest_private").setTranslationKey(MODID + ".screen_chest_private");
    public static final Block BLOCK_SECURE_CHEST = new BlockSecureChest(Material.IRON).setRegistryName(MODID + ":secure_chest").setTranslationKey(MODID + ".secure_chest");

    public static final List<Block> BLOCKS = Lists.newArrayList(
            BLOCK_SCREEN_CHEST_PRIVATE,
            BLOCK_SCREEN_CHEST_PUBLIC,
            BLOCK_SECURE_CHEST
    );

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
        GameRegistry.registerTileEntity(TileEntityScreenChest.class, new ResourceLocation(MODID + ":screen_chest"));
        GameRegistry.registerTileEntity(TileEntitySecureChest.class, new ResourceLocation(MODID + ":secure_chest"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Block block : BLOCKS) {
            //noinspection ConstantConditions
            event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }

    public static void registerRenders() {
        registerRender(BLOCK_SECURE_CHEST);
    }

    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(OpenUtils.MODID + ":" + item.getTranslationKey().substring(5), "inventory"));
    }



    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
