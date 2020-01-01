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

public class UpdateArrearsView {
    Test test;

    public UpdateArrearsView(Test test) {
        this.test = test;
        init();
    }

    private Pattern r = Pattern.compile("\\#(.*?)#");
    private Pattern r1 = Pattern.compile("^((0{1}\\.\\d{1,2})|([1-9]\\d*\\.{1}\\d{1,2})|([1-9]+\\d*)|0)$");
    private Pattern r2 = Pattern.compile("^[1-9]\\d*$");

    public boolean isPositiveNumber(String str) {

        return r1.matcher(str).matches();
    }

    public boolean isPositiveInteger(String str) {

        return r2.matcher(str).matches();
    }

    private JPanel contentPanel = new JPanel();
    private JFrame f = new JFrame("更新欠费表");
    private JTextField houseIDText = new JTextField(45);
    private JButton updateBtn = new JButton("更新");
    private JButton resetBtn = new JButton("重置");
    private JLabel label = new JLabel("输入住房ID：");
    private Font font = new Font("微软雅黑", Font.PLAIN, 18);

    public void init() {
        ResetListener resetListener = new ResetListener();
        resetBtn.addActionListener(resetListener);
        UpdateListener updateListener = new UpdateListener(test);
        updateBtn.addActionListener(updateListener);
        contentPanel.setLayout(null);
        contentPanel.add(updateBtn);
        contentPanel.add(label);
        contentPanel.add(resetBtn);
        contentPanel.add(houseIDText);
        label.setFont(font);
        houseIDText.setFont(font);
        updateBtn.setFont(font);
        resetBtn.setFont(font);
        updateBtn.setBounds(170, 80, 70, 35);
        houseIDText.setBounds(170, 30, 200, 35);
        label.setBounds(60, 30, 200, 35);
        resetBtn.setBounds(300, 80, 70, 35);
        f.add(contentPanel);
        f.setBounds(700, 400, 500, 180);
        f.setVisible(true);
    }

    class UpdateListener implements ActionListener {
        Test test = null;

        public UpdateListener(Test test) {
            this.test = test;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            String houseID = houseIDText.getText();
            if (houseID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "输入不能为空！", "错误信息", JOptionPane.ERROR_MESSAGE);
            } else if ((isPositiveInteger(houseID))) {
                String info = "#U1##" + houseID + "#";
                try {
                    test.pass(info);
                    Matcher m = r.matcher(test.get());
                    m.find();
                    m.find();
                    if (m.group(1).equals("1")){
                        JOptionPane.showMessageDialog(null, "更新成功！");
                    }else{
                        JOptionPane.showMessageDialog(null, "更新失败！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "编号必须为数字", "错误信息", JOptionPane.ERROR_MESSAGE);
                return;
            }
            f.dispose();
        }

    }

    class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            houseIDText.setText("");
        }
    }

}
