package usermanager;

import javax.swing.*;

import databaseconfig.DbCon;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
	private String type;
	private JTextField idField, nameField, usernameField, passwordField, confPasswordField, phoneField;
	private JButton register;
	
	public Register(String type) {
		this.type = type;
		
		setTitle("Register");
		setSize(500, 400);
		
		JLabel idLabel = new JLabel("ID: ");
		idField = new JTextField(3);
		
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        
        JLabel confPasswordLabel = new JLabel("Password:");
        confPasswordField = new JPasswordField(15);        

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(15);
        
        register = new JButton("Register");
        Color bgColor = new Color(68, 144, 212);
        register.setBackground(bgColor);
        register.setBorderPainted(false);
        
        register.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		register();
        	}
        });
        
		
		//Layout
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(idField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(confPasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(confPasswordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(phoneField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(register, gbc);
        
        add(mainPanel);
        setLocationRelativeTo(null);
    	setVisible(true);

	}
	
	public void register() {
		String id = idField.getText();
		String name = nameField.getText();
		String username = usernameField.getText();
		String password = passwordField.getText();
		String confPassword = confPasswordField.getText();
		String phone = phoneField.getText();
		
		
		if (id.equals("") ||  name.equals("") || username.equals("") || password.equals("") || confPassword.equals("") || phone.equals("")) {
			JOptionPane.showMessageDialog(null, "The field cannot be empty");
		}
		else if (!checkPassword(password, confPassword)) {
			JOptionPane.showMessageDialog(null, "The password don't match");
		}
		else {
			try {
				Connection con = DbCon.getConnection();
				PreparedStatement smt = con.prepareStatement("insert into user values(?, ?, ?, ?, ?, ?)");
				
				int iId = Integer.parseInt(id);
				int iPhone = Integer.parseInt(phone);
				
				smt.setInt(1, iId);
				smt.setString(2, name);
				smt.setString(3, username);
				smt.setString(4,  password);
				smt.setInt(5, iPhone);
				smt.setString(6,  type);
				
				int rowsAffected = smt.executeUpdate();
				
				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(null, "Registeration Successful");
					User user = new User(iId, username, name, iPhone, type);
					CurrentUser.getInstance().setCurrentUser(user);
					con.close();
				} else {
					JOptionPane.showMessageDialog(null, "Registration Failed. Try Again");
				}
				
			} 
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Boolean checkPassword(String password, String confPassword) {
		if (password.equals(confPassword)) {
			return true;
		}
		return false;
	}
	
}
