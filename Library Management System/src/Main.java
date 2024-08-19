import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main {
    HashMap<String,Integer> loginto = new HashMap<>();
    HashMap<Integer,String> map = new HashMap<>();
    public static String userID;
    public static int issues = 0;
    Scanner sc = new Scanner(System.in);

    public HashMap<Integer,String> book_database_server() {
        map.put(1, "BSc C_Programming");
        map.put(2, "EE : Electric Machine-2");
        map.put(3, "EE : Electric Drive");
        map.put(4, "EE : Power System-1");
        map.put(5, "EE : Power System-2");
        map.put(6, "EE : Basic Electrical");
        map.put(7, "EE : Power Electronics");
        map.put(8, "ECE : Basic Electronics");
        map.put(9, "ECE : Microprocessor & Microcontroller");
        map.put(10, "ECE : Analog Electronics");
        map.put(11, "ECE : Digital Electronics");
        map.put(12, "CSE : C Programming");
        map.put(13, "CSE : Java Programming");
        map.put(14, "CSE : Artificial Intelligence");
        map.put(15, "CSE : Object Oriented Programming (OOPs)");
        map.put(16, "IT : Data Structures & Algorithms");
        map.put(17, "IT : Databases - Networks");
        map.put(18, "IT : Statistics");
        map.put(19, "BBA : Principle of Management");
        map.put(20, "BBA : Economics for Engineers");
        map.put(21, "MATHS : Engineering Mathematics-1");
        map.put(22, "MATHS : Engineering Mathematics-2");
        map.put(23, "MATHS : Engineering Mathematics-3");
        map.put(24, "PHYS : Physics");
        map.put(25, "CHEM : Chemistry");
        map.put(26, "ENG : English");
        map.put(27, "BENG : Bengali");
        map.put(28, "HIND : Hindi");
        map.put(29, "EE : Control System");
        map.put(30, "EE : Electrical Circuit Theory");
        map.put(31, "EE : Electric & Hybrid Vehicle");
        map.put(32, "EE : Renewable Energy Sources");

        return map;
    }

    public void homepage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Digital Library Management System");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane, BorderLayout.CENTER);

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            JButton adminButton = new JButton("Admin Login");
            JButton userButton = new JButton("User Login");
            JButton exitButton = new JButton("Exit");

            panel.add(adminButton);
            panel.add(userButton);
            panel.add(exitButton);

            frame.add(panel, BorderLayout.SOUTH);

            adminButton.addActionListener(e -> {
                textArea.setText("Admin Login\n");
                admin_login(textArea);
            });

            userButton.addActionListener(e -> {
                textArea.setText("User Login\n");
                user_login(textArea);
            });

            exitButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(frame, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            });

            frame.setVisible(true);
        });
    }

    public void admin_login(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String userID = JOptionPane.showInputDialog("Enter User-Id:");
            String passwordStr = JOptionPane.showInputDialog("Enter Password:");
            int password = Integer.parseInt(passwordStr);

            loginto.put("Pavithra", 12345);
            loginto.put("Pavithraanand", 12345);

            if (loginto.containsKey(userID) && loginto.get(userID) == password) {
                textArea.setText("LOGIN SUCCESSFULLY...!\n");
                admin_mainpage(textArea);
            } else {
                textArea.setText("Invalid login credentials.!!! Please try again...\n");
            }
        });
    }

    public void user_login(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String userID = JOptionPane.showInputDialog("Enter User-Id:");
            String passwordStr = JOptionPane.showInputDialog("Enter Password:");
            int password = Integer.parseInt(passwordStr);

            loginto.put("Pavithra",12345 );
            loginto.put("Ananthi",12345);
            loginto.put("Harini", 12345);
            loginto.put("Sangee", 12345);
            loginto.put("Nandhana", 123451);

            if (loginto.containsKey(userID) && loginto.get(userID) == password) {
                textArea.setText("LOGIN SUCCESSFULLY...!\n");
                user_mainpage(textArea);
            } else {
                textArea.setText("Invalid login credentials.!!! Please try again...\n");
            }
        });
    }

    public void admin_mainpage(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String[] options = {"Add a New Book", "Update an Existing Book", "Delete an Existing Book", "Go To User MainPage", "Logout"};
            int choice = JOptionPane.showOptionDialog(null, "Select Option", "Admin MainPage", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    textArea.setText("Add a New Book\n");
                    add_new(textArea);
                    break;
                case 1:
                    textArea.setText("Update an Existing Book\n");
                    upd_old(textArea);
                    break;
                case 2:
                    textArea.setText("Delete an Existing Book\n");
                    del_old(textArea);
                    break;
                case 3:
                    textArea.setText("Go To User MainPage\n");
                    user_mainpage(textArea);
                    break;
                case 4:
                    textArea.setText("Logout Successfully\n");
                    homepage();
                    break;
            }
        });
    }

    public void user_mainpage(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String[] options = {"View a Specific Book", "Issue a Book", "Return a Book", "Logout"};
            int choice = JOptionPane.showOptionDialog(null, "Select Option", "User MainPage", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    textArea.setText("View a Specific Book\n");
                    views(textArea);
                    break;
                case 1:
                    textArea.setText("Issue a Book\n");
                    borrows(textArea);
                    break;
                case 2:
                    textArea.setText("Return a Book\n");
                    returns(textArea);
                    break;
                case 3:
                    textArea.setText("Logout Successfully\n");
                    homepage();
                    break;
            }
        });
    }

    public void add_new(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String bookNumberStr = JOptionPane.showInputDialog("Enter a New Book Number:");
            int addbook = Integer.parseInt(bookNumberStr);

            if (book_database_server().containsKey(addbook)) {
                textArea.setText("This Book Number already exists... Please try with another number...\n");
            } else if (addbook == 0 || addbook < 0) {
                textArea.setText("You can't assign Zero(0) or a Negative(-ve) number for a book...\n");
            } else {
                String addbookdetails = JOptionPane.showInputDialog("Enter New Book Details:");
                map.put(addbook, addbookdetails);
                textArea.setText("New Book added Successfully...\nNew Book No.:: " + addbook + "\nNew Book Name & Details:: " + map.get(addbook) + "\n");
            }
        });
    }

    public void upd_old(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String bookNumberStr = JOptionPane.showInputDialog("Enter a Book No. to Update its Details:");
            int updbook = Integer.parseInt(bookNumberStr);

            if (book_database_server().containsKey(updbook)) {
                String oldDetails = map.get(updbook);
                String upbookdetails = JOptionPane.showInputDialog("Backdated Book Name & Details:: " + oldDetails + "\nEnter the Updated Book Details:");
                map.replace(updbook, upbookdetails);
                textArea.setText("Book Details Updated Successfully...!\nUpdated Book No.:: " + updbook + "\nUpdated Book Name & Details:: " + map.get(updbook) + "\n");
            } else {
                textArea.setText("This Book No. is not available in the database. Please try with another number...\n");
            }
        });
    }

    public void del_old(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String bookNumberStr = JOptionPane.showInputDialog("Enter Book Number to be Deleted:");
            int delbook = Integer.parseInt(bookNumberStr);

            if (book_database_server().containsKey(delbook)) {
                map.remove(delbook);
                textArea.setText("Book No. " + delbook + " deleted successfully...\n");
            } else {
                textArea.setText("This Book No. is not available in the database. Please try with another number...\n");
            }
        });
    }

    public void views(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String bookNumberStr = JOptionPane.showInputDialog("Enter a Book Number to View its Details:");
            int viewbook = Integer.parseInt(bookNumberStr);

            if (book_database_server().containsKey(viewbook)) {
                textArea.setText("Book Number:: " + viewbook + "\nBook Name & Details:: " + map.get(viewbook) + "\n");
            } else {
                textArea.setText("This Book Number is not available in the database...\n");
            }
        });
    }

    public void borrows(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String bookNumberStr = JOptionPane.showInputDialog("Enter Book Number to be Issued:");
            int book = Integer.parseInt(bookNumberStr);

            if (book_database_server().containsKey(book)) {
                issues++;
                textArea.setText("Book No. " + book + " issued successfully...\nTotal Books Issued: " + issues + "\n");
            } else {
                textArea.setText("This Book Number is not available in the database...\n");
            }
        });
    }

    public void returns(JTextArea textArea) {
        SwingUtilities.invokeLater(() -> {
            String bookNumberStr = JOptionPane.showInputDialog("Enter Book Number to be Returned:");
            int book = Integer.parseInt(bookNumberStr);

            if (book_database_server().containsKey(book)) {
                if (issues > 0) {
                    issues--;
                    textArea.setText("Book No. " + book + " returned successfully...\nTotal Books Issued: " + issues + "\n");
                } else {
                    textArea.setText("No Books are currently issued.\n");
                }
            } else {
                textArea.setText("This Book Number is not available in the database...\n");
            }
        });
    }

    public static void main(String[] args) {
        Main obj = new Main();
        obj.homepage();
    }
}
