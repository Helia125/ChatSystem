package chat1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
import javax.swing.*;
 
public class register implements ActionListener {

	JFrame jf;
	JTextField jtf;
	JPasswordField jpf, jpf2;
 
	public register() {
		jf = new JFrame("register");
		jf.setLayout(new GridLayout(6, 1));
		jf.add(new JPanel());
		JLabel jl1 = new JLabel(" username：");
		jtf = new JTextField(12);
		JPanel jp1 = new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jf.add(jp1);
 
		JLabel jl2 = new JLabel(" password：");
		jpf = new JPasswordField(12);
		JPanel jp2 = new JPanel();
		jp2.add(jl2);
		jp2.add(jpf);
		jf.add(jp2);
 
		JLabel jl3 = new JLabel("confirm：");
		jpf2 = new JPasswordField(12);
		JPanel jp3 = new JPanel();
		jp3.add(jl3);
		jp3.add(jpf2);
		jf.add(jp3);
 
		JPanel jp4 = new JPanel();
		JButton jb1 = new JButton("success");
		jb1.addActionListener(this);
		JButton jb2 = new JButton("cancel");
		jb2.addActionListener(this);
		jp4.add(jb1);
		jp4.add(jb2);
		jf.add(jp4);
 
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setSize(300, 240);
		jf.setLocation(300, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
	public static void main(String[] args) {
		new register();
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		String comm = e.getActionCommand();
		if (comm.equals("success")) {
			// jf.dispose();
			if ("".equals(jtf.getText())
					|| "".equals(new String(jpf.getPassword()))
					|| jpf.getPassword() == null
					|| "".equals(new String(jpf2.getPassword()))
					|| jpf2.getPassword() == null) {
				final JFrame jf = new JFrame("error");
				JLabel jl = new JLabel("username or password cannot be empty！");
				JPanel jp1 = new JPanel();
				JPanel jp2 = new JPanel();
				jp1.add(jl);
				jf.add(jp1);
				JButton jb = new JButton("confirm");
				jb.addActionListener(new ActionListener() {
 
					@Override
					public void actionPerformed(ActionEvent e) {
						jf.dispose();
					}
 
				});
				jp2.add(jb);
				jf.add(jp2);
				jf.setLayout(new GridLayout(2, 1));
				jf.setResizable(false);
				jf.setVisible(true);
				jf.pack();
				jf.setLocation(400, 300);
			} else {
				String s = jtf.getText() + "&&" + new String(jpf.getPassword())
						+ "\r\n";
				String name = jtf.getText() + "&&";
				File file = new File("/Users/shirley_lee/Documents/workspace/Test/src/reg.txt");
				try {
					file.createNewFile(); 
				} catch (IOException e2) {
					e2.printStackTrace();
				}
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
					if (i == -1) { 
						if (new String(jpf.getPassword()).equals(new String(
								jpf2.getPassword()))) {
							try {
								FileOutputStream fos = new FileOutputStream(
										file, true);
								// true就是追加，false就是替换。
								fos.write(s.getBytes());
								fos.close();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							int a = JOptionPane.showConfirmDialog(jf, "success！\n"
									+ "username ： " + jtf.getText() + "\npassword ： "
									+ new String(jpf.getPassword())
									+ "\nclick login jump to the frame", "result",
									JOptionPane.OK_CANCEL_OPTION);
							if (a == 0) {
								jf.dispose();
								new Login();
							}
						} else {
							JOptionPane.showConfirmDialog(
									jf, 
									"password not matched!\nplease input the password again！", "password not matched",
									JOptionPane.CLOSED_OPTION);
							jpf.setText(null);
							jpf2.setText(null);
							jpf.requestFocus();
						}
					} else {
 
						JOptionPane.showConfirmDialog(
								jf, 
								"username is already exist!\nplease change another one！", "error",
								JOptionPane.CLOSED_OPTION);
						jtf.setText(null);
						jpf.setText(null);
						jpf2.setText(null);
						jtf.requestFocus();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (comm.equals("cancel")) {
			System.exit(0);
		}
 
	}
}
