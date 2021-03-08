package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpRequestMessage;

/**
 * 注销
 */
public class JqlLogoutProtocol extends JqlProtocolAbstract {

    public JqlLogoutProtocol(String url) {
        super(url);
    }

    @Override
    protected void doResolverResponseMessage(JSONObject parse) {

    }

    @Override
    public JqlCallProtocolType protocolType() {
        return JqlCallProtocolType.Logout;
    }

    @Override
    protected void doBuildRequestMessage(HttpRequestMessage message) {

    }

    @Override
    public String getUri() {
        return "/api/users/?action=logout";
    }
}
