package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpRequestMessage;

/**
 * 续订
 * @see JqlSubscribeProtocol 订阅
 * @see JqlUnsubscribeProtocol 取消订阅
 */
public class JqlRenewSubscription extends JqlProtocolAbstract{

    private String subscribeId;

    public JqlRenewSubscription(String baseUrl, String subscribeId) {
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
        return JqlCallProtocolType.RenewSubscription;
    }

    @Override
    public String getUri() {
        return "/api/events/?action=renew_subscription&subscribeId="+subscribeId;
    }
}
