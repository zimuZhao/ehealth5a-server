package com.frameDemo.utils;

import com.frameDemo.frame.FrameHead;
import com.frameDemo.frame.Frame;
import com.frameDemo.frame.FrameData;

public class FrameCheckSum {

    /**
     * 帧首生成
     *
     * @param head
     * @return
     */
    public static byte getFrameHeadCheckSum(FrameHead head) {

        byte checkSum;
        byte[] res = head.getRes();
        byte[] len = head.getLen();

        checkSum = (byte) (FrameHead.startFlagA + FrameHead.startFlagB +
                head.getVersion() + head.getId() + res[0] + res[1] + res[2] +
                res[3] + len[0] + len[1] + head.getdChk());

        return (byte) (~checkSum + 1);
    }

    /**
     * 帧首校验
     *
     * @param head 帧首
     * @return
     */
    public static boolean isFrameHeadCheckSumOK(FrameHead head) {

        int checkSum;
        byte[] res = head.getRes();
        byte[] len = head.getLen();

        checkSum = FrameHead.startFlagA + FrameHead.startFlagB +
                head.getVersion() + head.getId() + res[0] + res[1] + res[2] +
                res[3] + len[0] + len[1] + head.getdChk() + head.gethChk();

        return ((byte) checkSum == 0);
    }

    /**
     * 帧数据生成 D_CHK
     *
     * @param data
     * @return
     */
    public static byte getFrameDataCheckSum(FrameData data) {

        byte checkSum = 0;
        for (byte b : data.getData())
            checkSum = (byte) (checkSum + b);

        return (byte) ((~checkSum) + 1);
    }

    /**
     * 帧数据校验
     * 避免求和溢出导致校验不为0 （除 字节和得 0x00，取反得 0xff,加一得 0x00, 0x00+0x00=0x00,不溢出 但此时依旧使用本公式）
     * 取反（溢出原因）
     *
     * @param frame
     * @return
     */
    public static boolean isFrameDataCheckSumOK(Frame frame) {

        return (frame.getHead().getdChk() == frame.getData().getCheckSum());
    }

}
