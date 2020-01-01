package view;



import client.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchHostView {
    Test test;

    public SearchHostView(Test test) {
        this.test = test;
        init();
    }

    private  Pattern r = Pattern.compile("\\#(.*?)#");
    private  Pattern r1 = Pattern.compile("^((0{1}\\.\\d{1,2})|([1-9]\\d*\\.{1}\\d{1,2})|([1-9]+\\d*)|0)$");
    private  Pattern r2 = Pattern.compile("^[1-9]\\d*$");

    public  boolean isPositiveNumber(String str) {

        return r1.matcher(str).matches();
    }

    public  boolean isPositiveInteger(String str) {

        return r2.matcher(str).matches();
    }
    
    private JPanel contentPanel = new JPanel();
    private JFrame f = new JFrame("查询住户信息");
    private JFrame jf = new JFrame();
    private JTextField inputText = new JTextField(45);
    private JButton searchBtn = new JButton("查询");
    private JButton resetBtn = new JButton("重置");
    private JLabel selectLabel = new JLabel("请选择查询方式：");
    private JLabel inputLabel = new JLabel("请输入关键字：");
    private String[] arrearsChoice = new String[]{"按用户编号查询", "按用户姓名查询", "按电话号码查询"};
    private JComboBox<String> arrearsChoicer = new JComboBox<>(arrearsChoice);
    private Font font = new Font("微软雅黑", Font.PLAIN, 18);
    private Vector<Vector<String>> data = new Vector<>();

    public  void init() {
        SearchListener searchListener = new SearchListener();
        ResetListener resetListener = new ResetListener();

        resetBtn.addActionListener(resetListener);
        searchBtn.addActionListener(searchListener);
        contentPanel.setLayout(null);
        contentPanel.add(selectLabel);
        contentPanel.add(arrearsChoicer);
        contentPanel.add(searchBtn);
        contentPanel.add(inputLabel);
        contentPanel.add(inputText);
        contentPanel.add(resetBtn);

        inputLabel.setFont(font);
        inputText.setFont(font);
        selectLabel.setFont(font);
        arrearsChoicer.setFont(font);
        searchBtn.setFont(font);
        resetBtn.setFont(font);

        selectLabel.setBounds(40, 30, 200, 35);
        arrearsChoicer.setBounds(190, 30, 200, 35);
        inputLabel.setBounds(40, 80, 200, 35);
        inputText.setBounds(190, 80, 200, 35);
        searchBtn.setBounds(170, 160, 70, 35);
        resetBtn.setBounds(300, 160, 70, 35);
        f.add(contentPanel);
        f.setBounds(700, 400, 500, 260);
        f.setVisible(true);
    }

     class SearchListener implements ActionListener {
        
         @Override
         public void actionPerformed(ActionEvent e) {
             String choice = (String) arrearsChoicer.getSelectedItem();
             String keyWord = inputText.getText();
             String info = null;
             if (keyWord.isEmpty()) {
                 JOptionPane.showMessageDialog(null, "输入不能为空", "错误信息", JOptionPane.ERROR_MESSAGE);
             } else  {
                 if (choice.equals("按用户编号查询")) {
                     info = "#F4##1##" + keyWord + "#";
                 } else if (choice.equals("按用户姓名查询")) {
                     info = "#F4##2##" + keyWord + "#";
                 } else if (choice.equals("按电话号码查询")) {
                     info = "#F4##3##" + keyWord + "#";
                 }
                 try {
                    test.pass(info);
                    Matcher m = r.matcher(test.get());
                    m.find();

                    while (m.find()) {
                        System.out.println(m.group(1));
                        if (m.group(1).equals("^6^")) {
                            JOptionPane.showMessageDialog(null, "没找到该用户", "错误信息", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
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
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
                 f.dispose();

             }
        }

    }

     class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            inputText.setText("");
        }
    }
}
