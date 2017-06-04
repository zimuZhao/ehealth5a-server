package com.frameDemo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SokectDemoClient {

    private Socket socket;
    private int port;
    private String hostname;


    private SokectDemoClient(String hostname, int port) {

        this.hostname = hostname;
        this.port = port;
    }

    public static SokectDemoClient getClientSocket(String hostname, int port) {

        return new SokectDemoClient(hostname, port);
    }

    public byte[] sendFrame(byte[] bytes) {

        InputStream inputStream = null;
        OutputStream outputStream = null;

        byte[] recive = new byte[300];
        try {

            socket = new Socket(hostname, port);

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            outputStream.write(bytes, 0, bytes.length);

            inputStream.read(recive, 0, recive.length);

            return recive;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] b = {(byte) 0x00, (byte) 0x00};
        return b;
    }

}
