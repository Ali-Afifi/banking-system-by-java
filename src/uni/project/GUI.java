package uni.project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI implements ActionListener{
    private CSV myCsv = new CSV("accounts.csv");

    private String[][] data = myCsv.dataForTable();
    private String[] columns = myCsv.headersForTable();

    private JFrame frame = new JFrame();
    private JTable table = new JTable(data, columns);
    private JScrollPane sPane = new JScrollPane(table);
    private JPanel bPanel = new JPanel();
    private JPanel tPanel = new JPanel();
    private JButton addButton = new JButton("Add");
    private JButton buyButton = new JButton("Buy");
    private JButton depositButton = new JButton("Deposit");
    private JButton payButton = new JButton("Pay Off");
    private JButton sendButton = new JButton("Send");
    private JButton deleteButton = new JButton("Delete");
    
    GUI() {
        addButton.setFocusable(false); //to get rid of the border around the text
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBackground(Color.DARK_GRAY);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);
        
        buyButton.setFocusable(false);
        buyButton.setFont(new Font("Arial", Font.BOLD, 20));
        buyButton.setBackground(Color.DARK_GRAY);
        buyButton.setForeground(Color.WHITE);
        buyButton.addActionListener(this);

        sendButton.setFocusable(false);
        sendButton.setFont(new Font("Arial", Font.BOLD, 20));
        sendButton.setBackground(Color.DARK_GRAY);
        sendButton.setForeground(Color.WHITE);
        sendButton.addActionListener(this);

        depositButton.setFocusable(false);
        depositButton.setFont(new Font("Arial", Font.BOLD, 20));
        depositButton.setBackground(Color.DARK_GRAY);
        depositButton.setForeground(Color.WHITE);
        depositButton.addActionListener(this);

        payButton.setFocusable(false);
        payButton.setFont(new Font("Arial", Font.BOLD, 20));
        payButton.setBackground(Color.DARK_GRAY);
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(this);

        deleteButton.setFocusable(false);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setBackground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(this);

        bPanel.setLayout(new GridLayout());
        bPanel.setPreferredSize(new Dimension(900, 50));
        bPanel.add(addButton);
        bPanel.add(buyButton);
        bPanel.add(depositButton);
        bPanel.add(payButton);
        bPanel.add(sendButton);
        bPanel.add(deleteButton);

        table.setEnabled(false);

        sPane.setPreferredSize(new Dimension(970, 500));

        tPanel.setLayout(new BorderLayout());
        tPanel.setPreferredSize(new Dimension(1000,500));
        tPanel.add(sPane);

        frame.setTitle("java app");
        frame.setSize(1000,600);
        frame.setLayout(new BorderLayout());
        frame.add(bPanel, BorderLayout.NORTH);
        frame.add(tPanel, BorderLayout.SOUTH);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            new Add();
            frame.dispose();
        }

        if (e.getSource() == buyButton) {
            new Buy();
            frame.dispose();
        }

        if (e.getSource() == depositButton) {
            new Deposit();
            frame.dispose();
        }

        if (e.getSource() == payButton) {
            new Pay();
            frame.dispose();
        }

        if (e.getSource() == sendButton) {
            new Send();
            frame.dispose();
        }

        if (e.getSource() == deleteButton) {
            new Delete();
            frame.dispose();
        }
    }
}
