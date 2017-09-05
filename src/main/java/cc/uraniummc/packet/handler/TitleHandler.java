package cc.uraniummc.packet.handler;

import cc.uraniummc.UraniumPlusClient;
import cc.uraniummc.packet.S45PacketTitle;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created by xjboss on 2017/9/4.
 */
public class TitleHandler implements IMessageHandler<S45PacketTitle,IMessage> {

    @SideOnly(Side.CLIENT)
    public void handleTitle(S45PacketTitle packetIn)
    {
        UraniumPlusClient client= UraniumPlusClient.getInstance();
        S45PacketTitle.Type s45packettitle$type = packetIn.getType();
        String s = null;
        String s1 = null;
        String s2 = packetIn.getMessage() != null ? packetIn.getMessage().getFormattedText() : "";

        switch (s45packettitle$type)
        {
            case TITLE:
                s = s2;
                break;
            case SUBTITLE:
                s1 = s2;
                break;
            case ACTIONBAR:
                UraniumPlusClient.getInstance().setRecordPlaying(s2,false);
                return;
            case RESET:
                client.displayTitle("", "", -1, -1, -1);
                client.setDefaultTitlesTimes();
                return;
        }

        client.displayTitle(s, s1, packetIn.getFadeInTime(), packetIn.getDisplayTime(), packetIn.getFadeOutTime());
    }

    @Override
    public IMessage onMessage(S45PacketTitle message, MessageContext ctx) {
        handleTitle(message);
        return null;
    }
}
