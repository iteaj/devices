package com.iteaj.network.device.server.aava;

import com.iteaj.network.device.elfin.ElfinMessage;
import com.iteaj.network.device.elfin.ElfinMessageBody;
import com.iteaj.network.device.elfin.ElfinMessageHeader;
import com.iteaj.network.utils.ByteUtil;

import java.io.IOException;

/**
 * 声光报警器报文
 * 声光报警器的报文使用非保准的Modbus格式
 */
public class AavaMessage extends ElfinMessage {

    public AavaMessage(byte[] message, String equipCode, AavaProtocolType tradeType) {
        super(message, equipCode, tradeType);
    }

    public AavaMessage(byte[] message, String equipCode) {
        this(message, equipCode, null);
    }

    public AavaMessage(ElfinMessageHeader head, ElfinMessageBody body) {
        super(head, body);
        final byte[] bodyMessage = body.getBodyMessage();
        this.message = new byte[bodyMessage.length];
        ByteUtil.addBytes(this.message, bodyMessage, 0);
    }

    @Override
    public ElfinMessage build() throws IOException {
        AavaProtocolType protocolType = getTradeType();
        if(protocolType != AavaProtocolType.Heart) {
            final String code = ByteUtil.bytesToHex(getMessage(), 1, 1);

            protocolType = AavaProtocolType.instance(code);
            setTradeType(protocolType);

            if(protocolType == AavaProtocolType.Switch) {
                final short num = ByteUtil.bytesToShort(getMessage(), 4);
                if(num == 4) {
                    /**
                     * @see com.iteaj.network.device.server.aava.protocol.AavaNewStatusSwitch
                     */
                    protocolType.setNum("0004"); // 新版的协议
                }
            }
        }

        this.messageHead = new ElfinMessageHeader(getEquipCode(), protocolType);
        return this;
    }

    @Override
    public AavaProtocolType getTradeType() {
        return (AavaProtocolType) super.getTradeType();
    }

}
