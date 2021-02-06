package com.iteaj.network.device.server.gps.protocol;

import com.iteaj.network.device.server.gps.GpsMessage;
import com.iteaj.network.device.server.gps.GpsProtocolType;
import com.iteaj.network.server.protocol.PlatformRequestProtocol;

import java.io.IOException;

/**
 * create time: 2021/1/27
 *
 * @author iteaj
 * @since 1.0
 */
public abstract class GpsPlatformRequestProtocol extends PlatformRequestProtocol<GpsMessage> {

    public GpsPlatformRequestProtocol(String equipCode) {
        super(equipCode);
    }

    @Override
    public abstract GpsProtocolType protocolType();

    @Override
    protected String doGetMessageId() {
        return null;
    }
}
