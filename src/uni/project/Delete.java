package uni.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class Delete implements ActionListener {
    private CSV csv = new CSV("accounts.csv"); 
    
    ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>> (csv.read());

    private JComboBox comboBox;
    private JLabel idLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JTextField nameTextField = new JTextField();
    private JButton submit = new JButton();
    private JPanel comboBoxPanel = new JPanel();
    private JPanel namePanel = new JPanel();
    private JFrame frame = new JFrame();

    Delete() {
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

        nameTextField.setPreferredSize(new Dimension(150, 30));
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameTextField.setEditable(false);

        submit.setText("Delete");
        submit.addActionListener(this);
        submit.setFocusable(false); 
        submit.setBackground(Color.DARK_GRAY);
        submit.setFont(new Font("Arial", Font.BOLD, 20));
        submit.setForeground(Color.WHITE);

        comboBoxPanel.setPreferredSize(new Dimension(240, 60));
        comboBoxPanel.setBackground(Color.LIGHT_GRAY);
        comboBoxPanel.add(idLabel);
        comboBoxPanel.add(comboBox);

        namePanel.setPreferredSize(new Dimension(300, 75));
        namePanel.setBackground(Color.LIGHT_GRAY);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        namePanel.add(submit);

        frame.setTitle("delete account"); //set the name of the window
        frame.setSize(300, 200); //init the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new FlowLayout()); //set the layout manger
        frame.setResizable(false); //make the window resizable
        frame.setLocationRelativeTo(null); //start the app in the center of the screen
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); //set background color
        
        frame.add(comboBoxPanel);
        frame.add(namePanel);

        frame.setVisible(true);        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = comboBox.getSelectedIndex() + 1;
        String name;

        if (e.getSource() == comboBox) {
            name = listOfLists.get(index).get(1);
            nameTextField.setText(name);
        }
        
        if (e.getSource() == submit) {
            csv.deleteRow(index);

            new GUI();
            frame.dispose();
        }
        
    }
    
}
