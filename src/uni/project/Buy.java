package uni.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class Buy implements ActionListener {
    private CSV csv = new CSV("accounts.csv"); 
    
    private ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> (csv.read());

    private JComboBox comboBox;
    private JLabel idLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel priceLabel = new JLabel();
    private JTextField nameTextField = new JTextField();
    private JTextField priceTextField = new JTextField();
    private JButton submit = new JButton();
    private JPanel comboBoxPanel = new JPanel();
    private JPanel namePanel = new JPanel();
    private JPanel pricePanel = new JPanel();
    private JFrame frame = new JFrame();

    Buy() {
        String[] idArray = new String[listOfLists.size() - 1];

        for (int i = 1; i < listOfLists.size(); i++) {
            idArray[i - 1] = listOfLists.get(i).get(0);
        }

        comboBox = new JComboBox(idArray);
        
        comboBox.addActionListener(this);
        
        idLabel.setText("Please Choose your ID"); //set text
        idLabel.setForeground(Color.BLACK); //set text color
        idLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font

        nameLabel.setText("Name"); //set text
        nameLabel.setForeground(Color.BLACK); //set text color
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font

        priceLabel.setText("Price"); //set text
        priceLabel.setForeground(Color.BLACK); //set text color
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font

        nameTextField.setPreferredSize(new Dimension(150, 30));
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameTextField.setEditable(false);

        priceTextField.setPreferredSize(new Dimension(150, 30));
        priceTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        priceTextField.setEditable(true);

        submit.setText("Submit");
        submit.addActionListener(this);
        submit.setFocusable(false); 
        submit.setBackground(Color.DARK_GRAY);
        submit.setFont(new Font("Arial", Font.BOLD, 20));
        submit.setForeground(Color.WHITE);

        comboBoxPanel.setPreferredSize(new Dimension(240, 60));
        comboBoxPanel.setBackground(Color.LIGHT_GRAY);
        comboBoxPanel.add(idLabel);
        comboBoxPanel.add(comboBox);

        namePanel.setPreferredSize(new Dimension(300, 55));
        namePanel.setBackground(Color.LIGHT_GRAY);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        pricePanel.setPreferredSize(new Dimension(250, 80));
        pricePanel.setBackground(Color.LIGHT_GRAY);
        pricePanel.add(priceLabel);
        pricePanel.add(priceTextField);
        pricePanel.add(submit);
        
        frame.setTitle("make a purchase"); //set the name of the window
        frame.setSize(300, 300); //init the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new FlowLayout()); //set the layout manger
        frame.setResizable(false); //make the window resizable
        frame.setLocationRelativeTo(null); //start the app in the center of the screen
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); //set background color
        
        frame.add(comboBoxPanel);
        frame.add(namePanel);
        frame.add(pricePanel);

        frame.setVisible(true);        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = comboBox.getSelectedIndex() + 1;
        int id;
        String name;
        float balance;
        float limit = 0;

        if (e.getSource() == comboBox) {
            name = listOfLists.get(index).get(1);
            nameTextField.setText(name);
        }
        
        if (e.getSource() == submit) {
            id = Integer.parseInt(listOfLists.get(index).get(0));
            name = listOfLists.get(index).get(1);
            balance = Float.parseFloat(listOfLists.get(index).get(3));

            
            try {
                float purchase = Float.parseFloat(priceTextField.getText());
                
                if (listOfLists.get(index).get(2).equals("Personal")) {
                    limit = balance * 2;
                } else {
                    limit = balance * 3;
                }
                
                
                if (Float.parseFloat(listOfLists.get(index).get(5)) == 0) {
                    
                    if (purchase > limit) {
                        JOptionPane.showMessageDialog(null, "sorry this purchase exceeds the spending limit: " + limit, "invalid Input", JOptionPane.ERROR_MESSAGE);
                    } else {
                        
                        if (purchase > balance) {
                            JOptionPane.showMessageDialog(null, "This purchase will put you in debt", "warning message", JOptionPane.WARNING_MESSAGE);
                        } 
        
                        if (balance >= 1000000) {
                            VIP vUser = new VIP(name, balance, id);
                            vUser.setLastPurchase(purchase);
                            csv.updateRow(index, vUser.readyForCSV());
                        } else {
                            User user = new User(name, balance, id);
                            user.setLastPurchase(purchase);
                            csv.updateRow(index, user.readyForCSV());
                        }
        
                        new GUI();
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You must pay for the last transaction before you can perform any other transaction", "invalid Input", JOptionPane.ERROR_MESSAGE);

                    new GUI();
                    frame.dispose();
                }
            } catch (Exception err) {
                priceTextField.setText("");
                
                JOptionPane.showMessageDialog(null, "please enter a number", "Invalid Input", JOptionPane.ERROR_MESSAGE);

            }

        }
        
    }
    
}
