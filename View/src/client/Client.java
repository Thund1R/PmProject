package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    static DataInputStream in = null;
    static DataOutputStream out = null;
    static Socket mysocket;

    public Client() {
        connectServer();
    }

    public static void connectServer() {
        mysocket = new Socket();
        try {
            System.out.println("尝试连接");
            mysocket = new Socket("127.0.0.1", 4333);
            System.out.println("连接成功");
            in = new DataInputStream(mysocket.getInputStream());
            out = new DataOutputStream(mysocket.getOutputStream());
        } catch (Exception e) {
            System.out.println("服务器已断开" + e);
            System.exit(0);
        }
    }

    public void pass(String info) throws IOException, InterruptedException {
        out.writeUTF(info);
        Thread.sleep(500);

    }

    public String get() {
        System.out.println("get方法被调用");
        String info = null;
        try {
            Thread.sleep(500);
            info = in.readUTF();

        } catch (Exception e) {

        }
        return info;
    }

    public void closeSocket() throws IOException {
        mysocket.close();
    }
}