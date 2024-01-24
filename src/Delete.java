import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class Delete {
    static Connection con;
    public static void main(String[] args) {
        new Delete(12100887);
    }
    Delete(int uid)
    {

        try{
            con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/inventrylogin?allowPublickeyRetrieval=true&useSSL=false", "amisha", "17042003");
        }
        catch(Exception ex) { System.out.println(ex); }
        Font bold = new Font("Serif",1,35);
        Font plan = new Font("Serif",0,25);

        JFrame df = new JFrame("DELETE ITEM");
        df.setSize(400,400);
        df.getContentPane().setBackground(new Color(86, 108, 211));
        df.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        df.setLocationRelativeTo(null);
        df.setLayout(null);

        ImageIcon ic2 = new ImageIcon("lpu.png");
        df.setIconImage(ic2.getImage());


        JLabel head = new JLabel("Delete Item",JLabel.CENTER);
        head.setFont(bold);

        JTextField id = new JTextField("Item Code*");
        id.setFont(plan);


        JButton delete = new JButton("DELETE");
        delete.setFont(plan);
        delete.setBackground(new Color(247,183,93));

        df.add(head);df.add(id);df.add(delete);
        head.setBounds(0,0,400,50);
        id.setBounds(100,100,200,40);

        delete.setBounds(100,300,200,40);

        String tablename = "UIT"+uid;
        class MyListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                String id_ = id.getText();
                try {
                    PreparedStatement ps = con.prepareStatement("delete from "+tablename+" where ITEM_ID = ?");
                    ps.setString(1,id_);
                    int n = ps.executeUpdate();
                    if(n>0) {
                        System.out.println("Deleted");
                        JOptionPane.showMessageDialog(df, "Item Deleted Successfully! ");
                    }
                } catch (Exception exc) {
                    System.out.println(exc);
                }
            }

        }

        MyListener ml = new MyListener();
        delete.addActionListener(ml);

        df.setVisible(true);
    }
}

