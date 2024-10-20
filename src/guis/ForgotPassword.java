package guis;

import db_objs.MyJDBC;
import db_objs.Transaction;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ForgotPassword extends BaseFrame{
	 public ForgotPassword() {
	        super("Quên Mật Khẩu");
	    }

	    @Override
	    protected void addGuiComponents() {
	        // Set background color
	        getContentPane().setBackground(Color.WHITE);

	        // Create title label
	        JLabel titleLabel = new JLabel("Khôi phục mật khẩu");
	        titleLabel.setBounds(0, 30, super.getWidth(), 40);
	        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
	        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        titleLabel.setForeground(new Color(183, 28, 28));
	        add(titleLabel);

	        // Username label
	        JLabel usernameLabel = new JLabel("Tên đăng nhập");
	        usernameLabel.setBounds(20, 100, getWidth() - 30, 24);
	        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
	        usernameLabel.setForeground(new Color(183, 28, 28));
	        add(usernameLabel);

	        // Username field
	        JTextField usernameField = new JTextField();
	        usernameField.setBounds(20, 130, getWidth() - 50, 40);
	        usernameField.setFont(new Font("Dialog", Font.PLAIN, 25));
	        add(usernameField);

	        // Submit button
	        JButton submitButton = new JButton("Gửi yêu cầu khôi phục");
	        submitButton.setBounds(20, 190, getWidth() - 50, 40);
	        submitButton.setFont(new Font("Dialog", Font.BOLD, 20));
	        submitButton.setBackground(new Color(183, 28, 28));
	        submitButton.setForeground(Color.WHITE);
	        add(submitButton);

	        // Back to login button
	        JButton backButton = new JButton("Quay về màn hình đăng nhập");
	        backButton.setBounds(20, 250, getWidth() - 50, 40);
	        backButton.setFont(new Font("Dialog", Font.BOLD, 20));
	        backButton.setBackground(new Color(183, 28, 28));
	        backButton.setForeground(Color.WHITE);
	        add(backButton);

	        // Add ActionListener to backButton to return to login screen
	        backButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Logic to go back to the login screen
	                new LoginGui(); // Assuming you have a LoginScreen class
	                dispose(); // Close the current ForgotPassword window
	            }
	        });
	    }
}
