package com.iteaj.network.device.client.lighton;

import com.iteaj.iot.client.udp.UdpClientComponent;
import com.iteaj.iot.client.udp.UdpNettyClient;
import com.iteaj.network.ProtocolFactory;
import com.iteaj.network.client.ClientMessage;
import org.springframework.stereotype.Component;

@Component
public class LightonUdpComponent extends UdpClientComponent {

    @Override
    protected UdpNettyClient createNettyClient() {
        return new LightonClient(this);
    }

    @Override
    protected ProtocolFactory createProtocolFactory() {
        return new LightonClientFactory();
    }

    @Override
    public String name() {
        return "来同多媒体中控组件";
    }

    @Override
    public Class<? extends ClientMessage> messageClass() {
        return LightonMediaMessage.class;
    }
}
