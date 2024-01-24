import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Add {
    static Connection con;


    public static void add_(int uid) {
        JTextField name;
        JTextField hsn;
        JTextField cat;
        JTextField sp;
        JTextField cp;
        JTextField min;
        JTextField os;
        JTextField loc;
        JButton save;
        JButton saven;
        try{
            con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/inventrylogin?allowPublickeyRetrieval=true&useSSL=false","amisha","17042003");
        }
        catch(Exception ex) { System.out.println(ex); }
        Font bold = new Font("Serif",1,35);

        Font plan = new Font("Serif",0,25);

        JFrame af = new JFrame("ADD ITEM");
        af.setSize(1000,500);
        af.getContentPane().setBackground(new Color(86, 108, 211));
        af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        af.setLocationRelativeTo(null);
        af.setLayout(null);

        ImageIcon ic2 = new ImageIcon("lpu.png");
        af.setIconImage(ic2.getImage());


        JLabel head = new JLabel("Add Item",JLabel.CENTER);
        head.setFont(bold);


        name = new JTextField("Item Name*");
        name.setFont(plan);
        name.setBackground(Color.WHITE);


        hsn = new JTextField("Item HSN*");
        hsn.setFont(plan);
        hsn.setBackground(Color.WHITE);

        cat = new JTextField("Category*");
        cat.setFont(plan);
        cat.setBackground(Color.WHITE);

        sp = new JTextField("Sale Price*");
        sp.setFont(plan);
        sp.setBackground(Color.WHITE);

        cp = new JTextField("Purchase Price*");
        cp.setFont(plan);
        cp.setBackground(Color.WHITE);

        os = new JTextField("Opening Quantity*");
        os.setFont(plan);
        os.setBackground(Color.WHITE);

        min = new JTextField("Min Stock To Maintain*");
        min.setFont(plan);
        min.setBackground(Color.WHITE);

        loc = new JTextField("Location*");
        loc.setFont(plan);
        loc.setBackground(Color.WHITE);

        save = new JButton("Save");
        save.setFont(plan);
        save.setBackground(new Color(247,183,93));

        saven = new JButton("Save & New");
        saven.setFont(plan);
        saven.setBackground(new Color(247,183,93));

        af.add(head);af.add(name);af.add(hsn);af.add(cat);af.add(sp);
        af.add(cp);af.add(os);af.add(min);af.add(loc);af.add(save);
        af.add(saven);

        head.setBounds(0,0,1000,50);
        name.setBounds(50,100,300,30);
        hsn.setBounds(50,150,300,30);
        cat.setBounds(50,200,300,30);
        sp.setBounds(600,100,300,30);
        cp.setBounds(600,150,300,30);
        os.setBounds(600,200,300,30);
        min.setBounds(50,300,300,30);
        loc.setBounds(50,350,300,30);
        save.setBounds(650,300,200,30);
        saven.setBounds(650,350,200,30);

        class MyListener implements ActionListener,FocusListener {

            public void actionPerformed(ActionEvent ae) {


                String c1 = name.getText();
                String c2 = hsn.getText();
                String c3 = sp.getText();
                String c4 = cp.getText();
                String c5 = os.getText();
                String c6 = cat.getText();
                String c7 = min.getText();
                String c8 = loc.getText();
                String tablename = "UIT"+uid;
                if(ae.getSource()==save)
                {
                    try
                    {
                        PreparedStatement ps = con.prepareStatement("insert into "+tablename+" values(null,?,?,?,?,?,?,?,?)");


                        ps.setString(1,c1);
                        ps.setString(2,c2);
                        ps.setString(3,c3);
                        ps.setString(4,c4);
                        ps.setString(5,c5);
                        ps.setString(6,c6);
                        ps.setString(8,c7);
                        ps.setString(7,c8);

                        int row = ps.executeUpdate();
                        if(row>0)
                        {
                            System.out.println("added");
                            JOptionPane.showMessageDialog(af,"Item Added");
                            af.dispose();
                        }

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                }
                else if(ae.getSource()==saven)
                {
                    try
                    {
                        PreparedStatement ps = con.prepareStatement("insert into "+tablename+" values(null,?,?,?,?,?,?,?,?)");


                        ps.setString(1,c1);
                        ps.setString(2,c2);
                        ps.setString(3,c3);
                        ps.setString(4,c4);
                        ps.setString(5,c5);
                        ps.setString(6,c6);
                        ps.setString(8,c7);
                        ps.setString(7,c8);

                        int row = ps.executeUpdate();
                        if(row>0)
                        {
                            JOptionPane.showMessageDialog(af,"Item Added");
                            name.setText("Item Name*");
                            hsn.setText("Item HSN*");
                            cat.setText("Category*");
                            cp.setText("Purchase Price*");
                            sp.setText("Sale Price*");
                            os.setText("Opening Quantity*");
                            min.setText("Min Stock To Maintain*");
                            loc.setText("Location*");
                        }

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                }


            }


            @Override
            public void focusGained(FocusEvent e) {
                if(e.getSource()==name)
                {name.setText("");}
                if(e.getSource()==hsn)
                {hsn.setText("");}
                if(e.getSource()==cat)
                {cat.setText("");}
                if(e.getSource()==os)
                {os.setText("");}
                if(e.getSource()==sp)
                {sp.setText("");}
                if(e.getSource()==cp)
                {cp.setText("");}
                if(e.getSource()==loc)
                {loc.setText("");}
                if(e.getSource()==min)
                {min.setText("");}
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(e.getSource()==name)
                {
                    if(name.getText().equals(""))
                    {
                        name.setText("Name*");
                    }
                }
                if(e.getSource()==cat)
                {
                    if(cat.getText().equals(""))
                    {
                        cat.setText("Category*");
                    }
                }

                if(e.getSource()==os)
                {
                    if(os.getText().equals(""))
                    {
                        os.setText("Opening Stock*");
                    }
                }
                if(e.getSource()==hsn)
                {
                    if(hsn.getText().equals(""))
                    {
                        hsn.setText("HSN*");
                    }
                }
                if(e.getSource().equals(""))
                {
                    if(loc.getText()=="")
                    {
                        loc.setText("Location*");
                    }
                }
                if(e.getSource().equals(""))
                {
                    if(min.getText()=="")
                    {
                        min.setText("Min Quantity*");
                    }
                }
                if(e.getSource()==cp)
                {
                    if(cp.getText().equals(""))
                    {
                        cp.setText("Purchase Price*");
                    }
                }
                if(e.getSource()==sp)
                {
                    if(sp.getText().equals(""))
                    {
                        sp.setText("Sale Price*");
                    }
                }


            }
        }

        MyListener ml = new MyListener();
        save.addActionListener(ml);
        saven.addActionListener(ml);
        name.addFocusListener(ml);
        hsn.addFocusListener(ml);
        cat.addFocusListener(ml);
        os.addFocusListener(ml);
        sp.addFocusListener(ml);
        cp.addFocusListener(ml);
        loc.addFocusListener(ml);
        min.addFocusListener(ml);

        af.setVisible(true);
    }
    Add(int uid)
    {
        add_(uid);
    }
}
