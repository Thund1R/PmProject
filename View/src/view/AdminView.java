package view;

import client.Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;


/**
 * @author Vanmay（1161120282）
 */
public class AdminView {

    private static Pattern r = Pattern.compile("\\#(.*?)#");
    Test test;
    JPanel adminPanel = new JPanel();
    JFrame f = new JFrame("物业费管理系统——管理员");
    Font font1 = new Font("微软雅黑", Font.PLAIN, 16);
    JMenuBar menuBar = new JMenuBar();
    JMenu systemSet = new JMenu("系统设置");
    JMenu hostManage = new JMenu("住户管理");
    JMenu arrearsManage = new JMenu("费用管理");
    JMenu more = new JMenu("更多");
    JMenuItem resetPwd = new JMenuItem("修改密码");
    JMenuItem logout = new JMenuItem("退出系统");
    JMenuItem addHost = new JMenuItem("增加住户");
    JMenuItem deleteHost = new JMenuItem("删除住户");
    JMenuItem searchHost = new JMenuItem("搜索住户");
    JMenuItem showAllHost = new JMenuItem("显示全部");
    JMenuItem editHost = new JMenuItem("修改用户");
    JMenuItem showAllArrears = new JMenuItem("显示全部");
    JMenuItem editUsaged = new JMenuItem("编辑用量");
    JMenuItem payArrears = new JMenuItem("缴费功能");
    JMenuItem searchArrears = new JMenuItem("查询欠费");
    JMenuItem updateArrears = new JMenuItem("更新欠费");
    JMenuItem sortArrears = new JMenuItem("排序欠费");
    JMenuItem about = new JMenuItem("关于我");
    AddHostListener addHostListener = new AddHostListener();
    JDesktopPane desktopPane = new JDesktopPane();
    searchHostListener searchHostListener = new searchHostListener();

    DeleteHostListener deleteHostListener = new DeleteHostListener();
    LogoutListener logoutListener = new LogoutListener();
    //    ResetPwdListener resetPwdListener = new ResetPwdListener();
    AboutMeListener aboutMeListener = new AboutMeListener();
    EditHostListener editHostListener = new EditHostListener();
    ShowAllHostListener showAllHostListener = new ShowAllHostListener();
    ShowAllArrearsListener showAllArrearsListener = new ShowAllArrearsListener();
    SearchArrearsListener searchArrearsListener = new SearchArrearsListener();
    PayArrearsListener payArrearsListener = new PayArrearsListener();
    EditUsagedListener editUsagedListener = new EditUsagedListener();
    UpdateArrearsListener updateArrearsListener = new UpdateArrearsListener();
    SortArrearsListener sortArrearsListener = new SortArrearsListener();

    ImageIcon addHostIcon = new ImageIcon("src/images\\addHost.png");
    ImageIcon editUsagedIcon = new ImageIcon("src/images\\edit.png");
    ImageIcon deleteIcon = new ImageIcon("src/images\\delete.png");
    ImageIcon searchIcon = new ImageIcon("src/images\\search.png");
    ImageIcon editIcon = new ImageIcon("src/images\\edit.png");
    ImageIcon systemIcon = new ImageIcon("src/images\\system.png");
    ImageIcon hostIcon = new ImageIcon("src/images\\host.png");
    ImageIcon pmIcon = new ImageIcon("src/images\\pm.png");
    ImageIcon moreIcon = new ImageIcon("src/images\\more.png");
    ImageIcon pwdIcon = new ImageIcon("src/images\\pwd.png");
    ImageIcon logoutIcon = new ImageIcon("src/images\\logout.png");
    ImageIcon aboutIcon = new ImageIcon("src/images\\about.png");
    ImageIcon allHostIcon = new ImageIcon("src/images\\allHost.png");
    ImageIcon allArrearsIcon = new ImageIcon("src/images\\allArrears.png");
    ImageIcon updateIcon = new ImageIcon("src/images\\update.png");
    ImageIcon sortIcon = new ImageIcon("src/images\\sort.png");
    ImageIcon payIcon = new ImageIcon("src/images\\pay.png");

    private String hostname;

    public AdminView(Test test) {

        this.test = test;
    }

