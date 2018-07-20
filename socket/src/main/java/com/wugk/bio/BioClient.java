package com.wugk.bio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/*
 * 客户端
 */
public class BioClient {
    public static void main(String[] args) {
        try {
            //1.创建客户端Socket，指定服务器地址和端口
            Socket socket=new Socket("localhost", 8080);
            socket.setSoTimeout(240000);
            socket.setKeepAlive(true);
            //2.获取输出流，向服务器端发送信息
            OutputStream output = socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(output);//将输出流包装为打印流
            //3.获取输入流，并读取服务器端的响应信息

            byte bufferIn[] = new byte[256];
            System.out.println("Please input:");
            System.in.read(bufferIn);
            pw.write(new String(bufferIn));
            pw.flush();

            InputStream input=socket.getInputStream();
            int curRevLen = 0;
            byte buffer[] = new byte[20];
            int e = buffer.length - curRevLen;
            int realLen = input.read(buffer, 0, e);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
            while (realLen > 0) {

                for (int i = 0; i < realLen; i++) {
                    byteBuffer.put(buffer[i]);
                }
            }
            System.out.println("Sever said:" + new String(byteBuffer.array()));


            //4.关闭资源
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}