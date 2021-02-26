package com.iteaj.network.device.server.aava;

import com.iteaj.network.AbstractProtocol;
import com.iteaj.network.Message;
import com.iteaj.network.config.DeviceProperties;
import com.iteaj.network.server.component.ByteToMessageDecoderComponentAdapter;
import com.iteaj.network.server.protocol.NoneDealProtocol;
import com.iteaj.network.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import org.springframework.stereotype.Component;

/**
 * 声光报警服务端组件
 */
@Component
public class AavaServerComponent extends ByteToMessageDecoderComponentAdapter<AavaMessage> {

    private static final String DEVICE_SN = "DEVICE_SN";

    public AavaServerComponent() {
        super(new DeviceProperties(8093));
    }

    @Override
    public String name() {
        return "声光报警器";
    }

    @Override
    public String desc() {
        return "设备声光报警器的服务监听组件";
    }

    @Override
    public AbstractProtocol getProtocol(AavaMessage message) {
        final AavaProtocolType tradeType = message.getTradeType();
        final Message.MessageHead head = message.getHead();
        if(tradeType == AavaProtocolType.Heart) {
            return NoneDealProtocol.getInstance(message.getEquipCode());
        } else if(tradeType == AavaProtocolType.Read_Device_Id) {
            return get(head.getMessageId());
        } else if(tradeType == AavaProtocolType.Switch) {
            return remove(head.getMessageId());
        }

        return null;
    }

    @Override
    public AavaMessage decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int readableBytes = in.readableBytes();
        if(readableBytes > 0) {
            try {
                in.retain();
                Attribute<Object> attribute = ctx.channel().attr(AttributeKey.valueOf(DEVICE_SN));
                // 读取报文
                byte[] message = new byte[in.readableBytes()];
                in.readBytes(message);

                // 转成16进制的报文
                String hex = ByteUtil.bytesToHex(message);

                Object deviceSn = attribute.get(); // 设备编号
                // 如果设备编号存在
                if(deviceSn instanceof String) {
                    if(deviceSn.equals(hex)) { // 等于设备编号, 说明是心跳包
                        return new AavaMessage(message, (String) deviceSn, AavaProtocolType.Heart);
                    } else {
                        return new AavaMessage(message, (String) deviceSn);
                    }
                } else { // 设备编号不存在, 说明设备是第一次上电发送请求, 属于注册包
                    attribute.set(hex);
                }
            } finally {
                ReferenceCountUtil.release(in);
            }
        }

        return null;
    }
}
