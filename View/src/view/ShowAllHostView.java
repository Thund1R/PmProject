package view;


import client.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowAllHostView {
    Test test;
    private Pattern r = Pattern.compile("\\#(.*?)#");

    public ShowAllHostView(Test test) {
        this.test = test;
        init();
    }

    public void init() {
        String info = "#F1#";
        JFrame jf = new JFrame();
        Vector<Vector<String>> data = new Vector<>();
        try {
            test.pass(info);
            Matcher m = r.matcher(test.get());
            m.find();
            while (m.find()) {
                Vector<String> hostInfo = new Vector<>();
                if (m.group(1).equals("$")) {
                    break;
                }
                for (int i = 0; i < 7; i++) {
                    m.find();
                    hostInfo.add(m.group(1));
                }
                data.add(hostInfo);
            }
            Vector<String> columnNames = new Vector<>();
            columnNames.add("用户编号");
            columnNames.add("姓名");
            columnNames.add("身份证号");
            columnNames.add("性别");
            columnNames.add("联系电话");
            columnNames.add("工作单位");
            columnNames.add("电子邮箱");

            JTable table = new JTable(data, columnNames);
            JScrollPane js = new JScrollPane(table);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setRowSelectionAllowed(true);
            for (int i = 0; i < columnNames.size(); i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(150);
            }

            js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jf.add(js);
            jf.setVisible(true);
            jf.setBounds(650, 250, 1080, 600);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
