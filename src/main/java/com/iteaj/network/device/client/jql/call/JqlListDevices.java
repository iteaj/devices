package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpMethod;
import com.iteaj.iot.client.http.HttpRequestMessage;

/**
 * 获取设备列表
 */
public class JqlListDevices extends JqlProtocolAbstract{

    public JqlListDevices(String baseUrl) {
        super(baseUrl);
    }

    @Override
    protected void doBuildRequestMessage(HttpRequestMessage message) {
        message.addParam("flat", "false").addParam("level", "0").addParam("ref", "2");
    }

    @Override
    protected void doResolverResponseMessage(JSONObject parse) {
        System.out.println(parse);
    }

    @Override
    public JqlCallProtocolType protocolType() {
        return JqlCallProtocolType.Devices;
    }

    /**
     * start	string	可选，起始节点设备 id 或者组名称。如没有指定，则表示从根节点开始遍历。
     * ref	integer	可选，0：获取祖先 1：获取兄弟 2：获取后代
     * flat	boolean	当 true，返回的结果是一个一维数组。false 时返回树状JSON 格式。
     * level	integer	限定树查询级别，如果有 level=0 则不限制树的层级，将返回整棵设备树。1-n 代表限定返回树的级别。如 level=1 只返回该设备的第一层级的设备清单，level=2 返回该设备
     * 和该设备下一级设备清单
     * @return
     */
    @Override
    public String getUri() {
        return "/api/devices/";
    }

    @Override
    protected HttpMethod getMethod() {
        return HttpMethod.Get;
    }

    public JSONObject getPayload() {
        return this.response;
    }
}
