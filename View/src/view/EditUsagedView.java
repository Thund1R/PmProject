package view;


import client.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditUsagedView {
    Test test;

    public EditUsagedView(Test test) {
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

    private  JPanel contentPanel = new JPanel();
    private  JFrame f = new JFrame("编辑用量");
    private  Font font = new Font("微软雅黑", Font.PLAIN, 18);
    private  JButton addBtn = new JButton("编辑");
    private  JButton resetBtn = new JButton("重置");
    private  JLabel usagedIDLab = new JLabel("用量单号");
    private  JTextField usagedIDText = new JTextField();
    private  JLabel waterLab = new JLabel("用水量");
    private  JTextField waterText = new JTextField();
    private  JLabel electricLab = new JLabel("用电量");
    private  JTextField electricText = new JTextField();
    private  JLabel gasLab = new JLabel("用气量");
    private  JTextField gasText = new JTextField();

    public  void init() {
        EditUsagedListener editUsagedListener = new EditUsagedListener(test);
        ResetListener resetListener = new ResetListener();
        addBtn.addActionListener(editUsagedListener);
        resetBtn.addActionListener(resetListener);

        contentPanel.setLayout(null);
        contentPanel.add(usagedIDLab);
        contentPanel.add(usagedIDText);
        contentPanel.add(waterLab);
        contentPanel.add(waterText);
        contentPanel.add(electricLab);
        contentPanel.add(electricText);

        contentPanel.add(gasLab);
        contentPanel.add(gasText);
        contentPanel.add(addBtn);
        contentPanel.add(resetBtn);
        usagedIDLab.setBounds(90, 50, 100, 35);
        usagedIDLab.setFont(font);
        usagedIDText.setBounds(185, 50, 200, 35);
        usagedIDText.setFont(font);
        waterLab.setBounds(90, 100, 100, 35);
        waterLab.setFont(font);
        waterText.setBounds(185, 100, 200, 35);
        waterText.setFont(font);
        electricLab.setBounds(90, 150, 100, 35);
        electricLab.setFont(font);
        electricText.setBounds(185, 150, 200, 35);
        electricText.setFont(font);
        gasLab.setBounds(90, 200, 100, 35);
        gasLab.setFont(font);
        gasText.setBounds(185, 200, 200, 35);
        gasText.setFont(font);

        addBtn.setBounds(125, 300, 80, 35);
        addBtn.setFont(font);
        resetBtn.setBounds(275, 300, 80, 35);
        resetBtn.setFont(font);

        f.add(contentPanel);
        f.setBounds(700, 200, 500, 400);
        f.setVisible(true);


    }

     class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            usagedIDText.setText("");
            waterText.setText("");
            electricText.setText("");
            gasText.setText("");

        }
    }

     class EditUsagedListener implements ActionListener {
        Test test = null;

        public EditUsagedListener(Test test) {
            this.test = test;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String houseID = usagedIDText.getText();
            String waterUsaged = waterText.getText();
            String electricUsaged = electricText.getText();
            String gasUsaged = gasText.getText();

            if (houseID.isEmpty() || waterUsaged.isEmpty() || electricUsaged.isEmpty() || gasUsaged.isEmpty()) {
                JOptionPane.showMessageDialog(null, "输入不能为空！", "错误信息", JOptionPane.ERROR_MESSAGE);
            } else if (isPositiveInteger(houseID) && isPositiveNumber(waterUsaged) && isPositiveNumber(electricUsaged) && isPositiveNumber(gasUsaged)) {
                String info = "#E2##" + houseID + "##" + waterUsaged + "##" + electricUsaged + "##" + gasUsaged + "#";
                try {
                    test.pass(info);
                    Matcher m = r.matcher(test.get());
                    m.find();
                    m.find();
                    switch (m.group(1)) {
                        case "1":
                            m.find();
                            JOptionPane.showMessageDialog(null, "编辑用量成功");
                            break;
                        case "0":
                            JOptionPane.showMessageDialog(null, "编辑用量失败", "错误信息", JOptionPane.ERROR_MESSAGE);
                            break;
                        default:
                            break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "输入有误！", "错误信息", JOptionPane.ERROR_MESSAGE);
            }
            f.dispose();
        }

    }
}