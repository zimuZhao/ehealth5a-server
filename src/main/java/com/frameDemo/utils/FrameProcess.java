package com.frameDemo.utils;

import com.frameDemo.frame.Frame;
import com.frameDemo.frame.FrameData;
import com.frameDemo.frame.FrameHead;

import java.util.Calendar;

/**
 * 服务器响应帧
 */
public class FrameProcess {

    public static String getProcessResult() {

        return "帧处理结果好了，确认信息是： XXXXXXXXX";
    }

    /**
     * 生理参数接收确认返回帧
     *
     * @param frameHead 接收到的帧首数据
     * @param D_SYNC    生理参数接收确认
     * @return 回传帧
     */
    public static Frame sendDSYNC(FrameHead frameHead, byte[] D_SYNC) {
        System.out.println("\n@ 生理参数接收确认 回复数据准备发送...");

        //命令帧 0x81表示生理参数接收确认
        frameHead.setId((byte) 0x81);
        //帧长度 固定16bytes
        byte[] len = {(byte) 0x10, (byte) 0x00};
        frameHead.setLen(len);
        //SYNC
        FrameData frameData = FrameData.initFrameData(D_SYNC);
        //D_CHK
        byte dCHK = FrameCheckSum.getFrameDataCheckSum(frameData);
        frameHead.setdChk(dCHK);
        //H_CHK
        byte hCHK = FrameCheckSum.getFrameHeadCheckSum(frameHead);
        frameHead.sethChk(hCHK);

        System.out.print("(返回帧):");

        Frame frame = Frame.initFrame(frameHead, frameData);
        for (byte b : frame.getFrameBytes()) {
            System.out.printf("0x%x ", b);
        }

        System.out.println();
        System.out.println("@ 生理参数接收确认 返回帧发送完毕");

        return frame;
    }

    /**
     * 时间同步确认返回帧
     *
     * @param frameHead 接收到的帧首数据
     * @return 回传帧
     */
    public static Frame sendTimeSync(FrameHead frameHead) {
        System.out.println("\n@ 时间同步确认 回复数据准备发送...");

        //命令帧 0x80表示时间同步
        frameHead.setId((byte) 0x80);
        //帧长度 固定20bytes
        byte[] len = {(byte) 0x14, (byte) 0x00};
        frameHead.setLen(len);
        //TIME时间
        byte[] Time = new byte[8];
        Calendar current = Calendar.getInstance();
        //测试数据
//        current.set(2015, 10, 9, 9, 8, 33);
        //获取年份 (低位在前，高位在后)
        int year = current.get(Calendar.YEAR);
        Time[0] = (byte) ((year & 0x000000FF));
        Time[1] = (byte) ((year & 0x0000FF00) >> 8);
        //获取月份
        int month = current.get(Calendar.MONTH) + 1;
        Time[2] = (byte) (month & 0xFF);
        //获取日
        int day = current.get(Calendar.DATE);
        Time[3] = (byte) (day & 0xFF);
        //时
        int hour = current.get(Calendar.HOUR_OF_DAY);
        Time[4] = (byte) (hour & 0xFF);
        //分
        int minute = current.get(Calendar.MINUTE);
        Time[5] = (byte) (minute & 0xFF);
        //秒
        int second = current.get(Calendar.SECOND);
        Time[6] = (byte) (second & 0xFF);
        //保留字节 测试数据是0x02
        Time[7] = 0x00;

        //TIME
        FrameData frameData = FrameData.initFrameData(Time);
        //D_CHK
        byte dCHK = FrameCheckSum.getFrameDataCheckSum(frameData);
        frameHead.setdChk(dCHK);
        //H_CHK
        byte hCHK = FrameCheckSum.getFrameHeadCheckSum(frameHead);
        frameHead.sethChk(hCHK);

        System.out.print("(返回帧):");

        Frame frame = Frame.initFrame(frameHead, frameData);
        for (byte b : frame.getFrameBytes()) {
            System.out.printf("0x%x ", b);
        }

        System.out.println();
        System.out.println("@ 时间同步确认 返回帧发送完毕");

        return frame;
    }

