import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class Search {
    static Connection con;
    public static void main(String[] args) {
        new Search(12100887);
    }
    Search(int uid)
    {

        try{
            con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/inventrylogin?allowPublickeyRetrieval=true&useSSL=false", "amisha", "17042003");
        }
        catch(Exception ex) { System.out.println(ex); }
        Font bold = new Font("Serif",1,35);
        Font itelic = new Font("Serif",2,21);
        Font plan = new Font("Serif",0,25);

        JFrame df = new JFrame("SEARCH ITEM");
        df.setSize(600,400);
        df.getContentPane().setBackground(new Color(86, 108, 211));
        df.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        df.setLocationRelativeTo(null);
        df.setLayout(null);

        ImageIcon ic2 = new ImageIcon("lpu.png");
        df.setIconImage(ic2.getImage());


        JLabel head = new JLabel("SEARCH ITEM",JLabel.CENTER);
        head.setFont(bold);

        JTextField id = new JTextField("Item Code*");
        id.setFont(plan);

        JLabel name = new JLabel("Name @");name.setBackground(new Color(247,183,93));name.setOpaque(true);
        JLabel stock = new JLabel("Stock @");stock.setBackground(new Color(247,183,93));stock.setOpaque(true);
        JLabel sp = new JLabel("Sale @");sp.setBackground(new Color(247,183,93));sp.setOpaque(true);
        JLabel cp = new JLabel("Purchase @");cp.setBackground(new Color(247,183,93));cp.setOpaque(true);

        JButton search = new JButton("SEARCH");
        search.setFont(plan);
        name.setFont(itelic);
        sp.setFont(itelic);
        cp.setFont(itelic);
        stock.setFont(itelic);

        search.setBackground(new Color(247,183,93));
        df.add(head);df.add(id);df.add(search);
        df.add(name);df.add(stock);df.add(sp);df.add(cp);
        head.setBounds(0,0,600,50);
        id.setBounds(50,70,200,40);
        search.setBounds(330,70,200,40);
        name.setBounds(50,150,200,40);
        stock.setBounds(50,250,200,40);
        sp.setBounds(330,150,200,40);
        cp.setBounds(330,250,200,40);

        String tablename = "UIT"+uid;
        class MyListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                String id_ = id.getText();
                try {
                    PreparedStatement ps = con.prepareStatement("select * from "+tablename+" where ITEM_ID = ?");
                    ps.setString(1,id_);
                    ResultSet rs = ps.executeQuery();

                    String msg = null;
                    if(rs.next())
                    {
                        name.setText(rs.getString(2));
                        stock.setText("Stock : "+rs.getString(6));
                        sp.setText("Sale @ RS. "+rs.getString(4));
                        cp.setText("Purchase @ RS. "+rs.getString(5));
                    }
                    else
                    {
                        name.setText("Name");
                        stock.setText("Stock");
                        sp.setText("Sale @");
                        cp.setText("Purchase @");
                        msg = "Item Does Not Exists";
                        JOptionPane.showMessageDialog(df, msg);
                    }
                } catch (Exception exc) {
                    System.out.println(exc);
                }
            }

        }

        MyListener ml = new MyListener();
        search.addActionListener(ml);

        df.setVisible(true);
    }
}
