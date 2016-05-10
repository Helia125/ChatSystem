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
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;


public class Login extends JFrame implements ActionListener, MouseListener
{
	//use JFrame to create some panels and buttons
	JPanel UserPanel,NorthPanel;

	JLabel UserId,UserPassword;
	JLabel LblFrgtPass;

	JTextField TxtUser;

	JPasswordField TxtUserPass;

	JButton BtnLogin;

	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	boolean flag=false;
        ResultSet rs2;


	public Login()
	{       
                super("Welcome to Stevens.");
		add(GetUserPanel(), BorderLayout.CENTER);
		add(GetNorthPanel(), BorderLayout.NORTH);
	}
//make connection with database
	public boolean GetConnection()
	{
		flag=false;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Project");
			stmt=con.createStatement();
			flag=true;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			flag=false;
		}
		return flag;
	}
//close connection
	public boolean CloseConnection()
	{
		flag=false;
		try
		{
			if(con!=null)
			{
				con.close();
				flag=true;
			}
		}catch(Exception ex)
		{
			flag=false;
		}
		return flag;
	}

	public ResultSet GetRecords(String sql)
	{
		rs=null;
		try
		{
			rs=stmt.executeQuery(sql);

		}catch(Exception ex)
		{
			rs=null;
		}
		return rs;
	}


	JPanel GetNorthPanel()
	{
		NorthPanel = new JPanel();
		NorthPanel.setLayout(new FlowLayout());

		ImageIcon titleIcon = new ImageIcon("Capture1.jpg");
		JLabel title = new JLabel(titleIcon);

		NorthPanel.add(title);

		NorthPanel.setBackground(Color.white);
		return NorthPanel;
	}

	JLabel GetLblForgetPassword()
	{
		LblFrgtPass = new JLabel("Forgot Password ? ");
		return LblFrgtPass;
	}

//design the users panel
	JPanel GetUserPanel()
	{
		UserPanel=new JPanel();
		UserPanel.setLayout(new GridBagLayout());
		UserPanel.setBackground(Color.WHITE);



		GridBagConstraints GbcUserId = new GridBagConstraints();
		GbcUserId.gridx=1;
		GbcUserId.gridy=3;
		GbcUserId.fill=GridBagConstraints.BOTH;

		GbcUserId.insets = new Insets(0, 0, 0, 0);
		UserPanel.add(GetUserId(),GbcUserId);



		GridBagConstraints GbcTxtUser = new GridBagConstraints();
		GbcTxtUser.gridx=2;
		GbcTxtUser.gridy=3;

		GbcTxtUser.insets = new Insets(0, 0, 0, 0);
		UserPanel.add(GetTxtUser(),GbcTxtUser);



		GridBagConstraints GbcUserPassword = new GridBagConstraints();
		GbcUserPassword.gridx=1;
		GbcUserPassword.gridy=4;
		GbcUserPassword.fill=GridBagConstraints.BOTH;

		GbcUserPassword.insets = new Insets(10, 0, 0, 0);
		UserPanel.add(GetUserPassword(),GbcUserPassword);




		GridBagConstraints GbcTxtUserPass = new GridBagConstraints();
		GbcTxtUserPass.gridx=2;
		GbcTxtUserPass.gridy=4;

		GbcTxtUserPass.insets = new Insets(10, 0, 0, 0);
		UserPanel.add(GetTxtUserPass(),GbcTxtUserPass);




		GridBagConstraints GbcBtnLogin = new GridBagConstraints();
		GbcBtnLogin.gridx=2;
		GbcBtnLogin.gridy=5;

		GbcBtnLogin.insets = new Insets(30, -90, 0, 0);
		UserPanel.add(GetBtnLogin(),GbcBtnLogin);

		GridBagConstraints GbcLblFrgtPass = new GridBagConstraints();
		GbcLblFrgtPass.gridx=3;
		GbcLblFrgtPass.gridy=5;

		GbcLblFrgtPass.insets = new Insets(10, 0, 0, 0);
	



		return UserPanel;
	}

	JLabel GetUserId()
	{
		UserId = new JLabel("User ID       :      ");
		UserId.setFont(new Font("  Frutiger ", Font.PLAIN, 14));
		return UserId;
	}

	JTextField GetTxtUser()
	{
		TxtUser = new JTextField(10);
		return TxtUser;
	}

	JLabel GetUserPassword()
	{
		UserPassword = new JLabel("Password   :      ");
		UserPassword.setFont(new Font("  Frutiger ", Font.PLAIN, 14));
		return UserPassword;
	}

	JPasswordField GetTxtUserPass()
	{
		TxtUserPass = new JPasswordField(10);
		return TxtUserPass;
	}

	JLabel GetLblFrgtPass()
	{
		LblFrgtPass = new JLabel("Forgot Passord ?");
		return LblFrgtPass;
	}

	JButton GetBtnLogin()
	{
		BtnLogin = new JButton("  LogIn  ");
		
		BtnLogin.addActionListener(this);
		BtnLogin.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));


		return BtnLogin;
	}