    /**
     * 用户求助确认返回帧
     *
     * @param frameHead 接收到的帧首数据
     * @param D_SYNC    求助次数接收确认
     * @return 回传帧
     */
    public static Frame sendForHelp(FrameHead frameHead, byte[] D_SYNC) {
        System.out.println("\n@ 用户求助接收确认 回复数据准备发送...");

        //命令帧 0x82表示用户求助次数接收确认(通讯协议中木有写帧命令)
        frameHead.setId((byte) 0x82);
        //帧长度 固定16bytes （通讯协议中木有帧长度...按测试示例是16bytes）
        byte[] len = {(byte) 0x10, (byte) 0x00};
        frameHead.setLen(len);
        //SYNC
        FrameData frameData = FrameData.initFrameData(D_SYNC);
        //D_CHK
        byte dCHK = FrameCheckSum.getFrameDataCheckSum(frameData);
        frameHead.setdChk(dCHK);
        //H_CHK
        byte hCHK = FrameCheckSum.getFrameHeadCheckSum(frameHead);
        frameHead.sethChk(hCHK);

        System.out.print("(返回帧):");

        Frame frame = Frame.initFrame(frameHead, frameData);
        for (byte b : frame.getFrameBytes()) {
            System.out.printf("0x%x ", b);
        }

        System.out.println();
        System.out.println("@ 用户求助接收确认 返回帧发送完毕");

        return frame;
    }

    /**
     * 用户授权列表时间戳确认返回帧
     *
     * @param frameHead 接收到的帧首数据
     * @return 回传帧
     */
    public static Frame sendUserAuthorise(FrameHead frameHead) {
        System.out.println("\n@ 用户授权列表时间戳接收确认 回复数据准备发送...");

        //命令帧 0x91表示用户授权列表时间戳接收确认
        frameHead.setId((byte) 0x91);
        //帧长度 固定28bytes
        byte[] len = {(byte) 0x1c, (byte) 0x00};
        frameHead.setLen(len);

        //RES 8bytes + Time 8bytes  这里保留字节按照测试示例给出
        byte[] data = {0x00, 0x00, 0x00, 0x00, 0x01, 0x2B, 0x2F, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        Calendar current = Calendar.getInstance();
        //测试数据
//        current.set(2012, 3, 24, 8, 41, 00);
        //获取年份 (低位在前，高位在后)
        int year = current.get(Calendar.YEAR);
        data[8] = (byte) ((year & 0x000000FF));
        data[9] = (byte) ((year & 0x0000FF00) >> 8);
        //获取月份
        int month = current.get(Calendar.MONTH) + 1;
        data[10] = (byte) (month & 0xFF);
        //获取日
        int day = current.get(Calendar.DATE);
        data[11] = (byte) (day & 0xFF);
        //时
        int hour = current.get(Calendar.HOUR);
        data[12] = (byte) (hour & 0xFF);
        //分
        int minute = current.get(Calendar.MINUTE);
        data[13] = (byte) (minute & 0xFF);
        //秒
        int second = current.get(Calendar.SECOND);
        data[14] = (byte) (second & 0xFF);
        //保留字节
        data[15] = 0x00;

        FrameData frameData = FrameData.initFrameData(data);
        //D_CHK
        byte dCHK = FrameCheckSum.getFrameDataCheckSum(frameData);
        frameHead.setdChk(dCHK);
        //H_CHK
        byte hCHK = FrameCheckSum.getFrameHeadCheckSum(frameHead);
        frameHead.sethChk(hCHK);

        System.out.print("(返回帧):");

        Frame frame = Frame.initFrame(frameHead, frameData);
        for (byte b : frame.getFrameBytes()) {
            System.out.printf("0x%x ", b);
        }

        System.out.println();
        System.out.println("@ 用户授权列表时间戳接收确认 返回帧发送完毕");

        return frame;
    }

}
