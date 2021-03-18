package com.iteaj.network.device.client.lighton;

import com.iteaj.iot.client.ClientRequestProtocol;
import com.iteaj.iot.client.udp.UdpRequestProtocol;
import com.iteaj.network.ProtocolType;
import com.iteaj.network.device.consts.SwitchStatus;
import com.iteaj.network.utils.ByteUtil;

public class LightonGetInfoProtocol extends UdpRequestProtocol<LightonMediaMessage> {

    // 中控主音量
    private int volume;
    // 投影仪状态
    private SwitchStatus projector;
    // 功放状态
    private SwitchStatus amp;
    // 设备电源状态
    private SwitchStatus dps;
    // 电子锁状态
    private SwitchStatus lock;
    // 电动幕布 升状态
    private SwitchStatus msUp;
    // 电动幕布 降状态
    private SwitchStatus msDown;
    // 扩展开关1状态
    private SwitchStatus expend1;
    // 扩展开关2状态
    private SwitchStatus expend2;
    // 电脑状态
    private SwitchStatus compute;

    private final static CtrlType query = CtrlType.查询;

    public LightonGetInfoProtocol(String deviceSn) {
        this.setEquipCode(deviceSn);
    }

    @Override
    public ClientRequestProtocol buildRequestMessage() {
        String hex = String.format(query.hex, getEquipCode());
        requestMessage = new LightonMediaMessage(getEquipCode(), ByteUtil.hexToByte(hex));
        return this;
    }

    @Override
    public void doBuildResponseMessage(LightonMediaMessage message) {
        final int bytesToInt = ByteUtil.bytesToInt(message.getMessage(), message.getMessage().length - 6);
        final String s = Integer.toBinaryString(bytesToInt);
        this.volume = getValue(s, 0, 5);

        this.amp = getStatus(s, 10, 2);
        this.dps = getStatus(s, 12, 2);
        this.lock = getStatus(s, 14, 2);
        this.msUp = getStatus(s, 16, 2);
        this.msDown = getStatus(s, 18, 2);
        this.expend1 = getStatus(s, 20, 2);
        this.expend2 = getStatus(s, 22, 2);
        this.compute = getStatus(s, 24, 2);
        this.projector = getStatus(s, 26, 2);
    }

    private SwitchStatus getStatus(String binary, int offset, int len) {
        final int value = getValue(binary, offset, len);
        return value == 0 ? SwitchStatus.off : SwitchStatus.on;
    }

    private int getValue(String binary, int offset, int len) {
        if(offset >= binary.length()) {
            return 0;
        } else {
            final String valStr = binary.substring(binary.length() - offset - len, binary.length() - offset);
            return Integer.valueOf(valStr, 2);
        }
    }

    @Override
    public ProtocolType protocolType() {
        return null;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public SwitchStatus getProjector() {
        return projector;
    }

    public void setProjector(SwitchStatus projector) {
        this.projector = projector;
    }

    public SwitchStatus getAmp() {
        return amp;
    }

    public void setAmp(SwitchStatus amp) {
        this.amp = amp;
    }

    public SwitchStatus getDps() {
        return dps;
    }

    public void setDps(SwitchStatus dps) {
        this.dps = dps;
    }

    public SwitchStatus getLock() {
        return lock;
    }

    public void setLock(SwitchStatus lock) {
        this.lock = lock;
    }

    public SwitchStatus getMsUp() {
        return msUp;
    }

    public void setMsUp(SwitchStatus msUp) {
        this.msUp = msUp;
    }

    public SwitchStatus getMsDown() {
        return msDown;
    }

    public void setMsDown(SwitchStatus msDown) {
        this.msDown = msDown;
    }

    public SwitchStatus getExpend1() {
        return expend1;
    }

    public void setExpend1(SwitchStatus expend1) {
        this.expend1 = expend1;
    }

    public SwitchStatus getExpend2() {
        return expend2;
    }

    public void setExpend2(SwitchStatus expend2) {
        this.expend2 = expend2;
    }

    public SwitchStatus getCompute() {
        return compute;
    }

    public void setCompute(SwitchStatus compute) {
        this.compute = compute;
    }
}
