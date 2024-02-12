package usermanager;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import databaseconfig.DbCon;
import usermanager.User;

import java.sql.*;

public class Login extends JFrame {
	private JButton submit, register;
	private JPanel newPanel;
	private JLabel userLabel, pwLabel;
	private JTextField tf1, tf2;
	public static User user;
	String type;
	
	public Login(String type) {
		this.type = type;
		
		System.out.println("Test");
		
		setTitle("Login");
        setSize(400, 300);

        userLabel = new JLabel("Username:");
        tf1 = new JTextField(15);

        pwLabel = new JLabel("Password:");
        tf2 = new JPasswordField(15);

        submit = new JButton("SUBMIT");
        
        JLabel registerTxt = new JLabel("Dont't have an account? ");
        register = new JButton("REGISTER");
        
        //perform login operation
        submit.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent ae) {
				String userVal = tf1.getText();
				String passVal = tf2.getText();
				
				login(userVal, passVal);
			}
	    });
        
        register.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		new Register(type);
        		dispose();
        	}
        }); 
        
        newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        newPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        newPanel.add(tf1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        newPanel.add(pwLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        newPanel.add(tf2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        newPanel.add(submit, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        newPanel.add(registerTxt, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        newPanel.add(register, gbc);

        add(newPanel);
        setResizable(false);
        setLocationRelativeTo(null);
		
		setVisible(true);
	
	}
	
	public void login(String userVal, String passVal) {
		
		Boolean found = false;
		
		try {
			Connection con = DbCon.getConnection();
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("select * from user");
	
					try {
					
						while (rs.next()) {
							if (userVal.equals(rs.getString(3))) {
								System.out.println(userVal);
								if (passVal.equals(rs.getString(4))) {
									JOptionPane.showMessageDialog(null, "Login Sucess");
									user = new User(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getInt(5), "admin");
									CurrentUser.getInstance().setCurrentUser(user);
									found = true;
									break;
								} else {
									JOptionPane.showMessageDialog(null, "Incorrect username or password");
									return;
								}
								
							}
						}
						
						if (!found) {
							JOptionPane.showMessageDialog(null, "User not found. Please register");
							DbCon.closeConnection();
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (found) {
				DbCon.closeConnection();
				dispose();
			}
		}
	}
}
