package com.java.demo;

import jdk.swing.interop.SwingInterOpUtils;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//思路:首先 先创建好数据库连接需要的内容,
// 万年不变的三个参数URL USERNAME PASSWORD
//声明好Connection Statment 类
//然后在构造方法中注册驱动,创建链接
//于是,现在只需要挨个实现功能就可以了

public class DBCon {
    static final String DB_URL = "jdbc:mysql://localhost:3306/propertymanagementfee?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "123123123a";
    Connection conn = null;
    Statement stat = null;
    Pattern r = Pattern.compile("\\#(.*?)#");

    //最先被执行的内容,初始化数据库
    public DBCon() throws SQLException, Exception {
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

    }

    //最后被执行的内容,检查数据库
    private void checkResult(ResultSet rs) throws SQLException {
        // 关闭
        try {
            if (rs == null) {
                stat.close();
                System.out.println("结果出现问题,成功关闭数据库");
            } else {
                // TODO: 2019/12/5  
            }
        } catch (Exception e) {
            System.out.println("结果出现问题,成功关闭数据库");
        }
    }

    //关闭数据库
    public void closeDB() throws SQLException {
        stat.close();
        System.out.println("在DBCON中,成功关闭数据库");
    }

    public String addHostInfo(String hostInfo) throws SQLException {

        stat = conn.createStatement();
        Matcher m = r.matcher(hostInfo);
        m.find();
        m.find();
        String hostID = m.group(1);
        m.find();
        String hostName = m.group(1);
        m.find();
        String idCardID = m.group(1);
        m.find();
        String hostSex = m.group(1);
        m.find();
        String hostTel = m.group(1);
        m.find();
        String hostEmail = m.group(1);
        m.find();
        String hostWorkPlace = m.group(1);
        String sql = "INSERT INTO `host` VALUES ('" + hostID + "','" + hostName + "','" + idCardID + "','" + hostSex + "','" + hostTel + "','" + hostWorkPlace + "','" + hostEmail + "')";
        int rs = stat.executeUpdate(sql);
        return "#F2##" + rs + "##" + hostName + "#";

    }

    public String deleteHostInfo(String hostInfo) throws SQLException {
        System.out.println(hostInfo);
        Matcher m = r.matcher(hostInfo);
        m.find();
        m.find();
        String hostID = m.group(1);
        String sql = "delete FROM `host` WHERE HostID =" + hostID;
        int rs = stat.executeUpdate(sql);
        return "#F3##" + rs + "##" + hostID + "#";
    }

    //一个功能的示例
    public String showAllHost() throws SQLException, Exception {
        // 执行查询
        stat = conn.createStatement();
        String sql = "SELECT * FROM host";
        ResultSet rs = stat.executeQuery(sql);
        StringBuilder str = new StringBuilder("#F1#");
        // 输出查询结果
        int hostID = 0;
        String hostName = null;
        String idCardID = null;
        String hostSex = null;
        String tel = null;
        String workPlace = null;
        String email = null;
        while (rs.next()) {
            hostID = rs.getInt("HostID");
            hostName = rs.getString("HostName");
            idCardID = rs.getString("IDCardID");
            hostSex = rs.getString("HostSex");
            tel = rs.getString("TEL");
            workPlace = rs.getString("WorkPlace");
            email = rs.getString("Email");
            str.append("##^#").append(hostID).append("##").append(hostName).append("##").append(idCardID).append("##").append(hostSex).append("##").append(tel).append("##").append(workPlace).append("##").append(email).append("#");
        }
        str.append("#$#");
        return str.toString();
    }

    public String selectHost(String hostInfo) throws SQLException, Exception {
        // 执行查询
        stat = conn.createStatement();
        Matcher m = r.matcher(hostInfo);
        String sql = null;
        m.find();
        m.find();
        String caseKey = m.group(1);
        m.find();
        String key = m.group(1);
        switch (caseKey) {
            case "1":
                sql = "SELECT * from host WHERE HostID='" + key + "'";
                break;
            case "2":
                sql = "SELECT * from host WHERE HostName='" + key + "'";
                break;
            case "3":
                sql = "SELECT * from host WHERE TEL='" + key + "'";
                break;

        }

        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        String hostID=null;
        String hostName = null;
        String idCardID = null;
        String hostSex = null;
        String tel = null;
        String workPlace = null;
        String email = null;
        StringBuilder str = new StringBuilder("#F4#");
        String flag="^6^";
        if(rs==null){
            str.append("#^6^#");
        }
        while (rs.next()) {

            hostID = rs.getString("HostID");
            hostName = rs.getString("HostName");
            idCardID = rs.getString("IDCardID");
            hostSex = rs.getString("HostSex");
            tel = rs.getString("TEL");
            workPlace = rs.getString("WorkPlace");
            email = rs.getString("Email");
            str.append("##^#").append(hostID).append("##").append(hostName).append("##").append(idCardID).append("##").append(hostSex).append("##").append(tel).append("##").append(workPlace).append("##").append(email).append("#");
        }
        str.append("#$#");
        System.out.println(str);
        return str.toString();
    }

