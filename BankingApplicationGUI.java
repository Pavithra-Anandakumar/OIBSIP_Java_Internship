import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingApplicationGUI extends JFrame implements ActionListener {
    private JTextField accountNumberField;
    private JTextField nameField;
    private JPasswordField pinField;
    private JComboBox<String> bankTypeComboBox;
    private JComboBox<String> bankComboBox;
    private JComboBox<String> accountTypeComboBox;
    private JTextField amountField;
    private JComboBox<String> operationComboBox;
    private JButton submitButton;
    private JButton nextButton;

    private boolean authenticatedStep1 = false;

    // Dummy balance for demonstration
    private double balance = 100.0;

    public BankingApplicationGUI() {
        setTitle("Banking Application");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setBounds(50, 30, 120, 20);
        add(accountNumberLabel);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(180, 30, 150, 20);
        add(accountNumberField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 120, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 60, 150, 20);
        add(nameField);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(50, 90, 120, 20);
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(180, 90, 150, 20);
        add(pinField);

        nextButton = new JButton("Next");
        nextButton.setBounds(180, 120, 100, 30);
        nextButton.addActionListener(this);
        add(nextButton);

        JLabel bankTypeLabel = new JLabel("Bank Type:");
        bankTypeLabel.setBounds(50, 180, 120, 20);
        add(bankTypeLabel);

        bankTypeComboBox = new JComboBox<>(new String[]{"Public Sector Bank", "Private Sector Bank"});
        bankTypeComboBox.setBounds(180, 180, 150, 20);
        bankTypeComboBox.addActionListener(this);
        add(bankTypeComboBox);

        JLabel bankLabel = new JLabel("Bank:");
        bankLabel.setBounds(50, 210, 120, 20);
        add(bankLabel);

        bankComboBox = new JComboBox<>();
        bankComboBox.setBounds(180, 210, 250, 20);
        add(bankComboBox);
        bankComboBox.setEnabled(false); // Disabled by default
        bankComboBox.setVisible(false); // Hidden by default

        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(50, 240, 120, 20);
        add(accountTypeLabel);

        accountTypeComboBox = new JComboBox<>(new String[]{"Savings", "Checking", "Recurring Deposit (RD)", "Fixed Deposit (FD)", "Current"});
        accountTypeComboBox.setBounds(180, 240, 150, 20);
        add(accountTypeComboBox);
        accountTypeComboBox.setEnabled(false); // Disabled by default
        accountTypeComboBox.setVisible(false); // Hidden by default

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 270, 120, 20);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(180, 270, 150, 20);
        add(amountField);
        amountField.setEnabled(false); // Disabled by default
        amountField.setVisible(false); // Hidden by default

        JLabel operationLabel = new JLabel("Operation:");
        operationLabel.setBounds(50, 300, 120, 20);
        add(operationLabel);

        operationComboBox = new JComboBox<>(new String[]{"Deposit", "Withdraw", "Check Balance"});
        operationComboBox.setBounds(180, 300, 150, 20);
        add(operationComboBox);
        operationComboBox.setEnabled(false); // Disabled by default
        operationComboBox.setVisible(false); // Hidden by default

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 330, 100, 30);
        submitButton.addActionListener(this);
        add(submitButton);
        submitButton.setEnabled(false); // Disabled by default
        submitButton.setVisible(false); // Hidden by default

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            authenticateStep1();
        } else if (e.getSource() == bankTypeComboBox) {
            updateBankComboBox();
        } else if (e.getSource() == submitButton) {
            if (!authenticatedStep1) {
                JOptionPane.showMessageDialog(this, "Please authenticate first.");
                return;
            }
            String bank = (String) bankComboBox.getSelectedItem();
            String accountNumber = accountNumberField.getText();
            String accountType = (String) accountTypeComboBox.getSelectedItem();
            String name = nameField.getText();
            String pin = new String(pinField.getPassword());
            String operation = (String) operationComboBox.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            // Perform operation based on user input
            switch (operation) {
                case "Deposit":
                    balance += amount;
                    JOptionPane.showMessageDialog(this, "Deposit of $" + amount + " successful.");
                    break;
                case "Withdraw":
                    if (balance >= amount) {
                        balance -= amount;
                        JOptionPane.showMessageDialog(this, "Withdrawal of $" + amount + " successful.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient funds.");
                    }
                    break;
                case "Check Balance":
                    JOptionPane.showMessageDialog(this, "Balance: $" + balance);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid operation.");
            }
        }
    }

    private boolean isValidAccountNumber(String accountNumber) {
        return accountNumber.matches("\\d{10}");
    }

    private boolean isValidPIN(String pin) {
        return pin.matches("\\d{4}");
    }

    private void authenticateStep1() {
        String accountNumber = accountNumberField.getText();
        if (isValidAccountNumber(accountNumber)) {
            JOptionPane.showMessageDialog(this, "Step 1 Authentication Successful. Proceed to Step 2.");
            authenticatedStep1 = true;
            enableStep2Components();
        } else {
            JOptionPane.showMessageDialog(this, "Step 1 Authentication Failed. Please check your account number.");
            authenticatedStep1 = false;
            disableStep2Components();
        }
    }

    private void enableStep2Components() {
        bankComboBox.setEnabled(true);
        bankComboBox.setVisible(true);
        accountTypeComboBox.setEnabled(true);
        accountTypeComboBox.setVisible(true);
        amountField.setEnabled(true);
        amountField.setVisible(true);
        operationComboBox.setEnabled(true);
        operationComboBox.setVisible(true);
        submitButton.setEnabled(true);
        submitButton.setVisible(true);
    }

    private void disableStep2Components() {
        bankComboBox.setEnabled(false);
        bankComboBox.setVisible(false);
        accountTypeComboBox.setEnabled(false);
        accountTypeComboBox.setVisible(false);
        amountField.setEnabled(false);
        amountField.setVisible(false);
        operationComboBox.setEnabled(false);
        operationComboBox.setVisible(false);
        submitButton.setEnabled(false);
        submitButton.setVisible(false);
    }

    private void updateBankComboBox() {
        String bankType = (String) bankTypeComboBox.getSelectedItem();
        String[] banks;
        if (bankType.equals("Public Sector Bank")) {
            banks = new String[]{"State Bank of India (SBI)", "Indian Bank", "Indian Overseas Bank", "Bank of Baroda", "Canara Bank", "Punjab National Bank", "Union Bank of India", "Bank of India", "Central Bank of India", "Bank of Maharashtra", "UCO Bank", "Punjab & Sind Bank", "IDBI Bank", "Allahabad Bank (merged with Indian Bank)"};
        } else {
            banks = new String[]{"HDFC Bank", "ICICI Bank", "Axis Bank", "Kotak Mahindra Bank", "Yes Bank", "Federal Bank", "Karur Vysya Bank", "South Indian Bank", "City Union Bank", "Tamilnad Mercantile Bank", "Lakshmi Vilas Bank (merged with DBS Bank India)", "DCB Bank", "IndusInd Bank", "RBL Bank", "IDFC First Bank"};
        }
        bankComboBox.setModel(new DefaultComboBoxModel<>(banks));
    }

    public static void main(String[] args) {
        new BankingApplicationGUI();
    }
}

