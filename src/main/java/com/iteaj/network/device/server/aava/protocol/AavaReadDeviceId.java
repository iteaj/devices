package com.iteaj.network.device.server.aava.protocol;

import com.iteaj.network.device.server.aava.AavaMessage;
import com.iteaj.network.device.server.aava.AavaProtocolType;

public class AavaReadDeviceId extends AbstractAavaProtocol {

    public AavaReadDeviceId(String equipCode) {
        super(equipCode);
    }

    public AavaReadDeviceId(String equipCode, String address) {
        super(equipCode, address);
    }

    @Override
    public AavaProtocolType protocolType() {
        return AavaProtocolType.Read_Device_Id;
    }


    @Override
    protected AavaMessage resolverResponseMessage(AavaMessage message) {
        return null;
    }
}
