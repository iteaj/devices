package com.iteaj.network.device.server.nh3;


import com.iteaj.network.AbstractProtocol;
import com.iteaj.network.config.DeviceProperties;
import com.iteaj.network.server.component.ByteToMessageDecoderComponentAdapter;
import com.iteaj.network.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * @author sky
 * @date 2021/3/2 10:26 上午
 */
public class Nh3Component extends ByteToMessageDecoderComponentAdapter<Nh3Message> {

    AttributeKey deviceKey = AttributeKey.newInstance("DEVICE_SN");

    public Nh3Component(DeviceProperties properties) {
        super(properties);
    }

    @Override
    public String name() {
        return "氨气";
    }

    @Override
    public String desc() {
        return "氨气监测";
    }

    @Override
    public AbstractProtocol getProtocol(Nh3Message message) {
        if (message.getProtocolType() == Nh3ProtocolType.data){
            return remove(message.getHead().getMessageId());
        }
        return null;
    }

    @Override
    public Nh3Message decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        final int readLength = in.readableBytes();
        byte[] message = new byte[readLength];
        in.readBytes(message);
        final Attribute<Object> device_sn = ctx.channel().attr(deviceKey);
        String deviceSn = (String) device_sn.get();
        if(deviceSn == null) {
            deviceSn = ByteUtil.bytesToHex(message);
            ctx.channel().attr(deviceKey).set(deviceSn);
            return new Nh3Message(message, deviceSn, Nh3ProtocolType.heart);
        } else if (deviceSn.equals(ByteUtil.bytesToHex(message))) {
            return new Nh3Message(message, deviceSn, Nh3ProtocolType.heart);
        } else {
            return new Nh3Message(message, deviceSn, Nh3ProtocolType.data);
        }
    }
}
