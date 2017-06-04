package com.frameDemo.frame;

/**
 * 帧首部数据结构 12 bytes
 */
public class FrameHead {

    public static final byte startFlagA = (byte) 0x55; //帧起始标志
    public static final byte startFlagB = (byte) 0xAA; //帧起始标志
    private byte version; //协议版本序列号
    private byte id; //帧ID  确定内容
    private byte[] res; //保留字节
    private byte[] len; //帧长度
    private byte hChk; //帧首部校验和
    private byte dChk; //帧数据校验和

    private int dataLen;

    public int getDataLen() {

        return dataLen;
    }

    private FrameHead() {
        version = 0x01;
        res = new byte[4];
        len = new byte[2];
    }

    public static FrameHead initFrameHead(byte[] bytes) {

        if (bytes.length != 12)
            return null;

        FrameHead head = new FrameHead();
        byte[] res = {bytes[4], bytes[5], bytes[6], bytes[7]};
        byte[] len = {bytes[8], bytes[9]};
        head.setId(bytes[3]);
        head.setRes(res);
        head.setLen(len);
        head.sethChk(bytes[10]);
        head.setdChk(bytes[11]);
        head.dataLen = len[1] * 10 + len[0];
        return head;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public byte[] getRes() {
        return res;
    }

    public void setRes(byte[] res) {
        this.res = res;
    }

    public byte[] getLen() {
        return len;
    }

    public void setLen(byte[] len) {
        this.len = len;
    }

    public byte gethChk() {
        return hChk;
    }

    public void sethChk(byte hChk) {
        this.hChk = hChk;
    }

    public byte getdChk() {
        return dChk;
    }

    public void setdChk(byte dChk) {
        this.dChk = dChk;
    }

    public byte[] getFrameBytes() {

        byte[] h = new byte[]{

                FrameHead.startFlagA, FrameHead.startFlagB, version, id, res[0], res[1], res[2], res[3], len[0], len[1], hChk, dChk,

        };

        return h;
    }
}
