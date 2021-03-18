package com.iteaj.network.device.client.lighton;

import com.iteaj.iot.client.udp.DatagramPacketToMessageDecoder;
import com.iteaj.iot.client.udp.UdpClientComponent;
import com.iteaj.iot.client.udp.UdpNettyClient;
import io.netty.channel.nio.NioEventLoopGroup;

public class LightonClient extends UdpNettyClient {

    public LightonClient(UdpClientComponent clientComponent) {
        super(clientComponent);
    }

    @Override
    public void init(NioEventLoopGroup clientGroup) {
        super.init(clientGroup);
    }

    @Override
    protected DatagramPacketToMessageDecoder initClientRequestDecoder() {
        return new UdpDatagramPacketDecoder();
    }

    @Override
    public int getPort() {
        return 4001;
    }

    @Override
    public String getHost() {
        return "114.215.156.78";
    }
}
