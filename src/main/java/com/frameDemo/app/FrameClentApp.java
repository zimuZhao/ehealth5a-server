package com.frameDemo.app;

import com.frameDemo.frame.Frame;
import com.frameDemo.frame.FrameData;
import com.frameDemo.frame.FrameHead;
import com.frameDemo.socket.SokectDemoClient;

import java.io.IOException;

public class FrameClentApp {


    private static SokectDemoClient client;

    private static Frame xueyangFrame;
    private static Frame xueyaFrame;
    private static Frame xuetangFrame;
    private static Frame bmiFrame;
    private static Frame tempFrame;
    private static Frame yaotunFrame;
    private static Frame niaosuanFrame;
    private static Frame danguchunFrame;
    private static Frame xuehongdanbaFrame;
    private static FrameHead shijiantongbuFrame;
    private static Frame qiuzhuFrame;
    private static Frame shouquanshijianFrame;

    static {

        /**
         * 血氧数据
         * [帧首部解析][VER=1][ID=0x80][LEN=68 字节][H_CHK=0xEA][D_CHK=0x52]
         * [帧数据解析][UID=10310301001000193x][DID=8866][SYNC=0x00000008]
         * [TIME=2015/11/0 14:29:49][SPO2=98%][PR=80bpm]
         */
        xueyangFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x80, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, (byte) 0xEA, 0x52
        }), FrameData.initFrameData(new byte[]{0x31, 0x30, 0x33, 0x31, 0x30, 0x33, 0x30, 0x31, 0x30, 0x30, 0x31, 0x30, 0x30,
                0x30, 0x31, 0x39, 0x33, 0x78, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x38, 0x38,
                0x36, 0x36, 0x00, 0x00, 0x00, 0x00, 0x08, 0x00, 0x00, 0x00, (byte) 0xDF, 0x07, 0x0B,
                0x06, 0x0E, 0x1D, 0x31, 0x00, 0x62, (byte) 0xCC, 0x50, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x3A, 0x00
        }));

        xueyaFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x81, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, (byte) 0x18, 0x23
        }), FrameData.initFrameData(new byte[]{0x32, 0x30, 0x31, 0x35, 0x36, 0x32, 0x39, 0x31, 0x37, 0x30,
                0x31, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x06, 0x00, 0x00, 0x00,
                (byte) 0xDF, 0x07, 0x0B, 0x06, 0x0D, 0x3B, 0x16, 0x00,
                (byte) 0x78, 0x00, 0x4B, 0x00,
                0x53, 0x00, 0x00, 0x00, 0x00, 0x00, 0x3A, 0x00
        }));


        xuetangFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x82, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, (byte) 0xA8, (byte) 0x92
        }), FrameData.initFrameData(new byte[]{0x31, 0x30, 0x33, 0x31, 0x30, 0x33, 0x30, 0x31, 0x30, 0x30, 0x31, 0x30, 0x30,
                0x30, 0x31, 0x39, 0x33, 0x78, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x38, 0x38,
                0x36, 0x36, 0x00, 0x00, 0x00, 0x00, 0x07, 0x00, 0x00, 0x00, (byte) 0xDF, 0x07, 0x0B,
                0x06, 0x0E, 0x19, 0x10, 0x00, (byte) 0xCD, (byte) 0xCC, (byte) 0x8C, 0x3F, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x3A, 0x00
        }));

        bmiFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x83, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, (byte) 0x91, (byte) 0xA8
        }), FrameData.initFrameData(new byte[]{0x31, 0x30, 0x33, 0x31, 0x30, 0x33, 0x30, 0x31, 0x30, 0x30, 0x31, 0x30, 0x30,
                0x30, 0x31, 0x39, 0x33, 0x78, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x38, 0x38, 0x36, 0x36, 0x00, 0x00, 0x00, 0x00, 0x0F, 0x00, 0x00, 0x00,
                (byte) 0xDF, 0x07, 0x0B, 0x09, 0x08, 0x3A, 0x1B, 0x00, (byte) 0xA5, 0x3C, 0x00, 0x00,
                (byte) 0xFD, 0x4E, (byte) 0xB0, 0x41, 0x00, 0x00, 0x3A, 0x00
        }));

        tempFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x84, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, 0x47, (byte) 0xF1
        }), FrameData.initFrameData(new byte[]{0x31, 0x30, 0x33, 0x31, 0x30, 0x33, 0x30, 0x31, 0x30, 0x30, 0x31, 0x30, 0x30,
                0x30, 0x31, 0x39, 0x33, 0x78, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x38, 0x38, 0x36, 0x36, 0x00, 0x00, 0x00, 0x00,
                0x0A, 0x00, 0x00, 0x00,
                (byte) 0xDF, 0x07, 0x0B, 0x06, 0x0E, 0x2F, 0x33, 0x00,
                (byte) 0x24, 0x00, 0x64, 0x41,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x3A, 0x00
        }));

        yaotunFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x85, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, 0x1A, (byte) 0x1D
        }), FrameData.initFrameData(new byte[]{0x31, 0x30, 0x33, 0x31, 0x30, 0x33, 0x30, 0x31, 0x30, 0x30, 0x31, 0x30, 0x30,
                0x30, 0x31, 0x39, 0x33, 0x78, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x38, 0x38, 0x36, 0x36, 0x00, 0x00, 0x00, 0x00,
                0x0A, 0x00, 0x00, 0x00,
                (byte) 0xDF, 0x07, 0x0B, 0x09, 0x08, 0x3A, 0x20, 0x00,
                (byte) 0x41, 0x00, 0x5F, 0x00,
                0x6C, 0x28, 0x2F, 0x3F, 0x00, 0x00, 0x3A, 0x00
        }));

        niaosuanFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x95, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, (byte) 0x2B, (byte) 0xFC
        }), FrameData.initFrameData(new byte[]{0x32, 0x30, 0x31, 0x35, 0x36, 0x32, 0x39, 0x31, 0x37, 0x30,
                0x31, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, (byte) 0xDF, 0x07, 0x0B,
                0x06, 0x0A, 0x3A, 0x34, 0x00, (byte) 0xAD, (byte) 0x90, (byte) 0xAA, 0x3E, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x3A, 0x00
        }));

        danguchunFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x96, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, (byte) 0xD9, (byte) 0x4D
        }), FrameData.initFrameData(new byte[]{0x31, 0x30, 0x33, 0x31, 0x30, 0x33, 0x30, 0x31, 0x30, 0x30, 0x31, 0x30, 0x30,
                0x30, 0x31, 0x39, 0x33, 0x78, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x38, 0x38,
                0x36, 0x36, 0x00, 0x00, 0x00, 0x00, 0x09, 0x00, 0x00, 0x00, (byte) 0xDF, 0x07, 0x0B,
                0x06, 0x0A, 0x3B, 0x2F, 0x00, (byte) 0xAA, (byte) 0xA7, (byte) 0x46, 0x40, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x3A, 0x00
        }));

        xuehongdanbaFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x86, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, (byte) 0x8B, (byte) 0xAB
        }), FrameData.initFrameData(new byte[]{0x32, 0x30, 0x31, 0x35, 0x36, 0x32, 0x39, 0x31, 0x37, 0x30,
                0x31, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x09, 0x00, 0x00, 0x00, (byte) 0xDF, 0x07, 0x0B,
                0x06, 0x0A, 0x3B, 0x2F, 0x00, (byte) 0xCD, (byte) 0xCC, (byte) 0x64, 0x41, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x3A, 0x00
        }));

        shijiantongbuFrame = FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x90, 0x00, 0x00, 0x00, 0x00, 0x0C, 0x00, 0x64, 0x00
        });

        qiuzhuFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x91, 0x00, 0x00, 0x00, 0x00, 0x44, 0x00, (byte) 0x7E, (byte) 0xAD
        }), FrameData.initFrameData(new byte[]{0x31, 0x30, 0x33, 0x31, 0x30, 0x33, 0x30, 0x31, 0x30, 0x30, 0x31, 0x30, 0x30,
                0x30, 0x31, 0x39, 0x33, 0x78, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x38, 0x38,
                0x36, 0x36, 0x00, 0x00, 0x00, 0x00, (byte) 0x0E, 0x00, 0x00, 0x00, (byte) 0xDF, 0x07, 0x0B,
                0x09, 0x08, 0x39, 0x35, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x3A, 0x00
        }));

        shouquanshijianFrame = Frame.initFrame(FrameHead.initFrameHead(new byte[]{
                0x55, (byte) 0xAA, 0x01, (byte) 0x92, 0x00, 0x00, 0x00, 0x00, 0x1C, 0x00, (byte) 0x6D, (byte) 0xE5
        }), FrameData.initFrameData(new byte[]{0x38, 0x38, 0x36, 0x36, 0x00, 0x00, 0x00, 0x00, (byte) 0xDF, 0x07, 0x0B, 0x06,
                0x0A, 0x32, 0x0C, 0x00
        }));
    }

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private static void usage() {

        System.out.println("操作提示：");
        System.out.println("\t -h <server host> -p <server port>");
    }

    public static void main(String[] args) {

        if (args.length < 4) {
            usage();
            System.exit(0);
        }

        if (!args[0].equals("-h") || !args[2].equals("-p")) {
            usage();
            System.exit(0);
        }

        String host = args[1];

        int port = -1;

        try {
            port = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            usage();
            System.exit(0);
        }

        if (!(0 < port && port < 65536)) {
            usage();
            System.exit(0);
        }

        FrameClentApp app = new FrameClentApp();

        app.setHost(host);
        app.setPort(port);

        byte[] serverReturn;

        while (true) {
            System.out.println("=======> 输入 1： 发送 血氧 数据");
            System.out.println("=======> 输入 2： 发送 无创血压 数据");
            System.out.println("=======> 输入 3： 发送 血糖 数据");
            System.out.println("=======> 输入 4： 发送 BMI 数据");
            System.out.println("=======> 输入 5： 发送 体温 数据");
            System.out.println("=======> 输入 6： 发送 腰臀比 数据");
            System.out.println("=======> 输入 7： 发送 尿酸 数据");
            System.out.println("=======> 输入 8： 发送 总胆固醇 数据");
            System.out.println("=======> 输入 9： 发送 血红蛋白 数据");
            System.out.println("=======> 输入 a： 发送 时间同步 数据");
            System.out.println("=======> 输入 b： 发送 用户求助 数据");
            System.out.println("=======> 输入 c： 发送 用户授权列表时间戳 数据");
            System.out.println("=======> 输入 q： 退出程序:");
            try {
                char c = (char) System.in.read();

                switch (c) {
                    case '1':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(xueyangFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case '2':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(xueyaFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case '3':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(xuetangFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case '4':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(bmiFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case '5':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(tempFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case '6':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(yaotunFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case '7':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(niaosuanFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case '8':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(danguchunFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case '9':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(xuehongdanbaFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case 'a':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(shijiantongbuFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 20; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case 'b':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(qiuzhuFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 16; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case 'c':
                        client = SokectDemoClient.getClientSocket(host, port);
                        serverReturn = client.sendFrame(shouquanshijianFrame.getFrameBytes());
                        System.out.println("服务器回复:");
                        for (int i = 0; i < 28; i++) {
                            System.out.printf("0x%x ", serverReturn[i]);
                        }
                        System.out.println();
                        break;
                    case 'q':
                    case 'Q':
                        System.exit(0);
                        break;
                    default:
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}






