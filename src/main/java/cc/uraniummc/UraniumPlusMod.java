package cc.uraniummc;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.client.FMLFileResourcePack;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

import java.io.File;
import java.util.Arrays;

/**
 * Created by xjboss on 2017/9/4.
 */

public class UraniumPlusMod extends DummyModContainer {
    public UraniumPlusMod()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId="UraniumPlus";
        meta.name="Added title and actionbar support for client and server";
        meta.version= UMPData.ver;
        meta.credits="";
        meta.authorList= Arrays.asList( "imcc","xjboss");
        meta.description="";
        meta.url="https://git.xjboss.net/UraniumDev/UraniumPlus";
        meta.updateUrl="https://ci.xjboss.net/jobs/UraniumPlus";
        meta.screenshots=new String[0];
        meta.logoFile="";
    }
    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        bus.register(this);
        return true;
    }

    @Subscribe
    public void modConstruction(FMLConstructionEvent evt)
    {
        NetworkRegistry.INSTANCE.register(this, this.getClass(), null, evt.getASMHarvestedData());
    }
    @Subscribe
    public void modPost(FMLPostInitializationEvent evt){
        new UraniumPlusCommon().modPost(evt);
    }
    @Override
    public Object getMod()
    {
        return this;
    }

    @Override
    public File getSource() {
        if(UraniumPlusCore.getLocation()!=null)return UraniumPlusCore.getLocation();
        return null;
    }

    @Override
    public Class<?> getCustomResourcePackClass() {
        if(UraniumPlusCore.getLocation()!=null)return FMLFileResourcePack.class;
        return null;
    }
}
