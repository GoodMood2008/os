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
 * �ͻ���
 */
public class BioClient {
    public static void main(String[] args) {
        try {
            //1.�����ͻ���Socket��ָ����������ַ�Ͷ˿�
            Socket socket=new Socket("localhost", 8080);
            //2.��ȡ���������������˷�����Ϣ
            OutputStream os=socket.getOutputStream();//�ֽ������
            PrintWriter pw=new PrintWriter(os);//���������װΪ��ӡ��
            //3.��ȡ������������ȡ�������˵���Ӧ��Ϣ
            InputStream is=socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String info=null;
            byte buffer[] = new byte[256];
            System.out.println("Please input:");
            System.in.read(buffer);
            pw.write(new String(buffer));
            pw.flush();
            socket.shutdownOutput();//�ر������
            while ((info = br.readLine()) != null) {
                System.out.println("���ǿͻ��ˣ�������˵��" + info);
            }



            //4.�ر���Դ
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}