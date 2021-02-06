package com.iteaj.network.device.server.gps.protocol;

import com.iteaj.network.device.server.gps.GpsMessage;
import com.iteaj.network.device.server.gps.GpsProtocolType;

import java.io.IOException;

/**
 * create time: 2021/1/27
 *  平台主动向设备获取位置信息协议
 * @author iteaj
 * @since 1.0
 */
public class GetReportProtocol extends GpsPlatformRequestProtocol {

    private String messageId;

    public GetReportProtocol(String equipCode, String messageId) {
        super(equipCode);
        this.messageId = (Integer.valueOf(messageId) + 1) + "";
    }

    @Override
    public GpsProtocolType protocolType() {
        return GpsProtocolType.QPInfo;
    }

    @Override
    protected GpsMessage doBuildRequestMessage() throws IOException {
        return GpsMessage.buildPlatformEmptyBodyReqMessage(getEquipCode(), messageId, protocolType());
    }

    @Override
    protected GpsMessage resolverResponseMessage(GpsMessage message) {
        System.out.println("位置信息查询应答: " + message);
        return null;
    }

}
