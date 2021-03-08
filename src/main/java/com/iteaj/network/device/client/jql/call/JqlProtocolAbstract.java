package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.*;

public abstract class JqlProtocolAbstract extends HttpClientProtocol {

    private String msg;
    protected JSONObject response;

    public JqlProtocolAbstract(String baseUrl) {
        this(baseUrl, null);
    }

    public JqlProtocolAbstract(String baseUrl, String deviceSn) {
        super(baseUrl, deviceSn);
    }

    protected HttpRequestMessage doBuildRequestMessage() {
        // 请求的url = baseUrl + uri
        String url = getUrl() + getUri();

        final HttpRequestMessage message = new HttpRequestMessage(url, getMethod());
        this.doBuildRequestMessage(message);

        // 如果未授权, 则重新授权
        String token = JqlTokenManager.getInstance().getToken(getUrl());
        if(token == null && !getUri().contains("action=login")) {
            synchronized (JqlProtocolAbstract.class) {
                token = JqlTokenManager.getInstance().getToken(getUrl());
                if(token == null) {
                    final JqlLoginProtocol request = new JqlLoginProtocol(this.getUrl()).sync(10).request();
                    if (!request.isOk()) {
                        throw new HttpProtocolException("未授权, 且调用认证协议[" +
                                JqlLoginProtocol.class.getName() + "]认证失败" + request.getMsg());
                    }

                    token = request.getToken();
                    JqlTokenManager.getInstance().addToken(getUrl(), token);
                }
            }
        }

        // 将token放入到Http Header里面
        if(token != null) {
            if(getMethod() == HttpMethod.Get) {
                message.addParam("token", token);
            } else {
                message.setUrl(url + "&token=" + token);
            }
        }

        return message;
    }

    protected HttpMethod getMethod() {
        return HttpMethod.Post;
    }

    protected abstract void doBuildRequestMessage(HttpRequestMessage message);

    @Override
    protected void resolverResponseMessage(HttpResponseMessage responseMessage) {
        final byte[] message = responseMessage.getMessage();
        if(responseMessage.isSuccessful()) {

            this.response = (JSONObject) JSONObject.parse(message);
            this.msg = this.response.getString("message");

            doResolverResponseMessage(this.response);
        } else {
            this.msg = new String(message);
        }
    }

    public boolean isOk() {
        return response != null && "success".equals(response.getString("status"));
    }

    protected abstract void doResolverResponseMessage(JSONObject parse);

    @Override
    public abstract JqlCallProtocolType protocolType();

    public abstract String getUri();

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public <T> T getValue(String key) {
        return (T) this.response.get(key);
    }
}
