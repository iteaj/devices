package com.iteaj.network.device.client.lighton;

import com.iteaj.network.client.ClientMessage;
import com.iteaj.network.message.DeviceMessageHead;
import com.iteaj.network.message.UnParseBodyMessage;
import com.iteaj.network.message.VoidMessageBody;
import com.iteaj.network.utils.ByteUtil;

import java.io.IOException;

public class LightonMediaMessage extends ClientMessage {

    public LightonMediaMessage(byte[] message) {
        super(message);
    }

    public LightonMediaMessage(String deviceSn, byte[] message) {
        super(deviceSn, message);
    }

    @Override
    public UnParseBodyMessage build() throws IOException {
        final String deviceSn = ByteUtil.bytesToHex(message, 2, 6);

        this.setDeviceSn(deviceSn);
        this.messageBody = VoidMessageBody.getInstance();
        this.messageHead = new DeviceMessageHead(message, deviceSn, deviceSn, null);
        return this;
    }

    @Override
    public String getMessageId() {
        return this.getDeviceSn();
    }
}