    public void init(String s) {

        setHostname(s);
        desktopPane.setBackground(Color.LIGHT_GRAY);
        f.setContentPane(desktopPane);

        menuBar.add(systemSet);
        menuBar.add(hostManage);
        menuBar.add(arrearsManage);
        menuBar.add(more);

        systemSet.add(resetPwd);
        systemSet.add(logout);
        systemSet.setIcon(systemIcon);
        systemSet.setFont(font1);
        logout.setIcon(logoutIcon);
        logout.setFont(font1);
        logout.addActionListener(logoutListener);
        resetPwd.setIcon(pwdIcon);
        resetPwd.setFont(font1);
//        resetPwd.addActionListener(resetPwdListener);

        hostManage.add(showAllHost);
        hostManage.add(addHost);
        hostManage.add(deleteHost);
        hostManage.add(searchHost);
        hostManage.add(editHost);


        hostManage.setIcon(hostIcon);
        hostManage.setFont(font1);
        showAllHost.setIcon(allHostIcon);
        showAllHost.setFont(font1);
        addHost.setIcon(addHostIcon);
        addHost.setFont(font1);
        deleteHost.setIcon(deleteIcon);
        deleteHost.setFont(font1);
        searchHost.setIcon(searchIcon);
        searchHost.setFont(font1);
        editHost.setIcon(editIcon);
        editHost.setFont(font1);
        sortArrears.setFont(font1);
        sortArrears.setIcon(sortIcon);


        addHost.addActionListener(addHostListener);
        deleteHost.addActionListener(deleteHostListener);
        showAllHost.addActionListener(showAllHostListener);
        searchHost.addActionListener(searchHostListener);

        editHost.addActionListener(editHostListener);

        arrearsManage.add(showAllArrears);
        showAllArrears.addActionListener(showAllArrearsListener);
        arrearsManage.add(editUsaged);
        arrearsManage.add(payArrears);
        arrearsManage.add(searchArrears);
        arrearsManage.add(updateArrears);
        arrearsManage.add(sortArrears);

        searchArrears.addActionListener(searchArrearsListener);
        payArrears.addActionListener(payArrearsListener);
        editUsaged.addActionListener(editUsagedListener);
        updateArrears.addActionListener(updateArrearsListener);
        sortArrears.addActionListener(sortArrearsListener);

        arrearsManage.setIcon(pmIcon);
        arrearsManage.setFont(font1);
        showAllArrears.setIcon(allArrearsIcon);
        showAllArrears.setFont(font1);
        editUsaged.setIcon(editUsagedIcon);
        editUsaged.setFont(font1);
        payArrears.setIcon(payIcon);
        payArrears.setFont(font1);
        searchArrears.setIcon(searchIcon);
        searchArrears.setFont(font1);
        updateArrears.setIcon(updateIcon);
        updateArrears.setFont(font1);

        more.add(about);
        more.setIcon(moreIcon);
        more.setFont(font1);
        about.setIcon(aboutIcon);
        about.setFont(font1);
        about.addActionListener(aboutMeListener);

        f.setJMenuBar(menuBar);
        f.add(adminPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(550, 100, 800, 800);
        f.setVisible(true);
    }

    public void setHostname(String hostname) {

        this.hostname = hostname;
    }

    class ShowAllHostListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            new ShowAllHostView(test);
        }
    }

    class AddHostListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            new AddHostView(test);
        }
    }

    class DeleteHostListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

//            new DeleteHostView(test);
        }

    }

    class searchHostListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            new SearchHostView(test);
        }
    }

    class EditHostListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("test1");
            new EditHostView(test);
        }
    }

    class AboutMeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(null, "作者：龙星全 章琳沛 徐靖鑫 郑扬");
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            int i = JOptionPane.showConfirmDialog(null, "退出系统", "确认退出", JOptionPane.YES_NO_OPTION, JOptionPane.CLOSED_OPTION);
            if (i == 0) {
                System.exit(0);
            }
        }
    }

    class ShowAllArrearsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ShowAllArrearsView(test);
        }
    }

    class SearchArrearsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new SearchArrearsView(test);
        }
    }

    class PayArrearsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new PayArrearsView(test);
        }
    }

    class EditUsagedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            new EditUsagedView(test);
        }

    }
    class UpdateArrearsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new UpdateArrearsView(test);
        }
    }
    class SortArrearsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame jf = new JFrame();
            String info ="#F8#";
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
                    for (int i = 0; i < 4; i++) {
                        m.find();
                        hostInfo.add(m.group(1));
                    }
                    data.add(hostInfo);
                }
                Vector<String> columnNames = new Vector<>();
                columnNames.add("用户编号");
                columnNames.add("用户姓名");
                columnNames.add("住房编号");
                columnNames.add("欠款总额");

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

