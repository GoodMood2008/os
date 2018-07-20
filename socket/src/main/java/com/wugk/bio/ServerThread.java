package com.wugk.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

/*
 * 服务器线程处理类
 */
public class ServerThread extends Thread {
    // 和本线程相关的Socket
    Socket socket = null;
    private InputStream input = null;
    private OutputStream output = null;

    private byte[] revBuffer = null;
    private int curRevLen;
    private volatile boolean running = true;

    public ServerThread(Socket socket) {
        this.socket = socket;
        this.revBuffer = new byte[100];
        this.curRevLen = 0;
    }

    public ServerThread init() throws IOException {
        socket.setKeepAlive(true);
        socket.setSoTimeout(240000);
        this.input = socket.getInputStream();
        this.output = socket.getOutputStream();
        return this;
    }

    //线程执行的操作，响应客户端的请求
    @Override
    public void run() {
        while(this.running) {
            try {
                System.out.println("a read begin...");
                int e = this.revBuffer.length - this.curRevLen;
                int realLen = input.read(this.revBuffer, this.curRevLen, e);
                if(realLen <= 0) {
                    close();
                    return;
                }
                this.curRevLen += realLen;
                if(this.curRevLen > 0) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(curRevLen);
                    for (int i = 0; i < curRevLen; i++) {
                        byteBuffer.put(revBuffer[i]);
                    }
                    this.curRevLen = 0;
                    System.out.println("服务器说：" + new String(byteBuffer.array()));
                    output.write(byteBuffer.array());
                    output.flush();
                }
            } catch (IOException var4) {
                close();
            } catch (Exception var5) {
                this.curRevLen = 0;
            }
        }
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}