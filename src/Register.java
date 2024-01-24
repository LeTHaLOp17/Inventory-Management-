import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


class Register {

    public static void main(String[] args) {
        new Register();
    }
    static Connection con;
    Register()
    {

        //font
        Font bold = new Font("Serif",1,35);
        Font plan = new Font("Serif",0,20);


        JFrame lf = new JFrame("Register");
        lf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        lf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lf.setLocationRelativeTo(null);
        //lf.setUndecorated(true);
        lf.setBackground(new Color(223,223,223));
        lf.setLayout(null);

        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("icon/lpu.png"));
        lf.setIconImage(ic2.getImage());

        ImageIcon bg = new ImageIcon(ClassLoader.getSystemResource("icon/inventory.png"));
        JLabel l = new JLabel(bg);
        lf.setContentPane(l);

        JLabel header = new JLabel("Inventory Management System",JLabel.CENTER);
        header.setFont(bold);
        Color c1 = new Color(255, 255, 255);
        header.setForeground(c1);
        lf.add(header);
        header.setBounds(270,10,1080,50);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setOpaque(false);
        //p.setBackground(new Color(86, 108, 211));
        lf.add(p); p.setBounds(0,160,300,500);

        JLabel head = new JLabel("REGISTER",JLabel.CENTER);
        head.setFont(bold);
        head.setForeground(c1);
        p.add(head);

        head.setBounds(0,10,400,50);


        JTextField name = new JTextField("Name*");name.setFont(plan);
        JTextField uid = new JTextField("UID*"); uid.setFont(plan);
        JPasswordField pass = new JPasswordField("PASSWORD*"); pass.setFont(plan);pass.setEchoChar((char)0);
        JButton submit = new JButton("SUBMIT"); submit.setFont(plan); submit.setBackground(new Color(247,183,93));

        p.add(uid);p.add(pass);p.add(submit);p.add(name);
        name.setBounds(100,90,200,50);
        uid.setBounds(100,170,200,50);
        pass.setBounds(100,250,200,50);
        submit.setBounds(140,375,125,50);

        try{
            con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/inventrylogin?allowPublickeyRetrieval=true&useSSL=false", "amisha", "17042003");
        }
        catch(Exception ex) { System.out.println(ex); }

        class MyListener implements ActionListener, FocusListener
        {
            public void actionPerformed(ActionEvent ae)
            {
                int choice = JOptionPane.showConfirmDialog(lf,"Do you want to confirm your registration?","Confirmation",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION)
                {
                    String user = uid.getText();
                    String pwd = new String(pass.getPassword());
                    String na = name.getText();
                    if(!(user.equals("") || user.equals("UID*")) && !(pwd.equals("") || pwd.equals("Password*")) &&  !(na.equals("") || na.equals("Name*")))
                    {
                        try
                        {

                            PreparedStatement insert = con.prepareStatement("insert into lpu_user values (?,?,?)");
                            PreparedStatement createtb = con.prepareStatement("CREATE TABLE IF NOT EXISTS UIT" +user +
                                    " (ITEM_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                                    " ITEM_NAME VARCHAR(30), HSN VARCHAR(10), SALE_PRICE INT, " +
                                    " PURCHASE_PRICE INT, STOCK INT, CATEGORY VARCHAR(20), " +
                                    " LOCATION VARCHAR(10),MIN_STOCK INT) AUTO_INCREMENT = 100");
                            insert.setString(1,user);
                            insert.setString(2,na);
                            insert.setString(3,pwd);

                            int row1 = insert.executeUpdate();
                            if(row1>0)
                            {
                                System.out.print("Updated");
                            }
                            createtb.execute();
                            {
                                JOptionPane.showMessageDialog(lf,"Registration Confirmed\nRemember you UID and Password\nThank-you");
                                lf.dispose();
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                    }
                }

            }

            @Override
            public void focusGained(FocusEvent e) {
                if(e.getSource()==uid)
                {
                    uid.setText(null);
                }
                if(e.getSource()==pass)
                {
                    pass.setText(null);
                    pass.setEchoChar('*');
                }
                if(e.getSource()==name)
                {
                    name.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(e.getSource()==name)
                {
                    if(name.getText().equals(""))
                        name.setText("Name*");
                }
                if(e.getSource()==uid)
                {
                    if(uid.getText().equals(""))
                        uid.setText("UID*");
                }
                if(e.getSource()==pass)
                {
                    if(pass.getPassword().length==0)
                    {
                        pass.setEchoChar((char)0);
                        pass.setText("Password*");
                    }
                }
            }
        }
        MyListener ml = new MyListener();
        submit.addActionListener(ml);
        uid.addFocusListener(ml);
        pass.addFocusListener(ml);
        name.addFocusListener(ml);
        lf.setVisible(true);
    }
}

