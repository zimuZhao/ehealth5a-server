package com.frameDemo.frame;

import com.frameDemo.utils.FrameCheckSum;

public class FrameData {

    // 数据帧长度，即字节数
    private int dataLen;

    //check sum
    private byte checkSum;

    // 数据
    private byte[] data;


    private FrameData(int dataLen, byte[] bytes){

        this.dataLen = dataLen;
        data = bytes;
        checkSum = 0;
    }


    public static FrameData initFrameData(byte[] bytes){

        FrameData data = new FrameData(bytes.length, bytes);
        data.setCheckSum(FrameCheckSum.getFrameDataCheckSum(data));
        return data;
    }


    public int getDataLen() {
        return dataLen;
    }

    public void setDataLen(int dataLen) {
        this.dataLen = dataLen;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte getCheckSum() {
        return checkSum;
    }

    private void setCheckSum(byte checkSum) {
        this.checkSum = checkSum;
    }
}
