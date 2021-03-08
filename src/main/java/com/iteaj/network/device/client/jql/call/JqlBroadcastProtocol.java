package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpRequestMessage;

/**
 * 发起广播
 * 对某些设备发起广播,发起广播的设备只能是话筒
 */
public class JqlBroadcastProtocol extends JqlProtocolAbstract{

    private String to;
    private String from;

    public JqlBroadcastProtocol(String baseUrl, String from , String to) {
        super(baseUrl);
        this.to = to;
        this.from = from;
    }

    @Override
    protected void doBuildRequestMessage(HttpRequestMessage message) {

    }

    @Override
    protected void doResolverResponseMessage(JSONObject parse) {

    }

    @Override
    public JqlCallProtocolType protocolType() {
        return JqlCallProtocolType.Broadcast;
    }

    @Override
    public String getUri() {
        return "/api/devices/?action=broadcast&from="+this.from+"&to="+this.to;
    }
}
