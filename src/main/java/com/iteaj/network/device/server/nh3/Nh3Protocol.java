package com.iteaj.network.device.server.nh3;


import com.iteaj.network.message.DeviceMessageHead;
import com.iteaj.network.server.protocol.PlatformRequestProtocol;
import com.iteaj.network.utils.ByteUtil;

import java.io.IOException;

/**
 * @author sky
 * @date 2021/3/2 10:34 上午
 */

public class Nh3Protocol extends PlatformRequestProtocol<Nh3Message> {
    private int concentration;
    public Nh3Protocol(String equipCode) {
        super(equipCode);
    }

    public int getConcentration() {
        return concentration;
    }

    public void setConcentration(int concentration) {
        this.concentration = concentration;
    }

    @Override
    protected String doGetMessageId() {
        return null;
    }

    @Override
    protected Nh3Message doBuildRequestMessage() throws IOException {
        byte[] messageByte = ByteUtil.hexToByte("050300000001858E");
        final DeviceMessageHead deviceMessageHead = new DeviceMessageHead(messageByte, this.getEquipCode(), this.getEquipCode(), this.protocolType());

        return new Nh3Message(deviceMessageHead);
    }

    @Override
    protected Nh3Message resolverResponseMessage(Nh3Message message) {
        final byte[] message1 = message.getMessage();
        this.concentration = ByteUtil.bytesToShort(message1,3);
        return message;
    }

    @Override
    public <T> T protocolType() {
        return (T) Nh3ProtocolType.data;
    }
}