    public void selectHouseByID(String HouseID) throws SQLException, Exception {
        // 执行查询
        stat = conn.createStatement();
        String sql = "SELECT * from house WHERE HouseID='" + HouseID + "';";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while (rs.next()) {
            System.out.print(rs.getInt("HouseID") + ",");
            System.out.print("\n");
        }

    }

    public String loginState(String loginInfo) throws SQLException {
        Matcher m = r.matcher(loginInfo);
        m.find();
        m.find();
        String adminID = m.group(1);
        m.find();
        String adminPassword = m.group(1);
        String state = "0";
        try {

            stat = conn.createStatement();
            String sql = "SELECT * from administors WHERE AdminID='" + adminID + "'";
            ResultSet rs = stat.executeQuery(sql);
            // 输出查询结果
            while (rs.next()) {

                if (rs.getString("Password").equals(adminPassword)) {
                    state = "1";
                } else {
                    state = "0";
                }

            }
        } catch (Exception e) {
            // TODO: 2019/12/17
        }
        return "#L1##" + state + "##" + adminID + "#";

    }


    public String editHost(String hostInfo) throws SQLException {
        //解析出来各个参数
        StringBuilder str = new StringBuilder("#E1#");

        stat = conn.createStatement();
        Matcher m = r.matcher(hostInfo);
        m.find();
        m.find();
        int hostID = Integer.parseInt(m.group(1));
        m.find();
        String hostName = m.group(1);
        m.find();
        String idCardID = m.group(1);
        m.find();
        String hostSex = m.group(1);
        m.find();
        String hostTel = m.group(1);
        m.find();
        String hostEmail = m.group(1);
        m.find();
        String hostWorkPlace = m.group(1);
        String sql = "SELECT * from host WHERE hostID='" + hostID + "'";
        ResultSet rs = stat.executeQuery(sql);

        if (rs.next()) {
            stat.executeUpdate("update host SET HostName='" + hostName + "',IDCardID='" + idCardID + "',HostSex='" + hostSex + "',TEL='" + hostTel + "',WorkPlace='" + hostWorkPlace + "',Email='" + hostEmail + "' where HostID='" + hostID + "'");
            str.append("#1#");
        } else {
            str.append("#0#");
        }
        str.append("#$#");
        return str.toString();
    }

    public String showAllArrears(String hostInfo) throws SQLException {
        // 执行查询
        stat = conn.createStatement();
        String sql = "SELECT * FROM housearrear";
        ResultSet rs = stat.executeQuery(sql);
        StringBuilder str = new StringBuilder("#F6#");
        // 输出查询结果
        int HouseArrearID = 0;
        String HouseID = null;
        String WaterFee = null;
        String ElectricityFee = null;
        String GasFee = null;
        String ManageFee = null;
        String GreenFee = null;
        String TotalFee = null;
        while (rs.next()) {
            HouseArrearID = rs.getInt("HouseArrearID");
            HouseID = rs.getString("HouseID");
            WaterFee = rs.getString("WaterFee");
            ElectricityFee = rs.getString("ElectricityFee");
            GasFee = rs.getString("GasFee");
            ManageFee = rs.getString("ManageFee");
            GreenFee = rs.getString("GreenFee");
            TotalFee = rs.getString("TotalFee");
            str.append("##^#").append(HouseArrearID).append("##").append(HouseID).append("##").append(WaterFee).append("##").append(ElectricityFee).append("##").append(GasFee).append("##").append(ManageFee).append("##").append(GreenFee).append("##").append(TotalFee).append("#");
        }
        str.append("#$#");
        System.out.println(str);
        return str.toString();


    }

    public String selectArrearsByInfo(String info) throws SQLException {

//        stat = conn.createStatement();
//        StringBuilder str = new StringBuilder("#F7#");
        String result = info;
        Matcher m = r.matcher(info);
//        CallableStatement cstmt = null;
//        ResultSet rs = null;
        m.find();
        m.find();
        String caseKey = m.group(1);
        m.find();
        String key = m.group(1);
        switch (caseKey) {
            case "1":
                return tool(caseKey, key);

            case "2":
                return tool(caseKey, key);

        }
return "";
    }

