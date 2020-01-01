package com.java.demo;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    DBCon con;
    Pattern r = Pattern.compile("\\#(.*?)#");

    public Controller() throws java.lang.Exception {
        con = new DBCon();
    }

    public void closeDB() throws SQLException {
        con.closeDB();
        System.out.println("在分发器中,成功关闭数据库");
    }

    public String controller(String info) throws java.lang.Exception {
        String resultSet = null;
        System.out.println(info);
        Matcher m = r.matcher(info);
        m.find();
        System.out.println(m.group(1));
        switch (m.group(1)) {
            case "L1":
                resultSet = loginState(info);
                break;
            case "F1":
                resultSet = showAllHost();
                break;
            case "F2":
                resultSet = addHostInfo(info);
                break;
            case "F3":
                resultSet = deleteHostInfo(info);
                break;
            case "F4":
                resultSet = selectHost(info);
                break;
            case "F6":
                resultSet = showAllArrears(info);
                break;
            case "F7" :
                resultSet=selectArrearsByInfo(info);
                break;
            case"F8":
                resultSet=selectArrearsOrderByTotalFee(info);
                break;
            case "E1":
                resultSet = editHost(info);
                break;
            case "E2":
                resultSet = editUsaged(info);
                break;
            case "P1":
                resultSet = payManagementFee(info);
                System.out.println(info);
                break;
            case "U1":
                resultSet=updateArrears(info);
                break;
            default:
                resultSet = "#E1##fuck#";
                break;
        }
        return resultSet;
    }

    private String selectArrearsByInfo(String info) throws java.lang.Exception {
        System.out.println("根据参数显示欠费信息");
        return con.selectArrearsByInfo(info);

    }

    private String showAllHost() throws java.lang.Exception {
        System.out.println("显示所有住户信息");
        return con.showAllHost();

    }

    private String addHostInfo(String hostInfo) throws java.lang.Exception {
        System.out.println("增加住户信息");
        return con.addHostInfo(hostInfo);
    }

    private String selectHost(String hostInfo) throws java.lang.Exception {
        System.out.println("通过ID查询住户信息");
        return con.selectHost(hostInfo);
    }

    private void selectHouseByID(String houseID) throws java.lang.Exception {
        System.out.println("查询所有数据");
        con.selectHouseByID(houseID);
    }

    private String loginState(String loginInfo) throws SQLException {
        System.out.println("查询登录结果,返回状态值");
        return con.loginState(loginInfo);
    }

    private String deleteHostInfo(String hostInfo) throws java.lang.Exception {
        System.out.println("删除住户信息");
        return con.deleteHostInfo(hostInfo);
    }

    private String editHost(String hostInfo) throws java.lang.Exception {
        System.out.println("修改用户信息");
        return con.editHost(hostInfo);
    }

    private String showAllArrears(String hostInfo) throws java.lang.Exception {
        System.out.println("查询所有欠费信息");
        return con.showAllArrears(hostInfo);
    }

    private String payManagementFee(String info) throws  java.lang.Exception{
        System.out.println("缴费功能");
        return con.payManagementFee(info);
    }

    private String editUsaged(String info) throws  java.lang.Exception{
        System.out.println("编辑用量");
        return con.editUsaged(info);
    }
    private String updateArrears(String info) throws  java.lang.Exception{
        System.out.println("手动更新欠费表");
        return con.updateArrears(info);
    }
    private String selectArrearsOrderByTotalFee(String info) throws java.lang.Exception{
        System.out.println("展示所有欠费信息(根据TotalFee)");
        return con.selectArrearsOrderByTotalFee(info);
    }
}
