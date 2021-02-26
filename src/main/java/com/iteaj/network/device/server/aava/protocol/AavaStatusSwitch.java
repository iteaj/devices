package com.iteaj.network.device.server.aava.protocol;

import com.iteaj.network.device.server.aava.AavaMessage;
import com.iteaj.network.device.server.aava.AavaProtocolType;

/**
 * 切换声光报警器的开或关(旧版)
 */
public class AavaStatusSwitch extends AbstractAavaProtocol {

    private StatusSwitch status;

    public AavaStatusSwitch(String equipCode, StatusSwitch status) {
        super(equipCode);
        this.status = status;
    }

    public AavaStatusSwitch(String equipCode, String address, StatusSwitch status) {
        super(equipCode, address);
        this.status = status;
    }

    @Override
    public AavaProtocolType protocolType() {
        return AavaProtocolType.Switch;
    }

    @Override
    protected String getData() {
        return this.status.code;
    }

    @Override
    protected AavaMessage resolverResponseMessage(AavaMessage message) {
        return null;
    }

    public StatusSwitch getStatus() {
        return status;
    }

    public void setStatus(StatusSwitch status) {
        this.status = status;
    }
}
