package cc.uraniummc.packet.handler;

import cc.uraniummc.UraniumPlusClient;
import cc.uraniummc.packet.client.S02PacketChat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.network.play.INetHandlerPlayClient;

/**
 * Created by xjboss on 2017/9/4.
 */
@SideOnly(Side.CLIENT)
public class ActionBarHandler {
    public static void handleChat(INetHandlerPlayClient pClient,S02PacketChat pPacket)
    {
        if(pPacket.getType() != 2) pClient.handleChat(pPacket);
        else{
            UraniumPlusClient.getInstance().setRecordPlaying(pPacket.func_148915_c(),false);
        }
    }

}
