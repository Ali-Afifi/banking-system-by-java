package uni.project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Add implements ActionListener{
    private JFrame frame = new JFrame();
    private JLabel nameLabel = new JLabel();
    private JLabel depositLabel = new JLabel();
    private JTextField nameTextField = new JTextField();
    private JTextField depositTextField = new JTextField();
    private JButton submit = new JButton();

    Add() {
        
        nameLabel.setText("Name"); //set text
        nameLabel.setForeground(Color.BLACK); //set text color
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24)); //set font

        depositLabel.setText("Deposit"); //set text
        depositLabel.setForeground(Color.BLACK); //set text color
        depositLabel.setFont(new Font("Arial", Font.BOLD, 24)); //set font
        
        nameTextField.setPreferredSize(new Dimension(200,30));
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 24));

        depositTextField.setPreferredSize(new Dimension(200,30));
        depositTextField.setFont(new Font("Arial", Font.PLAIN, 24));

        submit.setText("Add");
        submit.addActionListener(this);
        submit.setFocusable(false); 
        submit.setBackground(Color.DARK_GRAY);
        submit.setFont(new Font("Arial", Font.BOLD, 20));
        submit.setForeground(Color.WHITE);       
        
        frame.setTitle("new account form"); //set the name of the window
        frame.setSize(350, 300); //init the size of the window
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setLayout(new FlowLayout()); //set the layout manger
        frame.setResizable(false); //make the window resizable
        frame.setLocationRelativeTo(null); //start the app in the center of the screen
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); //set background color
        
        frame.add(nameLabel);
        frame.add(nameTextField);
        frame.add(depositLabel);
        frame.add(depositTextField);
        frame.add(submit);
        
        frame.setVisible(true); //make the window visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            
            if (nameTextField.getText() == null || nameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "please enter your name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    float num = Math.round(Float.parseFloat(depositTextField.getText())*100) / 100.00f;
    
                    if (num >= 1000000) {
                        VIP vUser = new VIP(nameTextField.getText(), num);
                        
                        new CSV("accounts.csv").write(vUser.readyForCSV());
                    } else {
                        User user = new User(nameTextField.getText(), num);
                        
                        new CSV("accounts.csv").write(user.readyForCSV());
                    }
    
                    new GUI();

                    frame.dispose();
    
                } catch (Exception error) {
                    depositTextField.setText("");
    
                    JOptionPane.showMessageDialog(null, "please enter a number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }    
            }
            
        }
    }
}
