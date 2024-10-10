package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingAppGui extends JFrame implements ActionListener {
    private JTextField currentBalanceField;

    public JTextField getCurrentBalanceField() {
        return currentBalanceField;
    }

    public BankingAppGui(String title) {
        initialize(title);
    }

    private void initialize(String title) {
        // Cấu hình JFrame
        setTitle(title);
        setSize(420, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // Gọi phương thức để thêm các thành phần GUI
        addGuiComponents();

        // Thiết lập giao diện Nimbus nếu có
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addGuiComponents() {
        // Tạo thông điệp chào mừng
        String welcomeMessage = "<html><body style='text-align:center'><b>Hello, User!</b><br>What would you like to do today?</body></html>";
        JLabel welcomeMessageLabel = new JLabel(welcomeMessage);
        welcomeMessageLabel.setBounds(0, 20, getWidth() - 10, 40);
        welcomeMessageLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeMessageLabel.setForeground(Color.blue);
        welcomeMessageLabel.setBackground(Color.white);
        welcomeMessageLabel.setOpaque(true);
        add(welcomeMessageLabel);
      

        // Tạo nhãn và trường số dư hiện tại
        JLabel currentBalanceLabel = new JLabel("Current Balance");
        currentBalanceLabel.setBounds(0, 80, getWidth() - 10, 30);
        currentBalanceLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        currentBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(currentBalanceLabel);

        // Hiển thị một giá trị số dư cố định cho mục đích demo
        currentBalanceField = new JTextField("$1000");
        currentBalanceField.setBounds(15, 120, getWidth() - 50, 40);
        currentBalanceField.setFont(new Font("Dialog", Font.BOLD, 28));
        currentBalanceField.setHorizontalAlignment(SwingConstants.RIGHT);
        currentBalanceField.setEditable(false);
        add(currentBalanceField);

        // Nút nạp tiền
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(15, 180, getWidth() - 50, 50);
        depositButton.setFont(new Font("Dialog", Font.BOLD, 22));
        depositButton.addActionListener(this);
        add(depositButton);

        // Nút rút tiền
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(15, 250, getWidth() - 50, 50);
        withdrawButton.setFont(new Font("Dialog", Font.BOLD, 22));
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        // Nút giao dịch trước đó
        JButton pastTransactionButton = new JButton("Past Transaction");
        pastTransactionButton.setBounds(15, 320, getWidth() - 50, 50);
        pastTransactionButton.setFont(new Font("Dialog", Font.BOLD, 22));
        pastTransactionButton.addActionListener(this);
        add(pastTransactionButton);

        // Nút chuyển khoản
        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(15, 390, getWidth() - 50, 50);
        transferButton.setFont(new Font("Dialog", Font.BOLD, 22));
        transferButton.addActionListener(this);
        add(transferButton);

        // Nút đăng xuất
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(15, 500, getWidth() - 50, 50);
        logoutButton.setFont(new Font("Dialog", Font.BOLD, 22));
        logoutButton.addActionListener(this);
        add(logoutButton);
    }

    public static void main(String[] args) {
        BankingAppGui app = new BankingAppGui("Banking Application");
        app.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
