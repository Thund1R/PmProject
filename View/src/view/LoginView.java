package view;




import client.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginView {

    private static Pattern r = Pattern.compile("\\#(.*?)#");
    Test test = new Test();
    JPanel contentPanel = new JPanel();

    JFrame f = new JFrame("物业管理系统——登录界面");

    Font font = new Font("宋体", Font.PLAIN, 20);
    Font font1 = new Font("黑体", Font.PLAIN, 18);
    JButton logBtn = new JButton("登入");
    JButton newUserBtn = new JButton("新人");
    JButton resetBtn = new JButton("重置");
    JButton helpBtn = new JButton("帮助");
    private JLabel nameLab = new JLabel("用户名");
    private JTextField nameText = new JTextField();
    private JLabel passwordLab = new JLabel("密  码");
    private JPasswordField passwordText = new JPasswordField();
    String[] users = new String[]{"管理员", "住户"};
    JComboBox<String> userChoicer = new JComboBox<>(users);
    ResetListener reset = new ResetListener();
    NewUserListener nw = new NewUserListener();
    Helper hp = new Helper();
    ImageIcon loginIcon = new ImageIcon("src/images\\login.png");
    ImageIcon registIcon = new ImageIcon("src/images\\regist.png");
    ImageIcon resetIcon = new ImageIcon("src/images\\reset.png");
    ImageIcon helpIcon = new ImageIcon("src/images\\help.png");

    public void init() {
        f.getContentPane().setBackground(Color.BLACK);

        LoginListener loginListener = new LoginListener();
        resetBtn.setIcon(resetIcon);
        helpBtn.setIcon(helpIcon);
        newUserBtn.setIcon(registIcon);
        logBtn.setIcon(loginIcon);
        resetBtn.addActionListener(reset);
        logBtn.addActionListener(loginListener);
        newUserBtn.addActionListener(nw);
        helpBtn.addActionListener(hp);

        contentPanel.setLayout(null);
        contentPanel.add(nameLab);
        contentPanel.add(nameText);
        contentPanel.add(passwordLab);
        contentPanel.add(passwordText);
        contentPanel.add(logBtn);
        contentPanel.add(userChoicer);
        contentPanel.add(resetBtn);
        contentPanel.add(newUserBtn);
        contentPanel.add(helpBtn);

        helpBtn.setBounds(360, 100, 100, 35);
        helpBtn.setFont(font1);
        newUserBtn.setBounds(360, 50, 100, 35);
        newUserBtn.setFont(font1);
        nameLab.setBounds(90, 50, 60, 35);
        nameLab.setFont(font1);
        nameText.setBounds(150, 50, 200, 35);
        nameText.setFont(font);
        passwordLab.setBounds(90, 100, 60, 35);
        passwordLab.setFont(font1);
        passwordText.setBounds(150, 100, 200, 35);
        passwordText.setFont(font);
        logBtn.setBounds(100, 190, 110, 30);
        logBtn.setFont(font1);
        userChoicer.setBounds(200, 145, 90, 30);
        userChoicer.setFont(font1);
        resetBtn.setBounds(280, 190, 110, 30);
        resetBtn.setFont(font1);

        f.add(contentPanel);
        f.setBounds(700, 400, 500, 300);
        f.setVisible(true);
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameText.setText("");
            passwordText.setText("");
        }
    }

    class NewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(null, "请联系管理员添加用户");
        }

    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String name = nameText.getText();
            String password = String.valueOf(passwordText.getPassword());
            String info = "#L1##" + name + "##" + password + "#";
            if (name.isEmpty() || password.isEmpty()) {
                f.dispose();
                JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
                new LoginView().init();
            } else {
                try {
                    test.pass(info);
                    Matcher m = r.matcher(test.get());
                    m.find();
                    m.find();
                    switch (m.group(1)) {
                        case "1":
                            m.find();
                            new AdminView(test).init(m.group(1));
                            JOptionPane.showMessageDialog(null, "欢迎管理员   " + m.group(1) + "  登入物业费管理系统");
                            f.dispose();
                            break;
                        case "0":
                            f.dispose();
                            JOptionPane.showMessageDialog(null, "用户名或密码错误","错误信息", JOptionPane.ERROR_MESSAGE);
                            new LoginView().init();
                            break;
                        default:
                            break;
                    }

                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        }
    }

    class Helper implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            JOptionPane.showMessageDialog(null, "新用户密码为123");
        }

    }


}