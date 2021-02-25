package com.iteaj.network.device.server.gps;

import com.iteaj.network.AbstractProtocol;
import com.iteaj.network.Message;
import com.iteaj.network.config.DeviceProperties;
import com.iteaj.network.device.server.gps.protocol.*;
import com.iteaj.network.server.component.ByteToMessageDecoderComponentAdapter;
import com.iteaj.network.server.protocol.NoneDealProtocol;
import com.iteaj.network.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * create time: 2021/1/14
 *
 * @author iteaj
 * @since 1.0
 */
public class GpsServerComponent extends ByteToMessageDecoderComponentAdapter<GpsMessage> {

    public GpsServerComponent(DeviceProperties deviceProperties) {
        super(deviceProperties);
    }

    @Override
    public String name() {
        return "Gps定位设备";
    }

    @Override
    public String desc() {
        return "道路运输车辆卫星定位系统";
    }

    @Override
    public AbstractProtocol getProtocol(GpsMessage message) {
        Message.MessageHead head = message.getHead();
        GpsProtocolType type = head.getTradeType();
        switch (type) {
            case Heart:
                new GetReportProtocol(head.getEquipCode(), head.getMessageId()).request();
                return new HeartProtocol(message).buildRequestMessage();
            case TRegister:
                return new TRegisterProtocol(message).buildRequestMessage();
            case TAuth:
                return new TAuthProtocol(message).buildRequestMessage();
            case DIdentity:
                return new DIdentityProtocol(message).buildRequestMessage();
            case PReport:
                return new PReportProtocol(message).buildRequestMessage();
            case QPRInfo:
                byte[] bodyMessage = message.getBody().getBodyMessage();
                String messageId = ByteUtil.bytesToShortOfNegate(bodyMessage, 0) + "";

                AbstractProtocol remove = (AbstractProtocol) remove(messageId);
                System.out.println("位置信息查询应答: " + messageId);
                return remove;

            case Unknown:
                return NoneDealProtocol.getInstance(head.getEquipCode());
        }
        return null;
    }

    @Override
    public GpsMessage decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int readableBytes = in.readableBytes();
        if(readableBytes > 0) {
            return new GpsMessage(in);
        }

        return null;
    }
}