    public String payManagementFee(String info) throws SQLException {

        StringBuilder str = new StringBuilder("#P1#");
        stat = conn.createStatement();
        Matcher m = r.matcher(info);
        m.find();

        m.find();
        String houseID = m.group(1);
        m.find();
        String money = m.group(1);
        CallableStatement cstmt = null;
        try {
            cstmt = conn.prepareCall("{call feepay(" + houseID + "," + money + ")}");
            cstmt.execute();
            str.append("#1#");

        } catch (Exception e) {
            str.append("#0#");
        }
        str.append("#$#");
        System.out.println(str.toString());
        return str.toString();
    }

    public String editUsaged(String info) throws SQLException {

        StringBuilder str = new StringBuilder("#E2#");
        stat = conn.createStatement();
        Matcher m = r.matcher(info);
        m.find();

        m.find();
        String houseID = m.group(1);
        m.find();
        String waterUsaged = m.group(1);
        m.find();
        String electricUsaged = m.group(1);
        m.find();
        String gasUsaged = m.group(1);
        CallableStatement cstmt = null;
        try {
            cstmt = conn.prepareCall("{call usagedupdate(" + houseID + "," + waterUsaged + "," + electricUsaged + "," + gasUsaged + ")}");
            cstmt.execute();
            str.append("#1#");

        } catch (Exception e) {
            str.append("#0#");
        }
        str.append("#$#");
        System.out.println(str.toString());
        return str.toString();

    }

    public String updateArrears(String info) throws SQLException {
        StringBuilder str = new StringBuilder("#U1#");
        Matcher m = r.matcher(info);
        m.find();
        m.find();
        stat = conn.createStatement();


        CallableStatement cstmt = null;
        try {

        cstmt = conn.prepareCall("{call feeupdate(" + m.group(1) + ")}");
            cstmt.execute();
            str.append("#1#");

        } catch (Exception e) {
            str.append("#0#");
        }
        str.append("#$#");
        System.out.println(str.toString());
        return str.toString();


    }

    public String selectArrearsOrderByTotalFee(String info) throws SQLException{

        stat = conn.createStatement();
        String sql = "SELECT host.HostID,host.HostName,house.houseID,housearrear.TotalFee from host,house,housearrear where host.HostID = house.HostID and housearrear.HouseID = house.HouseID ORDER BY TotalFee DESC,HostID DESC";
        ResultSet rs = stat.executeQuery(sql);
        StringBuilder str = new StringBuilder("#F8#");
        // 输出查询结果

        String HostID = null;
        String HostName=null;
        String HouseID = null;
        String TotalFee = null;
        while (rs.next()) {
            HostID = rs.getString("HostID");
            HostName=rs.getString("HostName");
            HouseID = rs.getString("HouseID");
            TotalFee = rs.getString("TotalFee");
            str.append("#^##").append(HostID).append("##").append(HostName).append("##").append(HouseID).append("##").append(TotalFee).append("#");
        }
        str.append("#$#");
        System.out.println(str);
        return str.toString();
    }



    public String tool(String caseKey, String key) throws SQLException {
        stat = conn.createStatement();
        StringBuilder str = new StringBuilder("#F7#");
        String sql = null;
        switch (caseKey) {
            case "1":
                sql = "select HouseID from house where HouseD=" + key;
                break;
            case "2":
                sql = "select HouseID from house where HostID=" + key;
                break;
        }
        ResultSet rs = stat.executeQuery(sql);
        int count = 0;
        String[] list = new String[5];
        while (rs.next()) {
            String ID = rs.getString("HouseID");
            System.out.println(ID);
            list[count] = ID;
            count++;
        }
        System.out.println(count + "***");
        for (int i = 0; i < count; i++) {

            System.out.println(i+"*"+list[i]);
            sql = "select * from housearrear where HouseID='" + list[i] + "'";
            ResultSet res = stat.executeQuery(sql);
            int HouseArrearID = 0;
            String HouseID = null;
            String WaterFee = null;
            String ElectricityFee = null;
            String GasFee = null;
            String ManageFee = null;
            String GreenFee = null;
            String TotalFee = null;
            res.next();
            HouseArrearID = res.getInt("HouseArrearID");
            HouseID = res.getString("HouseID");
            WaterFee = res.getString("WaterFee");
            ElectricityFee = res.getString("ElectricityFee");
            GasFee = res.getString("GasFee");
            ManageFee = res.getString("ManageFee");
            GreenFee = res.getString("GreenFee");
            TotalFee = res.getString("TotalFee");
            res.close();

            System.out.println("HID"+HouseArrearID);
            str.append("#^##").append(HouseArrearID).append("##").append(HouseID).append("##").append(WaterFee).append("##").append(ElectricityFee).append("##").append(GasFee).append("##").append(ManageFee).append("##").append(GreenFee).append("##").append(TotalFee).append("#");
            System.out.println(str);
        }
        str.append("#$#");

        System.out.println(str.toString());
        return str.toString();
    }



}
