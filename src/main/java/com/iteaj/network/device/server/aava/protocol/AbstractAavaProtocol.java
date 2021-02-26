package com.iteaj.network.device.server.aava.protocol;

import com.iteaj.network.device.elfin.ElfinMessageBody;
import com.iteaj.network.device.elfin.ElfinMessageHeader;
import com.iteaj.network.device.server.aava.AavaMessage;
import com.iteaj.network.device.server.aava.AavaProtocolType;
import com.iteaj.network.server.protocol.PlatformRequestProtocol;
import com.iteaj.network.utils.ByteUtil;

import java.io.IOException;

public abstract class AbstractAavaProtocol extends PlatformRequestProtocol<AavaMessage> {

    /**
     * 默认的设备地址: 01
     * 设备地址最大为 FF，编号为 255
     */
    private String address;

    public AbstractAavaProtocol(String equipCode) {
        this(equipCode, "01");
    }

    public AbstractAavaProtocol(String equipCode, String address) {
        super(equipCode);
        this.address = address;
    }

    @Override
    protected String doGetMessageId() {
        return null;
    }

    @Override
    protected AavaMessage doBuildRequestMessage() throws IOException {
        StringBuilder sb = new StringBuilder(this.getAddress()).append(protocolType()
                .getCode()).append(protocolType().getStart())
                .append(protocolType().getNum()).append(getData());

        // 追加Crc校验位
        final String crc = getCRC(ByteUtil.hexToByte(sb.toString()));
        sb.append(crc);

        byte[] messageBody = ByteUtil.hexToByte(sb.toString());
        final ElfinMessageBody elfinMessageBody = new ElfinMessageBody(messageBody);
        final ElfinMessageHeader messageHeader = new ElfinMessageHeader(getEquipCode(), protocolType());

        return new AavaMessage(messageHeader, elfinMessageBody);
    }

    /**
     * 返回要设置的数据内容(十六进制格式)
     * @return
     */
    protected String getData() {
        return "";
    }

    @Override
    public abstract AavaProtocolType protocolType();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 低位在前, 高位在后
     * @param bytes
     * @return
     */
    public static String getCRC(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }

        //结果转换为16进制
        String result = Integer.toHexString(CRC).toUpperCase();
        if (result.length() != 4) {
            StringBuffer sb = new StringBuffer("0000");
            result = sb.replace(4 - result.length(), 4, result).toString();
        }

        return result.substring(2, 4) + result.substring(0, 2);
    }
}
