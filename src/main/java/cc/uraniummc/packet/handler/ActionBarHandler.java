package cc.uraniummc.packet.handler;

import cc.uraniummc.UraniumPlusClient;
import cc.uraniummc.packet.PacketChatWithType;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S02PacketChat;

/**
 * Created by xjboss on 2017/9/4.
 */
public class ActionBarHandler implements IMessageHandler<PacketChatWithType,IMessage> {
    @SideOnly(Side.CLIENT)
    public static void handleChat(INetHandlerPlayClient pClient,PacketChatWithType pPacket)
    {
        if(pPacket.getType() != 2) pClient.handleChat(new S02PacketChat(pPacket.getChat()));
        else{
            UraniumPlusClient.getInstance().setRecordPlaying(pPacket.getChat(),false);
        }
    }

    @Override
    public IMessage onMessage(PacketChatWithType message, MessageContext ctx) {
        handleChat(ctx.getClientHandler(),message);
        return null;
    }
}
