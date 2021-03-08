package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpRequestMessage;

/**
 * 取消订阅
 */
public class JqlUnsubscribeProtocol extends JqlProtocolAbstract{

    private String subscribeId;

    public JqlUnsubscribeProtocol(String baseUrl, String subscribeId) {
        super(baseUrl);
        this.subscribeId = subscribeId;
    }

    @Override
    protected void doBuildRequestMessage(HttpRequestMessage message) {

    }

    @Override
    protected void doResolverResponseMessage(JSONObject parse) {

    }

    @Override
    public JqlCallProtocolType protocolType() {
        return JqlCallProtocolType.Unsubscribe;
    }

    @Override
    public String getUri() {
        return "/api/events/?action=unsubscribe&subscribeId="+subscribeId;
    }
}
