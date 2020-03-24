package krovyaka.openutils.proxy;

import krovyaka.openutils.OpenUtils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


/**
 * Created by Toby on 18/08/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain(OpenUtils.MODID);
    }

    public void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(OpenUtils.MODID + ":" + item.getTranslationKey().substring(5), "inventory"));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        OpenUtils.registerRenders();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
