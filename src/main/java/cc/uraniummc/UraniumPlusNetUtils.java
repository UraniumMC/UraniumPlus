package cc.uraniummc;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.EncoderException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IChatComponent;

import java.io.IOException;

/**
 * Created by xjboss on 2017/9/4.
 */
public class UraniumPlusNetUtils extends PacketBuffer {
    public UraniumPlusNetUtils(PacketBuffer buf) {
        super(buf);
    }

    public static IChatComponent readChatComponent(PacketBuffer buf) throws IOException
    {
        return IChatComponent.Serializer.func_150699_a(buf.readStringFromBuffer(32767));
    }

    public static void writeChatComponent(PacketBuffer buf, IChatComponent component) throws IOException
    {
        writeString(buf,IChatComponent.Serializer.func_150696_a(component));
    }

    public static  <T extends Enum<T>> T readEnumValue(PacketBuffer buf, Class<T> enumClass)
    {
        return (T)((Enum[])enumClass.getEnumConstants())[buf.readVarIntFromBuffer()];
    }

    public static void writeEnumValue(PacketBuffer buf, Enum<?> value)
    {
        buf.writeVarIntToBuffer(value.ordinal());
    }

    public static UraniumPlusNetUtils writeString(PacketBuffer buf, String string)
    {
        byte[] abyte = string.getBytes(Charsets.UTF_8);

        if (abyte.length > 32767)
        {
            throw new EncoderException("String too big (was " + string.length() + " bytes encoded, max " + 32767 + ")");
        }
        else
        {
            buf.writeVarIntToBuffer(abyte.length);
            buf.writeBytes(abyte);
            return new UraniumPlusNetUtils(buf);
        }
    }

    public IChatComponent readChatComponent() throws IOException
    {
        return IChatComponent.Serializer.func_150699_a(readStringFromBuffer(32767));
    }

    public void writeChatComponent(IChatComponent component) throws IOException
    {
        writeString(IChatComponent.Serializer.func_150696_a(component));
    }

    public <T extends Enum<T>> T readEnumValue(Class<T> enumClass)
    {
        return (T)((Enum[])enumClass.getEnumConstants())[readVarIntFromBuffer()];
    }

    public void writeEnumValue(Enum<?> value)
    {
        writeVarIntToBuffer(value.ordinal());
    }

    public UraniumPlusNetUtils writeString(String string)
    {
        byte[] abyte = string.getBytes(Charsets.UTF_8);

        if (abyte.length > 32767)
        {
            throw new EncoderException("String too big (was " + string.length() + " bytes encoded, max " + 32767 + ")");
        }
        else
        {
            writeVarIntToBuffer(abyte.length);
            writeBytes(abyte);
            return this;
        }
    }

}
