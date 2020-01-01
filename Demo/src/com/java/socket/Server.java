package com.java.socket;

import com.java.demo.Controller;

import java.io.*;

import java.net.*;

import java.sql.SQLException;


public class Server {
    static ServerSocket socket = null;
    static Socket you;
    static DataInputStream in;
    static DataOutputStream out;

    public static void main(String args[]) throws SQLException, java.lang.Exception {
        Controller controller = new Controller();
        connectSocket();
        try {
            while (true) {
                String info = in.readUTF();
                System.out.println(info);
                returnInfo(controller.controller(info));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("在Server中错误退出");
        }

    }

    public static void connectSocket() {
        try {
            socket = new ServerSocket(4333);
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            you = socket.accept();
            System.out.println("连接成功");
            in = new DataInputStream(you.getInputStream());
            out = new DataOutputStream(you.getOutputStream());


        } catch (Exception e) {
            System.out.println("服务器异常断开" + e);
            System.exit(0);

        }
    }

    public static void returnInfo(String info) {
        try {
            out.writeUTF(info);
        } catch (Exception e) {

        }

    }

    static void closeServerSocket() throws IOException {

        System.out.println("正在关闭ServerSocket");
        socket.close();
        System.out.println("ServerSocket被成功关闭");

    }

    static void closeSocket() throws IOException {
        System.out.println("正在关闭Socket");
        you.close();
        System.out.println("Socket被成功关闭");
    }

    static void closeIOStream() throws IOException {
        System.out.println("正在关闭IOStream");
        in.close();
        out.close();
        System.out.println("IOStream被成功关闭");
    }


}