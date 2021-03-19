package com.iteaj.network.device.client.lighton;

import com.iteaj.iot.client.udp.DatagramPacketToMessageDecoder;
import com.iteaj.iot.client.udp.UdpClientComponent;
import com.iteaj.iot.client.udp.UdpNettyClient;
import io.netty.channel.nio.NioEventLoopGroup;

public class LightonClient extends UdpNettyClient {

    private int port;
    private String host;

    public LightonClient(UdpClientComponent clientComponent, int port, String host) {
        super(clientComponent);
        this.port = port;
        this.host = host;
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
        return this.port;
    }

    @Override
    public String getHost() {
        return this.host;
    }
}
