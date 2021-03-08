package com.iteaj.network.device.client.jql.call;

import com.iteaj.network.ProtocolType;

public enum JqlCallProtocolType implements ProtocolType {
    Login("登录"),
    Logout("注销"),
    Call("呼叫设备"),
    Hangup("取消呼叫"),
    Broadcast("广播"),
    Subscribe("事件订阅"),
    Unsubscribe("取消订阅"),
    RenewSubscription("续订"),
    DeviceInfo("获取设备信息"),
    Devices("获取设备列表信息"),
    ;

    private String desc;

    JqlCallProtocolType(String desc) {
        this.desc = desc;
    }

    @Override
    public Enum getType() {
        return this;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