//add actionEvent to buttons.
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==BtnLogin)
		{
			String User_Id = TxtUser.getText().trim();
			String User_Pass = TxtUserPass.getText().trim();
                        
                        String userId="";
                        String usertype="";
                        String Fname = "";
                        String Lname = "";
			
                        String sql = " SELECT userid, UserType, Fname, Lname, password FROM Project.dbo.admins WHERE UserId = '"+User_Id+"' and Password= '"+User_Pass+"' UNION SELECT userid, UserType, Fname, Lname, password FROM Project.dbo.teachers WHERE UserId = '"+User_Id+"' and Password= '"+User_Pass+"' UNION SELECT userid, UserType, Fname, Lname, password FROM Project.dbo.students WHERE UserId = '"+User_Id+"' and Password= '"+User_Pass+"'";
                        

			if(GetConnection()==true)
			{
                            try
                            {
                            	rs =GetRecords(sql);
    				int count = 0;
                                
				while(rs.next())
				{
                                    count = count + 1;
                                    userId =rs.getString(1);
                                    usertype=rs.getString(2);
                                    Fname = rs.getString(3);
                                    Lname = rs.getString(4);
                                    
				}

				if(count ==1)
				{
                                    if(usertype.equalsIgnoreCase("Teacher"))
                                    {
					stmt.executeUpdate(" delete from Project.dbo.temp ");
                                        stmt.executeUpdate(" insert into Project.dbo.temp values('"+userId+"', '"+Fname+"', '"+Lname+"', '"+usertype+"') ");
                                        JOptionPane.showMessageDialog(null, "Teacher");
                                            Teacher frame = new Teacher();
                                            //FRAME
                                            frame.setSize(850, 200);
                                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                            frame.setVisible(true);
                                            frame.setLocationRelativeTo(null);
                                            frame.setResizable(false);
                                    }
                                    else if(usertype.equalsIgnoreCase("Admins"))
                                    {
                                        stmt.executeUpdate(" delete from Project.dbo.temp ");
                                        stmt.executeUpdate(" insert into Project.dbo.temp values('"+userId+"', '"+Fname+"', '"+Lname+"', '"+usertype+"') ");
                                        JOptionPane.showMessageDialog(null, "Admin");
                                            Admin frame = new Admin();
                                            //FRAME
                                            frame.setSize(900, 200);
                                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                            frame.setVisible(true);
                                            frame.setLocationRelativeTo(null);
                                            frame.setResizable(false);
                                    }
                                    else if(usertype.equalsIgnoreCase("Student"))
                                    {
                                        stmt.executeUpdate(" delete from Project.dbo.temp ");
                                        stmt.executeUpdate(" insert into Project.dbo.temp values('"+userId+"', '"+Fname+"', '"+Lname+"', '"+usertype+"') ");
                                        JOptionPane.showMessageDialog(null, "Student");
                                            Student frame = new Student();
                                            //FRAME
                                            frame.setSize(600, 200);
                                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                            frame.setVisible(true);
                                            frame.setLocationRelativeTo(null);
                                            frame.setResizable(false);
                                    }
                                    dispose();
				}
				else
				{
                                    JOptionPane.showMessageDialog(null, "User Not Found!");
				}

				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				
				}
			}
			else
			{
				System.out.println("Not Connected");
			}
		}
                
                
	}
        
	public static void main(String[] args)
	{
		Login l = new Login();
                    l.setVisible(true);
                    l.setResizable(false);
                    l.setSize(600,400);
                    l.setLocationRelativeTo(null);
                    l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
     
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}