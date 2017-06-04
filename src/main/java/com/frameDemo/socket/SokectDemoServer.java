package com.frameDemo.socket;

import com.frameDemo.frame.Frame;
import com.frameDemo.frame.FrameData;
import com.frameDemo.frame.FrameHead;
import com.frameDemo.queue.FrameQueue;
import com.frameDemo.utils.FrameCheckSum;
import com.frameDemo.utils.FrameProcess;
import com.frameDemo.utils.FrameSave;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SokectDemoServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private int port;
    //线程池
    private ExecutorService executeService;
    //缓冲队列
    private FrameQueue frameQueue;

    //不接收address和port 让程序自动获取
    private SokectDemoServer(int port, int poolSize, FrameQueue frameQueue) {
        this.port = port;
        this.frameQueue = frameQueue;
        executeService = Executors.newFixedThreadPool(poolSize);
    }

    public static SokectDemoServer getSchedulerServerSocket(int port, int poolSize, FrameQueue frameQueue) {

        return new SokectDemoServer(port, poolSize, frameQueue);
    }

    public void serverStart() {

        try {

            serverSocket = new ServerSocket(port);

            System.out.println("SocketServer have been started." + serverSocket);

            while (true) {

                socket = serverSocket.accept(); // this accept is blocking.
                executeService.submit(new Runnable() {
                    public void run() {

                        byte[] headFalgA;
                        byte[] headFalgB;
                        byte[] headData;

                        InputStream inStream = null;
                        OutputStream outStrem = null;
                        try {
                            inStream = socket.getInputStream();
                            outStrem = socket.getOutputStream();
                            headFalgA = new byte[1];
                            headFalgB = new byte[1];
                            headData = new byte[10];

//                            System.out.println("**********************************************************************************************************");
//                            System.out.println("==> 检测数据帧头部。");
                            //存储位置，读取偏移量，读取长度
                            inStream.read(headFalgA, 0, 1);

                            if (headFalgA[0] == FrameHead.startFlagA) {

                                System.out.printf("    head FLAG_1 is: {0x%x}  ===> OK. continue.\n", headFalgA[0]);

                                inStream.read(headFalgB, 0, 1);

                                if (headFalgB[0] == FrameHead.startFlagB) {

                                    System.out.printf("    head FLAG_2 is: {0x%x}  ===> OK. continue.\n", headFalgB[0]);
//                                    System.out.println("读取帧首部的信息。");
                                    inStream.read(headData, 0, 10);

                                    byte[] head = {headFalgA[0], headFalgB[0], headData[0], headData[1], headData[2],
                                            headData[3], headData[4], headData[5], headData[6], headData[7], headData[8], headData[9]};

//                                    System.out.println("    得到的帧头数据:");
//                                    System.out.print("        ");
//                                    for (byte b : head)
//                                        System.out.printf("0x%x ", b);
//                                    System.out.println();

                                    FrameHead frameHead = FrameHead.initFrameHead(head);

                                    System.out.println("=== 检查帧首部的检验和。");

                                    if (!FrameCheckSum.isFrameHeadCheckSumOK(frameHead)) {

                                        System.out.println("    Frame head check sum error, return.");
                                        outStrem.write("    帧首部检验和出错.".getBytes());
                                        return;
                                    }
                                    System.out.printf("    Frame head check sum OK.\n");

                                    //暂时不知道合法性检查策略 先略过
//                                    System.out.println("=== 检查帧首部的 VER ID LEN 的合法性，假设合法.");
//                                    System.out.println("=== 读取数据帧.");

                                    byte[] dataBody = new byte[frameHead.getDataLen()];
                                    byte[] dataCache = new byte[frameHead.getDataLen()];

                                    int size = 0;
                                    int i = 0;

                                    while ((size = inStream.read(dataCache, 0, frameHead.getDataLen() - 12 - size)) > 0) {

                                        for (int j = 0; j < size; j++) {
                                            dataBody[i + j] = dataCache[j];
                                        }
                                        i += size;

                                        if (i >= frameHead.getDataLen())
                                            break;
                                    }

//                                    System.out.println("得到的 数据帧 数据:");
//                                    System.out.print("        ");
//                                    for (byte b : dataBody)
//                                        System.out.printf("0x%x ", b);
//                                    System.out.println();

                                    FrameData frameData = FrameData.initFrameData(dataBody);

                                    Frame frame = Frame.initFrame(frameHead, frameData);

                                    System.out.println("=== 检查 数据帧 的检验和。");
                                    if (!FrameCheckSum.isFrameDataCheckSumOK(frame)) {

                                        System.out.println("    Frame data check sum error, return.");
                                    }
                                    System.out.println("    Frame data check sum OK.");

                                    System.out.println("=== 生成数据帧，加入帧队列。");
                                    frameQueue.addFrame(new Date().getTime(), frame);

//                                    System.out.println("===> 在这里获取相关帧的处理结果，并给客户端发送确认命令。");

                                    byte[] D_SYNC = new byte[4];
                                    //帧ID  判断帧数据内容类型
                                    switch (headData[1]) {
                                        case (byte) 0x80://血氧
                                            try {
                                                D_SYNC = FrameSave.setBloodOxygen(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameBO = FrameProcess.sendDSYNC(frameHead, D_SYNC);
                                            outStrem.write(frameBO.getFrameBytes());
                                            break;
                                        case (byte) 0x81://无创血压
                                            try {
                                                D_SYNC = FrameSave.setBloodPressure(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameBP = FrameProcess.sendDSYNC(frameHead, D_SYNC);
                                            outStrem.write(frameBP.getFrameBytes());
                                            break;
                                        case (byte) 0x82://血糖
                                            try {
                                                D_SYNC = FrameSave.setBloodGlucose(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameBG = FrameProcess.sendDSYNC(frameHead, D_SYNC);
                                            outStrem.write(frameBG.getFrameBytes());
                                            break;
                                        case (byte) 0x83://BMI
                                            try {
                                                D_SYNC = FrameSave.setBodyMassIndex(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameBMI = FrameProcess.sendDSYNC(frameHead, D_SYNC);
                                            outStrem.write(frameBMI.getFrameBytes());
                                            break;
                                        case (byte) 0x84://体温
                                            try {
                                                D_SYNC = FrameSave.setTemperature(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameT = FrameProcess.sendDSYNC(frameHead, D_SYNC);
                                            outStrem.write(frameT.getFrameBytes());
                                            break;
                                        case (byte) 0x85://腰臀比
                                            try {
                                                D_SYNC = FrameSave.setWaistHipRatio(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameWHP = FrameProcess.sendDSYNC(frameHead, D_SYNC);
                                            outStrem.write(frameWHP.getFrameBytes());
                                            break;
                                        case (byte) 0x90://时间同步
                                            Frame timeSync = FrameProcess.sendTimeSync(frameHead);
                                            outStrem.write(timeSync.getFrameBytes());
                                            break;
                                        case (byte) 0x91://用户求助
                                            try {
                                                D_SYNC = FrameSave.setForHelp(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameForHelp = FrameProcess.sendForHelp(frameHead, D_SYNC);
                                            outStrem.write(frameForHelp.getFrameBytes());
                                            break;
                                        case (byte) 0x92://用户授权列表时间戳
                                            FrameSave.setUserAuthorise(frameData);
                                            Frame frameUA = FrameProcess.sendUserAuthorise(frameHead);
                                            outStrem.write(frameUA.getFrameBytes());
                                            break;
                                        case (byte) 0x93://用户授权列表接收确认
                                            break;
                                        case (byte) 0x94://刷卡后上传用户信息
                                            break;
                                        case (byte) 0x95://尿酸数据
                                            try {
                                                D_SYNC = FrameSave.setUricCid(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameUC = FrameProcess.sendDSYNC(frameHead, D_SYNC);
                                            outStrem.write(frameUC.getFrameBytes());
                                            break;
                                        case (byte) 0x96://总胆固醇数据
                                            try {
                                                D_SYNC = FrameSave.setChol(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameCHOL = FrameProcess.sendDSYNC(frameHead, D_SYNC);
                                            outStrem.write(frameCHOL.getFrameBytes());
                                            break;
                                        case (byte) 0x86://血红蛋白数据
                                            try {
                                                D_SYNC = FrameSave.setHemoglobin(frameData);
                                                if (D_SYNC.length == 1) {
                                                    break;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            Frame frameHB = FrameProcess.sendDSYNC(frameHead, D_SYNC);
                                            outStrem.write(frameHB.getFrameBytes());
                                            break;
                                        default:
                                            System.out.println("没有匹配到帧ID");
                                    }

                                    String s = FrameProcess.getProcessResult();

                                    outStrem.write(s.getBytes(), 0, s.getBytes().length);


                                } else {

//                                    System.out.printf("head FLAF_2 is: {0x%x} NOT {0x%x} ===> not the frame head Flag, return.\n", headFalgB[0], FrameHead.startFlagB);
//                                    System.out.println("标志 2 错误.");
                                    //outStrem.write("标志 2 错误.".getBytes());
                                    return;
                                }

                            } else {

//                                System.out.printf("head FLAF_1 is: {0x%x} NOT {0x%x} ===> not the frame head Flag, return.\n", headFalgA[0], FrameHead.startFlagA);
//                                System.out.println("标志 1 错误.");
                                //outStrem.write("标志 1 错误.".getBytes());
                                return;

                            }

                            System.out.println("**********************************************************************************************************");

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {

                            try {
                                inStream.close();
                                outStrem.close();
                                socket.close();
                                System.out.println("Socket closed.");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });
            }


        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            try {
                if (null != serverSocket) {
                    serverSocket.close();
                    System.out.println("serverSocket close");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
