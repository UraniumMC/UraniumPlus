package cc.uraniummc.packet;

import cc.uraniummc.UraniumPlusNetUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IChatComponent;

import java.io.IOException;

public class S45PacketTitle extends Packet implements IMessage
{
    private S45PacketTitle.Type type;
    private IChatComponent message;
    private int fadeInTime;
    private int displayTime;
    private int fadeOutTime;

    public S45PacketTitle()
    {
    }

    public S45PacketTitle(S45PacketTitle.Type type, IChatComponent message)
    {
        this(type, message, -1, -1, -1);
    }

    public S45PacketTitle(int fadeInTime, int displayTime, int fadeOutTime)
    {
        this(S45PacketTitle.Type.TIMES, (IChatComponent)null, fadeInTime, displayTime, fadeOutTime);
    }

    public S45PacketTitle(S45PacketTitle.Type type, IChatComponent message, int fadeInTime, int displayTime, int fadeOutTime)
    {
        this.type = type;
        this.message = message;
        this.fadeInTime = fadeInTime;
        this.displayTime = displayTime;
        this.fadeOutTime = fadeOutTime;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            readPacketData(new PacketBuffer(buf));
        }catch (IOException e){}
    }

    @Override
    public void toBytes(ByteBuf buf) {
        try {
            writePacketData(new PacketBuffer(buf));
        }catch (IOException e){}
    }

    /**
     * Reads the raw packet data from the data stream.
     */

    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.type = (S45PacketTitle.Type) UraniumPlusNetUtils.readEnumValue(buf,S45PacketTitle.Type.class);

        if (this.type == S45PacketTitle.Type.TITLE || this.type == S45PacketTitle.Type.SUBTITLE)
        {
            this.message = UraniumPlusNetUtils.readChatComponent(buf);
        }
        if (this.type == S45PacketTitle.Type.TIMES)
        {
            this.fadeInTime = buf.readInt();
            this.displayTime = buf.readInt();
            this.fadeOutTime = buf.readInt();
        }
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        UraniumPlusNetUtils.writeEnumValue(buf,this.type);

        if (this.type == S45PacketTitle.Type.TITLE || this.type == S45PacketTitle.Type.SUBTITLE)
        {
            UraniumPlusNetUtils.writeChatComponent(buf,this.message);
        }

        if (this.type == S45PacketTitle.Type.TIMES)
        {
            buf.writeInt(this.fadeInTime);
            buf.writeInt(this.displayTime);
            buf.writeInt(this.fadeOutTime);
        }
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandler handler)
    {
        /*
        handler.handleTitle(this);
        */
    }

    @SideOnly(Side.CLIENT)
    public S45PacketTitle.Type getType()
    {
        return this.type;
    }

    @SideOnly(Side.CLIENT)
    public IChatComponent getMessage()
    {
        return this.message;
    }

    @SideOnly(Side.CLIENT)
    public int getFadeInTime()
    {
        return this.fadeInTime;
    }

    @SideOnly(Side.CLIENT)
    public int getDisplayTime()
    {
        return this.displayTime;
    }

    @SideOnly(Side.CLIENT)
    public int getFadeOutTime()
    {
        return this.fadeOutTime;
    }

    public static enum Type
    {
        TITLE,
        SUBTITLE,
        TIMES,
        ACTIONBAR,
        CLEAR,
        RESET;

        public static S45PacketTitle.Type byName(String name)
        {
            for (S45PacketTitle.Type s45packettitle$type : values())
            {
                if (s45packettitle$type.name().equalsIgnoreCase(name))
                {
                    return s45packettitle$type;
                }
            }

            return TITLE;
        }

        public static String[] getNames()
        {
            String[] astring = new String[values().length];
            int i = 0;

            for (S45PacketTitle.Type s45packettitle$type : values())
            {
                astring[i++] = s45packettitle$type.name().toLowerCase();
            }

            return astring;
        }
    }
}