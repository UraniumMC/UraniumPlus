package cc.uraniummc.packet;

import cc.uraniummc.packet.handler.ActionBarHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

import java.io.IOException;

/**
 * Created by xjboss on 2017/9/4.
 */
@Getter
public class PacketChatWithType implements IMessage {
    private byte type;
    private IChatComponent chat;

    public PacketChatWithType(IChatComponent chat,byte type) {
        this.type = type;
        this.chat = chat;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer pb=new PacketBuffer(buf);
        try {
            chat=IChatComponent.Serializer.func_150699_a(pb.readStringFromBuffer(32767));
        }catch (Exception e){throw new IndexOutOfBoundsException(e.getMessage());}
        type=pb.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {

        PacketBuffer pb=new PacketBuffer(buf);
        try {
            pb.writeStringToBuffer(IChatComponent.Serializer.func_150696_a(chat));
        }catch (Exception e){throw new IndexOutOfBoundsException(e.getMessage());}
        pb.writeByte(type);
    }

}
