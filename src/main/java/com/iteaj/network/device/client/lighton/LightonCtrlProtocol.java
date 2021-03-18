package com.iteaj.network.device.client.lighton;

import com.iteaj.iot.client.ClientRequestProtocol;
import com.iteaj.iot.client.IotClientBootstrap;
import com.iteaj.iot.client.udp.UdpNettyClient;
import com.iteaj.iot.client.udp.UdpRequestProtocol;
import com.iteaj.network.ProtocolType;
import com.iteaj.network.utils.ByteUtil;

public class LightonCtrlProtocol extends UdpRequestProtocol<LightonMediaMessage> {

    private CtrlType type;

    public LightonCtrlProtocol(CtrlType type, String deviceSn) {
        this.type = type;
        this.setEquipCode(deviceSn);
    }

    @Override
    public ClientRequestProtocol buildRequestMessage() {
        String hex = String.format(type.hex, getEquipCode());
        requestMessage = new LightonMediaMessage(getEquipCode(), ByteUtil.hexToByte(hex));
        return this;
    }

    @Override
    public void doBuildResponseMessage(LightonMediaMessage message) {
        /*设备不会有响应*/
    }

    @Override
    public boolean isRelation() {
        return false;
    }

    @Override
    protected UdpNettyClient getIotNettyClient() {
        return IotClientBootstrap.getClient(LightonMediaMessage.class);
    }

    @Override
    public ProtocolType protocolType() {
        return null;
    }

    public CtrlType getType() {
        return type;
    }

    public void setType(CtrlType type) {
        this.type = type;
    }
}
