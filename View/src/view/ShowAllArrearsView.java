package view;

import client.Test;
import javax.swing.*;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowAllArrearsView {
    Test test;
    public ShowAllArrearsView(Test test) {
        this.test = test;
        init();
    }

    private Pattern r = Pattern.compile("\\#(.*?)#");

    public void init() {
        String info = "#F6#";
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
                for (int i = 0; i < 8; i++) {
                    m.find();
                    hostInfo.add(m.group(1));
                }
                data.add(hostInfo);
            }
            Vector<String> columnNames = new Vector<>();
            columnNames.add("欠费单号");
            columnNames.add("住房编号");
            columnNames.add("水费");
            columnNames.add("电费");
            columnNames.add("气费");
            columnNames.add("管理费");
            columnNames.add("绿化费");
            columnNames.add("总计");

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
            jf.setBounds(650, 250, 1250, 600);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}