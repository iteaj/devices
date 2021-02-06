package com.iteaj.network.device.server.gps;

import com.iteaj.network.AbstractProtocol;
import com.iteaj.network.Message;
import com.iteaj.network.ProtocolFactory;
import com.iteaj.network.device.server.gps.protocol.*;
import com.iteaj.network.server.protocol.NoneDealProtocol;
import com.iteaj.network.utils.ByteUtil;

/**
 * create time: 2021/1/14
 *
 * @author iteaj
 * @since 1.0
 */
public class GpsProtocolFactory extends ProtocolFactory<GpsMessage> {

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

                AbstractProtocol remove = remove(messageId);
                System.out.println("位置信息查询应答: " + messageId);
                return remove;

            case Unknown:
                return NoneDealProtocol.getInstance(head.getEquipCode());
        }
        return null;
    }
}
