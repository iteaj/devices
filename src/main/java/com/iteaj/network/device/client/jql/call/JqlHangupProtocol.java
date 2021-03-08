package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpRequestMessage;

/**
 * 挂断呼叫
 */
public class JqlHangupProtocol extends JqlProtocolAbstract{

    /**
     * 主叫设备Id
     */
    private String from;

    public JqlHangupProtocol(String baseUrl, String from) {
        super(baseUrl);
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
        return JqlCallProtocolType.Hangup;
    }

    @Override
    public String getUri() {
        return "/api/devices/?action=hangup&from="+this.getFrom();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
