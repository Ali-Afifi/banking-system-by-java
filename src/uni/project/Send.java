package uni.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Send implements ActionListener {

    private CSV csv = new CSV("accounts.csv"); 
    
    private ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> (csv.read());

    private JComboBox comboBox1;
    private JComboBox comboBox2;
    
    private JLabel sIdLabel = new JLabel();
    private JLabel rIdLabel = new JLabel();
    private JLabel sNameLabel = new JLabel();
    private JLabel rNameLabel = new JLabel();
    private JLabel amountLabel = new JLabel();
    
    private JTextField sNameTextField = new JTextField();
    private JTextField rNameTextField = new JTextField();
    private JTextField amountTextField = new JTextField();
    
    private JButton submit = new JButton();

    private JPanel sComboBoxPanel = new JPanel();
    private JPanel rComboBoxPanel = new JPanel();
    private JPanel sNamePanel = new JPanel();
    private JPanel rNamePanel = new JPanel();
    private JPanel amountPanel = new JPanel();
    private JPanel senderPanel = new JPanel();
    private JPanel receiverPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    
    private JFrame frame = new JFrame();

    Send() {
        String[] idArray = new String[listOfLists.size() - 1];

        for (int i = 1; i < listOfLists.size(); i++) {
            idArray[i - 1] = listOfLists.get(i).get(0);
        }
        
        comboBox1 = new JComboBox(idArray);
        comboBox1.addActionListener(this);
        
        comboBox2 = new JComboBox(idArray);
        comboBox2.addActionListener(this);
        
        sIdLabel.setText("Choose the Sender's ID"); //set text
        sIdLabel.setForeground(Color.BLACK); //set text color
        sIdLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font

        rIdLabel.setText("Choose the Receiver's ID"); //set text
        rIdLabel.setForeground(Color.BLACK); //set text color
        rIdLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font

        sNameLabel.setText("Name"); //set text
        sNameLabel.setForeground(Color.BLACK); //set text color
        sNameLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font

        rNameLabel.setText("Name"); //set text
        rNameLabel.setForeground(Color.BLACK); //set text color
        rNameLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font

        amountLabel.setText("amount"); //set text
        amountLabel.setForeground(Color.BLACK); //set text color
        amountLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font
        
        sNameTextField.setPreferredSize(new Dimension(150, 30));
        sNameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        sNameTextField.setEditable(false);

        rNameTextField.setPreferredSize(new Dimension(150, 30));
        rNameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        rNameTextField.setEditable(false);

        amountTextField.setPreferredSize(new Dimension(150, 30));
        amountTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountTextField.setEditable(true);

        submit.setText("Submit");
        submit.addActionListener(this);
        submit.setFocusable(false); 
        submit.setBackground(Color.DARK_GRAY);
        submit.setFont(new Font("Arial", Font.BOLD, 20));
        submit.setForeground(Color.WHITE);        
        
        sComboBoxPanel.setPreferredSize(new Dimension(240, 60));
        sComboBoxPanel.setBackground(Color.LIGHT_GRAY);
        sComboBoxPanel.add(sIdLabel);
        sComboBoxPanel.add(comboBox1);

        rComboBoxPanel.setPreferredSize(new Dimension(240, 60));
        rComboBoxPanel.setBackground(Color.LIGHT_GRAY);
        rComboBoxPanel.add(rIdLabel);
        rComboBoxPanel.add(comboBox2);

        sNamePanel.setPreferredSize(new Dimension(300, 75));
        sNamePanel.setBackground(Color.LIGHT_GRAY);
        sNamePanel.add(sNameLabel);
        sNamePanel.add(sNameTextField);

        rNamePanel.setPreferredSize(new Dimension(300, 75));
        rNamePanel.setBackground(Color.LIGHT_GRAY);
        rNamePanel.add(rNameLabel);
        rNamePanel.add(rNameTextField);

        amountPanel.setPreferredSize(new Dimension(250, 80));
        amountPanel.setBackground(Color.LIGHT_GRAY);
        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);

        senderPanel.setPreferredSize(new Dimension(300, 250));
        senderPanel.setBackground(Color.LIGHT_GRAY);
        senderPanel.add(sComboBoxPanel);
        senderPanel.add(sNamePanel);
        senderPanel.add(amountPanel);

        receiverPanel.setPreferredSize(new Dimension(300, 250));
        receiverPanel.setBackground(Color.LIGHT_GRAY);
        receiverPanel.add(rComboBoxPanel);
        receiverPanel.add(rNamePanel);

        buttonPanel.setPreferredSize(new Dimension(600, 50));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(submit);

        frame.setTitle("make a transaction"); //set the name of the window
        frame.setSize(600, 300); //init the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new BorderLayout()); //set the layout manger
        frame.setResizable(false); //make the window resizable
        frame.setLocationRelativeTo(null); //start the app in the center of the screen
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); //set background color

        frame.add(senderPanel, BorderLayout.WEST);
        frame.add(receiverPanel, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index1 = comboBox1.getSelectedIndex() + 1;
        int index2 = comboBox2.getSelectedIndex() + 1;

        ArrayList<String> row1 = new ArrayList<>(listOfLists.get(index1));
        ArrayList<String> row2 = new ArrayList<>(listOfLists.get(index2));
        
        String[] date1 = row1.get(6).split("-");
        String[] date2 = row2.get(6).split("-");
        
        if (e.getSource() == comboBox1) {
            sNameTextField.setText(row1.get(1));
        }

        if (e.getSource() == comboBox2) {
            rNameTextField.setText(row2.get(1));
        }

        if (e.getSource() == submit) {
            
            if (index1 == index2) {
                JOptionPane.showMessageDialog(null, "You can not make a transaction to yourself", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } else {

                try {
                    float amount = Float.parseFloat(amountTextField.getText());
                    if (amount > Float.parseFloat(row1.get(3))) {
                        amountTextField.setText("");
                        
                        JOptionPane.showMessageDialog(null, "You can't make this transaction", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if ((Float.parseFloat(row1.get(3)) - amount) >= 1000000) {
                            VIP vUser = new VIP(row1.get(1), Float.parseFloat(row1.get(3)) - amount, Integer.parseInt(row1.get(0)));
                            
                            if (Float.parseFloat(row1.get(5)) != 0) {                        
                                vUser.setLastPurchase(Float.parseFloat(row1.get(5)), LocalDate.of(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2])));
                            }
        
                            csv.updateRow(index1, vUser.readyForCSV());
                        } else {
                            User user = new User(row1.get(1), Float.parseFloat(row1.get(3)) - amount, Integer.parseInt(row1.get(0)));
        
                            if (Float.parseFloat(row1.get(5)) != 0) {                        
                                user.setLastPurchase(Float.parseFloat(row1.get(5)), LocalDate.of(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2])));
                            }
        
                            csv.updateRow(index1, user.readyForCSV());
                        }

                        if ((Float.parseFloat(row2.get(3)) + amount) >= 1000000) {
        
                            VIP vUser = new VIP(row2.get(1), Float.parseFloat(row2.get(3)) + amount, Integer.parseInt(row2.get(0)));
                            
                            if (Float.parseFloat(row2.get(5)) != 0) {                        
                                vUser.setLastPurchase(Float.parseFloat(row2.get(5)), LocalDate.of(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2])));
                            }
        
                            csv.updateRow(index2, vUser.readyForCSV());
                        } else {
        
                            User user = new User(row2.get(1), Float.parseFloat(row2.get(3)) + amount, Integer.parseInt(row2.get(0)));
        
                            if (Float.parseFloat(row2.get(5)) != 0) {                        
                                user.setLastPurchase(Float.parseFloat(row2.get(5)), LocalDate.of(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2])));
                            }
        
                            csv.updateRow(index2, user.readyForCSV());
                        }
                        new GUI();
                        frame.dispose();
                    }
                } catch (Exception err) {
                    amountTextField.setText("");
        
                    JOptionPane.showMessageDialog(null, "please enter a number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }

            }
            
            

        }
        
    }

}