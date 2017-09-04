package cc.uraniummc;

import cc.uraniummc.packet.S45PacketTitle;
import cc.uraniummc.packet.client.PacketChatWithType;
import cc.uraniummc.packet.handler.ActionBarHandler;
import cc.uraniummc.packet.handler.TitleHandler;
import com.google.common.collect.BiMap;
import org.objectweb.asm.Type;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.network.EnumConnectionState;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Created by xjboss on 2017/9/4.
 */
public class UraniumPlusCommon {
    UraniumPlusCommon(){

    }
    private static SimpleNetworkWrapper chancel;

    public static SimpleNetworkWrapper getChancel() {
        return chancel;
    }

    void modPost(FMLPostInitializationEvent evt){
        chancel= NetworkRegistry.INSTANCE.newSimpleChannel("UraniumMC");
        chancel.registerMessage(TitleHandler.class, S45PacketTitle.class,0, Side.CLIENT);
        chancel.registerMessage(ActionBarHandler.class, PacketChatWithType.class,1, Side.CLIENT);
        String mn= FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(EnumConnectionState.class.getName(),"func_150756_b",Type.getMethodDescriptor(org.objectweb.asm.Type.VOID_TYPE, Type.INT_TYPE,Type.getType(Class.class)));
        try {
            Method regCPmsg = EnumConnectionState.class.getDeclaredMethod(mn,int.class,Class.class);
            regCPmsg.setAccessible(true);
            regCPmsg.invoke(EnumConnectionState.PLAY,69,S45PacketTitle.class);
            /*
            mn=FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(EnumConnectionState.class.getName(),"field_150770_i",Type.getType(BiMap.class).getDescriptor());
            Field CPmap=EnumConnectionState.class.getDeclaredField("field_150770_i");
                CPmap.setAccessible(true);
                if(evt.getSide()==Side.CLIENT) {
                BiMap CPMap_ = (BiMap) CPmap.get(EnumConnectionState.PLAY);
                CPMap_.remove(2);
                regCPmsg.invoke(EnumConnectionState.PLAY, 2, PacketChatWithType.class);
            }
            CPmap.setAccessible(false);
            */
            regCPmsg.setAccessible(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
