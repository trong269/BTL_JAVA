package GUIs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterGui extends BaseFrame{
    public RegisterGui(){
        super("Banking App Register");
    }
    @Override
    protected void addGuiComponents(){

        getContentPane().setBackground(Color.WHITE);
        //banking app label
        JLabel bankingAppLabel = new JLabel("PTITBANK PLUS");
        bankingAppLabel.setBounds(0, 20, super.getWidth(), 40);
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bankingAppLabel.setForeground(new Color(183, 28, 28));
        add(bankingAppLabel);
        //icon
        ImageIcon logoPTITicon = new ImageIcon("D:\\Workspace\\PTIT.jpg");
        Image logoPTITimage = logoPTITicon.getImage();
        Image resizePTITlogo = logoPTITimage.getScaledInstance(110,100,Image.SCALE_SMOOTH);
        ImageIcon resizePTITlogoIcon = new ImageIcon(resizePTITlogo);
        JLabel logoPTITLabel = new JLabel(resizePTITlogoIcon);
        logoPTITLabel.setBounds(0,60,super.getWidth(),50);
        logoPTITLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logoPTITLabel);


        //create username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20,120,getWidth()-50,20);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN,15));
        usernameLabel.setForeground(Color.RED);
        add(usernameLabel);

        //create username field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(20,145,getWidth()-50,30);
        usernameField.setFont(new Font("Dialog",Font.PLAIN,22));
        add(usernameField);

        //create phoneNumber label
        JLabel phoneNumberLabel = new JLabel("Phone number:");
        phoneNumberLabel.setBounds(20,180,getWidth()-50,20);
        phoneNumberLabel.setFont(new Font("Dialog", Font.PLAIN,15));
        phoneNumberLabel.setForeground(Color.RED);
        add(phoneNumberLabel);


        //create phoneNumber field
        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(20,205,getWidth()-50,30);
        phoneNumberField.setFont(new Font("Dialog",Font.PLAIN,22));
        add(phoneNumberField);

        //create CCCD/CMND label
        JLabel CCCD_CMNDLabel = new JLabel("CCCD/CMND:");
        CCCD_CMNDLabel.setBounds(20,240,getWidth()-50,20);
        CCCD_CMNDLabel.setFont(new Font("Dialog", Font.PLAIN,15));
        CCCD_CMNDLabel.setForeground(Color.RED);
        add(CCCD_CMNDLabel);

        //create CCCD/CMND field
        JTextField CCCD_CMNDField = new JTextField();
        CCCD_CMNDField.setBounds(20,275,getWidth()-50,30);
        CCCD_CMNDField.setFont(new Font("Dialog",Font.PLAIN,22));
        add(CCCD_CMNDField);
        //create password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20,300,getWidth()-50,20);
        passwordLabel.setFont(new Font("Dialog",Font.PLAIN,15));
        passwordLabel.setForeground(Color.RED);
        add(passwordLabel);

        //create password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20,320, getWidth()-50,30);
        passwordField.setFont(new Font("Dialog",Font.PLAIN,22));
        add(passwordField);

        //create rePassword label
        JLabel rePasswordLabel = new JLabel("Re-type Password:");
        rePasswordLabel.setBounds(20,360,getWidth()-50,20);
        rePasswordLabel.setFont(new Font("Dialog",Font.PLAIN,15));
        rePasswordLabel.setForeground(Color.RED);
        add(rePasswordLabel);

        //create repassword field
        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(20,385, getWidth()-50,30);
        rePasswordField.setFont(new Font("Dialog",Font.PLAIN,22));
        add(rePasswordField);

        //create Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(20,450, getWidth()-50,40);
        registerButton.setFont(new Font("Dialog",Font.BOLD,20));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(183, 28, 28));
        add(registerButton);

        //create Register Label
        JLabel loginLabel = new JLabel("<html><a href=\"#\">Have an account? Sign-in here</a><html>");
        loginLabel.setBounds(15,490,getWidth()-50,40);
        loginLabel.setFont(new Font("Dialog",Font.PLAIN,20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
