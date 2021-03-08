package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpRequestMessage;

/**
 * 订阅事件
 */
public class JqlSubscribeProtocol extends JqlProtocolAbstract{

    /**
     * 订阅回调的地址
     * 主要不要被权限拦截器拦截
     */
    private String callUrl;
    private SubscribeType type;
    private String subscribeId;
    public JqlSubscribeProtocol(String baseUrl, SubscribeType type, String callUrl) {
        super(baseUrl);
        this.callUrl = callUrl;
        this.type = type;
    }

    /**
     * 默认订阅所有事件
     * @param baseUrl
     * @param callUrl
     */
    public JqlSubscribeProtocol(String baseUrl, String callUrl) {
        super(baseUrl);
        this.callUrl = callUrl;
        this.type = SubscribeType.all;
    }

    @Override
    protected void doBuildRequestMessage(HttpRequestMessage message) {

    }

    @Override
    protected void doResolverResponseMessage(JSONObject parse) {
        if(isOk()) {
            this.subscribeId = parse.getString("subscribeId");
        }
    }

    @Override
    public JqlCallProtocolType protocolType() {
        return JqlCallProtocolType.Subscribe;
    }

    @Override
    public String getUri() {
        return "/api/events/?action=subscribe&type="+this.getType()+"&url="+this.getCallUrl();
    }

    public String getCallUrl() {
        return callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }

    public SubscribeType getType() {
        return type;
    }

    public void setType(SubscribeType type) {
        this.type = type;
    }

    public String getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId;
    }
}
