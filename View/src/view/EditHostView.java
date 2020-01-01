package view;

import client.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Vanmay（1161120282）
 */
public class EditHostView {
    Test test;

    public EditHostView(Test test) {
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

    private JFrame f = new JFrame("修改住户");
    private Font font = new Font("黑体", Font.PLAIN, 18);
    private JButton editBtn = new JButton("修改");
    private JButton resetBtn = new JButton("重置");
    private JLabel hostIDLab = new JLabel("待修改用户编号");
    private JTextField hostIDText = new JTextField();
    private JLabel hostNameLab = new JLabel("住户姓名");
    private JTextField hostNameText = new JTextField();
    private JLabel idCardIDLab = new JLabel("身份证号");
    private JTextField idCardIDText = new JTextField();
    private JLabel hostSexLab = new JLabel("住户性别");
    private String[] hostSexChoice = new String[]{"男", "女"};
    private JComboBox<String> hostSexChoicer = new JComboBox<>(hostSexChoice);
    private JLabel hostTelLab = new JLabel("联系电话");
    private JTextField hostTelText = new JTextField();
    private JLabel hostEmailLab = new JLabel("电子邮箱");
    private JTextField hostEmailText = new JTextField();
    private JLabel hostWorkPlaceLab = new JLabel("工作地址");
    private JTextArea hostWorkPlaceText = new JTextArea(3, 10);

    public  void init() {
        EditHostListener editHostListener = new EditHostListener(test);
        ResetListener resetListener = new ResetListener();
        editBtn.addActionListener(editHostListener);
        resetBtn.addActionListener(resetListener);

        hostWorkPlaceText.setLineWrap(true);
        contentPanel.setLayout(null);
        contentPanel.add(hostIDLab);
        contentPanel.add(hostIDText);
        contentPanel.add(hostNameLab);
        contentPanel.add(hostNameText);
        contentPanel.add(idCardIDLab);
        contentPanel.add(idCardIDText);
        contentPanel.add(hostSexLab);
        contentPanel.add(hostSexChoicer);
        contentPanel.add(hostTelLab);
        contentPanel.add(hostTelText);
        contentPanel.add(hostEmailLab);
        contentPanel.add(hostEmailText);
        contentPanel.add(hostWorkPlaceLab);
        contentPanel.add(hostWorkPlaceText);
        contentPanel.add(editBtn);
        contentPanel.add(resetBtn);

        hostIDLab.setBounds(35, 50, 200, 35);
        hostIDLab.setFont(font);
        hostIDText.setBounds(185, 50, 200, 35);
        hostIDText.setFont(font);
        hostNameLab.setBounds(90, 100, 100, 35);
        hostNameLab.setFont(font);
        hostNameText.setBounds(185, 100, 200, 35);
        hostNameText.setFont(font);
        idCardIDLab.setBounds(90, 150, 100, 35);
        idCardIDLab.setFont(font);
        idCardIDText.setBounds(185, 150, 200, 35);
        idCardIDText.setFont(font);
        hostSexLab.setBounds(90, 200, 100, 35);
        hostSexLab.setFont(font);
        hostSexChoicer.setBounds(185, 200, 200, 35);
        hostSexChoicer.setFont(font);
        hostTelLab.setBounds(90, 250, 100, 35);
        hostTelLab.setFont(font);
        hostTelText.setBounds(185, 250, 200, 35);
        hostTelText.setFont(font);
        hostEmailLab.setBounds(90, 300, 100, 35);
        hostEmailLab.setFont(font);
        hostEmailText.setBounds(185, 300, 200, 35);
        hostEmailText.setFont(font);
        hostWorkPlaceLab.setBounds(90, 350, 100, 35);
        hostWorkPlaceLab.setFont(font);
        hostWorkPlaceText.setBounds(185, 350, 200, 135);
        hostWorkPlaceText.setFont(font);
        editBtn.setBounds(125, 500, 80, 35);
        editBtn.setFont(font);
        resetBtn.setBounds(275, 500, 80, 35);
        resetBtn.setFont(font);

        f.add(contentPanel);
        f.setBounds(700, 200, 500, 700);
        f.setVisible(true);


    }

     class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            hostIDText.setText("");
            hostNameText.setText("");
            idCardIDText.setText("");
            hostTelText.setText("");
            hostEmailText.setText("");
            hostWorkPlaceText.setText("");
        }
    }

     class EditHostListener implements ActionListener {
        Test test = null;

        public EditHostListener(Test test) {
            this.test = test;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String hostID = hostIDText.getText();
            String hostName = hostNameText.getText();
            String idCardID = idCardIDText.getText();
            String hostSex = (String) hostSexChoicer.getSelectedItem();
            String hostTel = hostTelText.getText();
            String hostEmail = hostEmailText.getText();
            String hostWorkPlace = hostWorkPlaceText.getText();
            if (hostID.isEmpty()||hostName.isEmpty()||idCardID.isEmpty()||hostTel.isEmpty()||hostEmail.isEmpty()||hostWorkPlace.isEmpty()) {
                JOptionPane.showMessageDialog(null, "输入不能为空！", "错误信息", JOptionPane.ERROR_MESSAGE);
                return;
            } else if(isPositiveInteger(hostID)&&isPositiveInteger(idCardID)) {
                String info = "#E1##" + hostID + "##" + hostName + "##" + idCardID + "##" + hostSex + "##" + hostTel + "##" + hostEmail + "##" + hostWorkPlace + "#";
                try {
                    test.pass(info);
                    Matcher m = r.matcher(test.get());
                    m.find();
                    m.find();
                    switch (m.group(1)) {
                        case "1":
                            JOptionPane.showMessageDialog(null, "修改住户 " +hostName+ " 成功！");
                            break;
                        case "0":
                            JOptionPane.showMessageDialog(null, "修改住户失败！","错误信息", JOptionPane.ERROR_MESSAGE);
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