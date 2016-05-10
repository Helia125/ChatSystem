/*
 * 
 * 
 * @author: Hang ZHAO
 * @author: Yang ZHAO
 * @author: Xiaoyi Li
 * 
 * 
 * 
 */
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

public class MessageSend extends JFrame {

    JComboBox comboBox;
    JFrame frame;
    JTable table;
    JTextArea textArea;
    JButton btnSend;
    static Connection connection = null;
    static Statement stmt;
    static ResultSet rs;
//make connection with  database
    public static Connection dbConnector() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection = DriverManager.getConnection("jdbc:odbc:Project");
         
            return connection;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error");
            return null;
        }
    }

   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MessageSend window = new MessageSend();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
//select data from database and add it on combobox
    public void FillComboBox() {
        try {
            String query = "SELECT usertype + '- ' + userid as UserId FROM Project.dbo.admins UNION SELECT usertype + '- ' + userid as UserId FROM Project.dbo.teachers UNION SELECT usertype + '- ' + userid as UserId FROM Project.dbo.students";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                comboBox.addItem(rs.getString("UserId"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //make conncetion with database 
    public MessageSend() throws SQLException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection = DriverManager.getConnection("jdbc:odbc:Project");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageSend.class.getName()).log(Level.SEVERE, null, ex);
        }

        initialize();
    }
//initialize the frame
    public void initialize() {

        frame = new JFrame("Compose");
        frame.setBounds(100, 100, 400, 330);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.getContentPane().setLayout(null);
      

        comboBox = new JComboBox();
        comboBox.setBounds(117, 20, 167, 29);
        frame.getContentPane().add(comboBox);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(49, 63, 292, 163);
        frame.getContentPane().add(scrollPane);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        btnSend = new JButton("Send");
        btnSend.setFont(new Font("Verdana", Font.PLAIN, 16));
        btnSend.setBounds(155, 245, 92, 29);
        frame.getContentPane().add(btnSend);

       
                
        FillComboBox();
    }
}
