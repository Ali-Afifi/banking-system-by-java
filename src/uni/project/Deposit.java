package uni.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Deposit implements ActionListener {
    private CSV csv = new CSV("accounts.csv"); 
    
    private ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> (csv.read());

    private JComboBox comboBox;
    private JLabel idLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel amountLabel = new JLabel();
    private JTextField nameTextField = new JTextField();
    private JTextField amountTextField = new JTextField();
    private JButton submit = new JButton();
    private JPanel comboBoxPanel = new JPanel();
    private JPanel namePanel = new JPanel();
    private JPanel amountPanel = new JPanel();
    private JFrame frame = new JFrame();

    Deposit() {
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

        amountLabel.setText("amount"); //set text
        amountLabel.setForeground(Color.BLACK); //set text color
        amountLabel.setFont(new Font("Arial", Font.BOLD, 20)); //set font

        nameTextField.setPreferredSize(new Dimension(150, 30));
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameTextField.setEditable(false);

        amountTextField.setPreferredSize(new Dimension(150, 30));
        amountTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountTextField.setEditable(true);

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

        amountPanel.setPreferredSize(new Dimension(250, 80));
        amountPanel.setBackground(Color.LIGHT_GRAY);
        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);
        amountPanel.add(submit);
        
        frame.setTitle("deposit"); //set the name of the window
        frame.setSize(300, 300); //init the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new FlowLayout()); //set the layout manger
        frame.setResizable(false); //make the window resizable
        frame.setLocationRelativeTo(null); //start the app in the center of the screen
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); //set background color
        
        frame.add(comboBoxPanel);
        frame.add(namePanel);
        frame.add(amountPanel);

        frame.setVisible(true);        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = comboBox.getSelectedIndex() + 1;
        ArrayList<String> row = new ArrayList<>(listOfLists.get(index));
        String[] date = row.get(6).split("-");
       
        if (e.getSource() == comboBox) {
            nameTextField.setText(row.get(1));
        }
        
        if (e.getSource() == submit) {
            try {
                float amount = Float.parseFloat(amountTextField.getText());

                if ((Float.parseFloat(row.get(3)) + amount) >= 1000000) {

                    VIP vUser = new VIP(row.get(1), Float.parseFloat(row.get(3)) + amount, Integer.parseInt(row.get(0)));
                    
                    if (Float.parseFloat(row.get(5)) != 0) {                        
                        vUser.setLastPurchase(Float.parseFloat(row.get(5)), LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
                    }

                    csv.updateRow(index, vUser.readyForCSV());
                } else {

                    User user = new User(row.get(1), Float.parseFloat(row.get(3)) + amount, Integer.parseInt(row.get(0)));

                    if (Float.parseFloat(row.get(5)) != 0) {                        
                        user.setLastPurchase(Float.parseFloat(row.get(5)), LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
                    }

                    csv.updateRow(index, user.readyForCSV());
                }

                new GUI();
                frame.dispose();
            } catch (Exception err) {
                amountTextField.setText("");
    
                JOptionPane.showMessageDialog(null, "please enter a number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }

        }
               
    }
    
}
