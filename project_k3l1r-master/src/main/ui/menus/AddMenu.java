package ui.menus;

import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMenu extends JFrame implements ActionListener {
    private JPanel addPanel;

    private int newX;
    private int newY;
    private MyNetRunner myNetRunner;
    private JTextField firstName;
    private JTextField secondName;
    private JTextField age;
    private JTextField occupation;
    private JTextField relationship;
    private JLabel label;
    private JButton button;

    //MODIFIES: this
    //EFFECT: Create a button and window to add new person;
    public AddMenu(int x, int y,MyNetRunner myNetRunner) {
        this.myNetRunner = myNetRunner;
        newX = x;
        newY = y;
        setVisible(true);
        setBounds(newX, newY, 250, 350);

        addPanel = new JPanel();

        setFirstName();
        setSecondName();
        setAge();
        setOccupation();
        setRelationship();

        button = new JButton("Get");
        button.addActionListener(this);

        addPanel.add(button);

        label = new JLabel();
        Container contentPane = getContentPane();
        contentPane.add(addPanel, BorderLayout.CENTER);
        contentPane.add(label, BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECT: Create field and button for First Name
    private void setFirstName() {
        firstName = new JTextField(20);
        firstName.setHorizontalAlignment(JTextField.CENTER);
        label = new JLabel("First name:");
        addPanel.add(label);
        addPanel.add(firstName);
    }

    //MODIFIES: this
    //EFFECT: Create field and button for Second Name
    private void setSecondName() {
        secondName = new JTextField(20);
        secondName.setHorizontalAlignment(JTextField.CENTER);
        label = new JLabel("Second name:");
        addPanel.add(label);
        addPanel.add(secondName);
    }

    //MODIFIES: this
    //EFFECT: Create field and button for Age
    private void setAge() {
        age = new JTextField(20);
        age.setHorizontalAlignment(JTextField.CENTER);
        label = new JLabel("Age:");
        addPanel.add(label);
        addPanel.add(age);
    }

    //MODIFIES: this
    //EFFECT: Create field and button for Occupation

    private void setOccupation() {
        occupation = new JTextField(20);
        occupation.setHorizontalAlignment(JTextField.CENTER);
        label = new JLabel("Occupation:");
        addPanel.add(label);
        addPanel.add(occupation);
    }

    //MODIFIES: this
    //EFFECT: Create field and button for Relationship
    private void setRelationship() {
        relationship = new JTextField(20);
        relationship.setHorizontalAlignment(JTextField.CENTER);
        label = new JLabel("Relationship:");
        addPanel.add(label);
        addPanel.add(relationship);
    }

    //MODIFIES: myNetRunner
    //EFFECT: Add a new person when the button is pressed
    public void actionPerformed(ActionEvent e) {
        String fname = firstName.getText();
        String sname = secondName.getText();
        String rel = relationship.getText();
        String occ = occupation.getText();
        int ageInt = 0;
        try {
            ageInt = Integer.parseInt(age.getText());
            myNetRunner.addPerson(fname,sname,rel,occ,ageInt);
            setVisible(false);
        } catch (Exception ex) {
            JLabel msg = new JLabel("Age must be integer !!!");
            JOptionPane.showMessageDialog(this, msg);
        }
    }

}
