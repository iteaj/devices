package com.iteaj.network.device.client.lighton;

import com.iteaj.iot.client.udp.DatagramPacketToMessageDecoder;
import com.iteaj.network.message.UnParseBodyMessage;

public class UdpDatagramPacketDecoder extends DatagramPacketToMessageDecoder {

    @Override
    protected UnParseBodyMessage channelReadMessage(byte[] message) {
        return new LightonMediaMessage(message);
    }
}
