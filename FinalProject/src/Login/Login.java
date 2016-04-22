

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
 
public class Login implements ActionListener {
	/**
	 * @author 1: Hang Zhao
	 * @author 2: Yang Zhao
	 * @author 3: Xiaoyi Li
	 */
	JFrame jf;
	JTextField jtf;
	JPasswordField jpf;
 
	public Login() {
		jf = new JFrame("Login");
		jf.setLayout(new GridLayout(5, 1));
		jf.add(new JPanel());
		JLabel jl1 = new JLabel("    User ID£º  ");
		jtf = new JTextField(12);
		JPanel jp1 = new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jf.add(jp1);
 
		JLabel jl2 = new JLabel("Password£º ");
		jpf = new JPasswordField(12);
		JPanel jp2 = new JPanel();
		jp2.add(jl2);
		jp2.add(jpf);
		jf.add(jp2);
 
		JPanel jp3 = new JPanel();
		JButton jb1 = new JButton("Register");
		jb1.addActionListener(this);
		JButton jb2 = new JButton("Login");
		jb2.addActionListener(this);
		JButton jb3 = new JButton("Cancel");
		jb3.addActionListener(this);
		jp3.add(jb1);
		jp3.add(jb2);
		jp3.add(jb3);
		jf.add(jp3);
 
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setSize(300, 200);
		jf.setLocation(300, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
	public static void main(String[] args) {
		new Login();
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		String comm = e.getActionCommand();
		if (comm.equals("Register")) {
			jf.dispose();
			new register();
		} else if (comm.equals("Login")) {
			if ("".equals(jtf.getText())
					|| "".equals(new String(jpf.getPassword()))
					|| jpf.getPassword() == null) {
				JOptionPane.showConfirmDialog(
						jf, 
						"Please input your User ID and Password£¡", "Password or User ID is incorrect£¡",
						JOptionPane.DEFAULT_OPTION);
				jtf.setText(null);
				jpf.setText(null);
				jtf.requestFocus();
			} else {
				String s = jtf.getText() + "::" + new String(jpf.getPassword());
				String name = jtf.getText() + "::";
				File file = new File("C:/Users/hangz/Desktop/Login.txt");
				try { 
					FileInputStream fis = new FileInputStream(file);
					String s1 = "";
					byte[] b = new byte[1024];
					while (true) {
						int i = fis.read(b);
						if (i == -1)
							break;
						s1 = s1 + new String(b, 0, i);
					}
					fis.close();
					int i = s1.indexOf(name);
					int j = s1.indexOf(s);
					if (i == -1) {
						JOptionPane.showConfirmDialog(
								jf, 
								"We can not recognize your User ID £¡\n please input again, or register£¡", "Incorrect",
								JOptionPane.ERROR_MESSAGE);
						jtf.setText(null);
						jpf.setText(null);
						jtf.requestFocus();
					} else {
						if (j == -1) {
							JOptionPane.showConfirmDialog(
									jf, 
									"The password is incorrect! \n Please input again!", "Incorrect",
									JOptionPane.DEFAULT_OPTION);
							jpf.setText(null);
							jpf.requestFocus();
						} else {
							JOptionPane.showConfirmDialog(
									jf,
									"Success£¡\n" + "User ID £º " + jtf.getText()
											+ "\nPassword £º "
											+ new String(jpf.getPassword()),
									"Login Output", JOptionPane.DEFAULT_OPTION);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (comm.equals("Cancel")) {
			System.exit(0);
		}
	}
 
}
