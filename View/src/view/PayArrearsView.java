package view;


import client.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayArrearsView {
    Test test;
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
    private JFrame f = new JFrame("缴费功能");
    private JLabel idLable = new JLabel("请输入住房编号：");
    private JTextField idText = new JTextField(45);
    private JLabel payLabel = new JLabel("请输入缴费金额：");
    private JTextField payText = new JTextField(45);
    private JButton searchBtn = new JButton("查询");
    private JButton resetBtn = new JButton("重置");
    private Font font = new Font("微软雅黑", Font.PLAIN, 18);


    public PayArrearsView(Test test) {
        this.test = test;
        init();
    }

    public void init() {
        PayListener payListener = new PayListener(test);
        ResetListener resetListener = new ResetListener();
        resetBtn.addActionListener(resetListener);
        searchBtn.addActionListener(payListener);
        contentPanel.setLayout(null);
        contentPanel.add(idLable);
        contentPanel.add(idText);
        contentPanel.add(payLabel);
        contentPanel.add(payText);
        contentPanel.add(searchBtn);
        contentPanel.add(resetBtn);

        payLabel.setFont(font);
        payText.setFont(font);
        idLable.setFont(font);
        idText.setFont(font);
        searchBtn.setFont(font);
        resetBtn.setFont(font);

        idLable.setBounds(40, 30, 200, 35);
        idText.setBounds(190, 30, 200, 35);
        payLabel.setBounds(40, 80, 200, 35);
        payText.setBounds(190, 80, 200, 35);
        searchBtn.setBounds(170, 160, 70, 35);
        resetBtn.setBounds(300, 160, 70, 35);
        f.add(contentPanel);
        f.setBounds(700, 400, 500, 260);
        f.setVisible(true);
    }

    class PayListener implements ActionListener {
        Test test = null;

        PayListener(Test test) {
            this.test = test;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            String houseID = idText.getText();
            String money = payText.getText();
            if (houseID.isEmpty() || money.isEmpty()) {
                JOptionPane.showMessageDialog(null, "输入不能为空！", "错误信息", JOptionPane.ERROR_MESSAGE);
            } else if (isPositiveInteger(houseID) && isPositiveNumber(money)) {
                String info = "#P1##" + houseID + "##" + money + "#";
                try {
                    test.pass(info);
                    System.out.println("data");
                    Matcher m = r.matcher(test.get());
                    m.find();
                    m.find();
                    if (m.group(1).equals("1")) {
                        JOptionPane.showMessageDialog(null, "缴费成功！");
                    } else if (m.group(1).equals("0")) {
                        JOptionPane.showMessageDialog(null, "缴费失败！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "输入必须为数字！", "错误信息", JOptionPane.ERROR_MESSAGE);
            }
            f.dispose();
        }
    }

    class ResetListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            idText.setText("");
        }
    }

}
