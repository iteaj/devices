package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpRequestMessage;

public class JqlLoginProtocol extends JqlProtocolAbstract {

    private String token;

    public JqlLoginProtocol(String url) {
        super(url);
    }

    @Override
    protected void doResolverResponseMessage(JSONObject parse) {
        this.token = parse.getString("token");
    }

    @Override
    protected void doBuildRequestMessage(HttpRequestMessage message) {

    }

    @Override
    public JqlCallProtocolType protocolType() {
        return JqlCallProtocolType.Login;
    }

    @Override
    public String getUri() {
        return "/api/users/?action=login&username=apiGuest&key=06fc7c078919c6be6a5d8611efa3b4b6";
    }

    public String getToken() {
        return token;
    }
}
