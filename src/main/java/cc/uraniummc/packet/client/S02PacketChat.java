package cc.uraniummc.packet.client;

import cc.uraniummc.packet.handler.ActionBarHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

import java.io.IOException;

/**
 * Created by xjboss on 2017/9/4.
 */
@SideOnly(Side.CLIENT)
public class S02PacketChat extends net.minecraft.network.play.server.S02PacketChat {
    @Getter
    private Byte type;
    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        super.readPacketData(buf);
        try {
            if(buf.isReadable()) this.type = buf.readByte();
        }catch (Throwable e){}
        if(type==null||type==-1)type=1;

    }

    public void processPacket(INetHandler p_148833_1_)
    {
        ActionBarHandler.handleChat((INetHandlerPlayClient)p_148833_1_,this);
    }
}
