package com.iteaj.network.device.client.lighton;

import com.iteaj.iot.client.udp.UdpClientComponent;
import com.iteaj.iot.client.udp.UdpNettyClient;
import com.iteaj.network.ProtocolFactory;
import com.iteaj.network.client.ClientMessage;
import com.iteaj.network.device.client.DeviceClientProperties;

public class LightonMediaCUComponent extends UdpClientComponent {

    private DeviceClientProperties.ClientConfig config;

    public LightonMediaCUComponent(DeviceClientProperties.ClientConfig config) {
        this.config = config;
    }

    @Override
    protected UdpNettyClient createNettyClient() {
        return new LightonClient(this, config.getPort(), config.getHost());
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
