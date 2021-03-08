package com.iteaj.network.device.server.nh3;

import com.iteaj.network.ProtocolType;
import com.iteaj.network.message.DeviceMessageHead;
import com.iteaj.network.message.UnParseBodyMessage;

import java.io.IOException;

/**
 * @author sky
 * @date 2021/3/2 10:28 上午
 */
public class Nh3Message extends UnParseBodyMessage {

    private String equipCode;
    private ProtocolType protocolType;

    public String getEquipCode() {
        return equipCode;
    }

    public void setEquipCode(String equipCode) {
        this.equipCode = equipCode;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    public Nh3Message(byte[] message, String equipCode, ProtocolType protocolType) {
        super(message);
        this.protocolType = protocolType;
        this.equipCode = equipCode;
    }

    public Nh3Message(MessageHead head) {
        super(head,null);
        this.message = head.getHeadMessage();
    }

    @Override
    public UnParseBodyMessage build() throws IOException {

        this.messageHead = new DeviceMessageHead(message, this.equipCode, this.equipCode, null);
        return this;
    }
}
