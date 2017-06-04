package com.frameDemo.frame;

import java.util.Date;

public class RecvSaveFrameData {

    // char[]接收byte[] FrameData转换为ascii编码
    private char[] chFrameData;

    //userID
    private String userID;

    //deviceID
    private String deviceID;

    //SYNC小端模式 16进制 保持不变
    private byte[] SYNC = new byte[4];

    //测量时间
    private Date measureTime;

    //cardType
    private int cardType;

    //upload
    private int upload;

    private RecvSaveFrameData(FrameData frameData) {

        /**
         * char[]接收byte[] 并转换为ascii编码
         */
        char[] chData = new char[frameData.getData().length];
        for (int i = 0; i < frameData.getData().length; i++) {
            chData[i] = (char) frameData.getData()[i];
        }
        chFrameData = chData;

        //userID
        String sData = String.valueOf(chFrameData);
        userID = (sData.substring(0, 23)).replace("\u0000", "");
        System.out.print("(解析帧)userID=" + userID + " ");
        //deviceID
        deviceID = (sData.substring(24, 31)).replace("\u0000", "");
        System.out.print("deviceID=" + deviceID + " ");
        //SYNC
        System.out.print("SYNC=0x");
        for (int i = 0; i < 4; i++) {
            SYNC[i] = frameData.getData()[32 + i];
            System.out.printf("%x", SYNC[i]);
        }
        //日期measureTime (低位在前，高位在后)
        byte[] year = {frameData.getData()[36], frameData.getData()[37]};
        int Y = ((year[0] & 0xFF) | ((year[1] & 0xFF) << 8));
        int M = frameData.getData()[38];
        int D = frameData.getData()[39];
        int hrs = frameData.getData()[40];
        int min = frameData.getData()[41];
        int sec = frameData.getData()[42];
        measureTime = new Date((Y - 1900), (M - 1), D, hrs, min, sec);
        System.out.print(" measureTime=" + measureTime + " ");
        //cardType
        cardType = getCardType(frameData.getData()[54]);
        System.out.print(" cardType=" + cardType);
        //upload
        upload = frameData.getData()[55];
        System.out.print(" upload=" + upload + " ");

    }

    public static RecvSaveFrameData initRecvSaveFrameData(FrameData frameData) {

        RecvSaveFrameData recvSaveFrameData = new RecvSaveFrameData(frameData);

        return recvSaveFrameData;
    }


    public char[] getChFrameData() {
        return chFrameData;
    }

    public void setChFrameData(char[] chFrameData) {
        this.chFrameData = chFrameData;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public byte[] getSYNC() {
        return SYNC;
    }

    public void setSYNC(byte[] SYNC) {
        this.SYNC = SYNC;
    }

    public Date getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(Date measureTime) {
        this.measureTime = measureTime;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getUpload() {
        return upload;
    }

    public void setUpload(int upload) {
        this.upload = upload;
    }

    /**
     * 转换cardType
     *
     * @param b
     * @return
     */
    private Integer getCardType(byte b) {
        //cardID
        switch (b) {
            case (byte) 0x30://社保卡
                return 1;
            case (byte) 0x31://医保卡
                return 2;
            case (byte) 0x32://自费卡
                return 3;
            case (byte) 0x33://其他卡
                return 4;
            case (byte) 0x3A://自制IC卡
                return 5;
            case (byte) 0x3B://身份证卡
                return 6;
            case (byte) 0x3C://NFC卡
                return 7;
            default:
                return 0;
        }
    }

}
