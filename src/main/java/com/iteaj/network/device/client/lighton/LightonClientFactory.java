package com.iteaj.network.device.client.lighton;

import com.iteaj.network.AbstractProtocol;
import com.iteaj.network.ProtocolFactory;

public class LightonClientFactory extends ProtocolFactory<LightonMediaMessage> {

    @Override
    public AbstractProtocol getProtocol(LightonMediaMessage message) {
        final String messageId = message.getHead().getMessageId();
        return remove(messageId);
    }

}
