package cafe;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import usermanager.Login;

public class WelcomePage extends JFrame {
	public WelcomePage() {
		setTitle("Bistro Blend: Blending Tastes, Crafting Moments");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel cafeName = new JLabel("Bistro Blend");
        cafeName.setFont(new Font("Georgia", Font.BOLD, 36));
        cafeName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Blending Tastes, Crafting Moments");
        subtitle.setFont(new Font("Georgia", Font.ITALIC, 16));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel text = new JLabel("CAFE MANAGEMENT SYSTEM");
        text.setFont(new Font("Monospaced", Font.BOLD, 14));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(cafeName);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(subtitle);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(text);
        mainPanel.add(Box.createVerticalStrut(40));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JButton adminLogin = new JButton("Admin Login");
        JButton custLogin = new JButton("Customer Login");
        
        adminLogin.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new Login("admin");
        	}
        });
        
        custLogin.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new Login("customer");
        	}
        });

        buttonPanel.add(adminLogin);
        buttonPanel.add(custLogin);

        mainPanel.add(buttonPanel);

        add(mainPanel);

        // Center the frame on the screen
        setLocationRelativeTo(null);

//        setVisible(true);
	}
}
