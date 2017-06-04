package com.frameDemo.app;

import com.frameDemo.queue.FrameQueue;
import com.frameDemo.socket.SokectDemoServer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FrameServerApp {

    private static final int POOLSIZE = 100;
    private static final int PORT = 8888;

    private static SokectDemoServer sokectDemoServer;

    private static FrameQueue frameQueue;

    private static ExecutorService executorService;

    static {

        frameQueue = FrameQueue.getFrameQueue();
        executorService = Executors.newFixedThreadPool(20);
    }

    public static void main(String[] args) {

        try {
            sokectDemoServer = SokectDemoServer.getSchedulerServerSocket(PORT, POOLSIZE, frameQueue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.execute(new Runnable() {
            public void run() {
                sokectDemoServer.serverStart();
            }
        });

        /**
         *   使用nohup运行jar包时 放弃手动停止程序
         *   nohup.out会因为读取不到c而报错 Bad file descriptor
          */
//        while (true) {
//            System.out.println("=======> 输入 q： 退出程序:");
//            try {
//                char c = (char) System.in.read();
//                if (c == 'q' || c == 'Q')
//                    System.exit(0);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

}
