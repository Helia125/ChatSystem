
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
import java.awt.Font;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Java
 */
public class MessageDialog extends javax.swing.JFrame {

    static Connection connection = null;
    static Statement stmt;
    static ResultSet rs;

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

    JTable table;

    public MessageDialog() {
        initComponents();

    }
    Connection con = null;
    int i = 0;
    PreparedStatement ps = null;

 
    @SuppressWarnings("unchecked")
                       
    void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setLineWrap(true);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("<html><u>View Message</u></html>");
        jLabel1.setFont(new Font("Arial", Font.PLAIN, 16));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95) //label
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7) //left
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                ))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addContainerGap(7, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                )
        );

        pack();
    }

    private void table() {
        try {
            String query = "Select * from Project.dbo.PostNotice;";
            PreparedStatement pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void refreshtable() {
        try {
            String query = "Select * from Project.dbo.PostNotice;";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
    
            pst.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

                      
    void readdata() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:Project");
            ps = con.prepareStatement("select * from Project.dbo.PostNotice");
            rs = ps.executeQuery();

            while (rs.next()) {
       
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        readdata();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MessageDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MessageDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MessageDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MessageDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       

 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MessageDialog().setVisible(true);

            }
        });
    }

  
    javax.swing.JLabel jLabel1;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTextArea jTextArea1;
           
}
