package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpRequestMessage;

public class JqlCallProtocol extends JqlProtocolAbstract{

    /**
     * 被呼叫的设备Id
     */
    private String to;
    /**
     * 发起呼叫的设备Id
     */
    private String from;

    public JqlCallProtocol(String baseUrl, String from, String to) {
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
        return JqlCallProtocolType.Call;
    }

    @Override
    public String getUri() {
        return "/api/devices/?action=call&from="+this.from+"&to="+this.to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
