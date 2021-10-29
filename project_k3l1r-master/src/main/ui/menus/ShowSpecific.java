package ui.menus;

import model.Person;
import ui.MyNetRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowSpecific extends JFrame implements ActionListener {


    private JPanel panel;
    private JLabel label;
    private JComboBox selectPerson;
    private int newY;
    private int newX;
    private String[] people;
    private JButton button;
    private MyNetRunner myNetRunner;
    private ShowAll saw;
    private JTextField field;




    //modifies: this
    //effect: create a window to show options of filtering criteria
    public ShowSpecific(int x, int y, MyNetRunner myNetRunner) {
        newX = x;
        newY = y;
        this.myNetRunner = myNetRunner;
        setBounds(newX, newY, 300, 200);
        setVisible(true);

        label = new JLabel();

        String[] options = {"Age", "Occupation", "Relationship"};
        selectPerson = new JComboBox(options);
        button = new JButton("Select");
        button.addActionListener(this);
        panel = new JPanel();
        field = new JTextField(20);
        panel.add(selectPerson);
        panel.add(field);
        panel.add(button);
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
    }


    //modifies: this
    //effect: when a button is pressed, show a list of people that
    //        matches the corresponding criteria
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        List<Person> list;
        if (cmd.equals("Select")) {
            if (selectPerson.getSelectedItem().equals("Age")) {
                try {
                    list = myNetRunner.viewByAge(Integer.parseInt(field.getText()));
                    saw = new ShowAll(newX + 50, newY + 50, list);
                } catch (Exception ek) {
                    //pass
                }
            } else if (selectPerson.getSelectedItem().equals("Occupation")) {
                list = myNetRunner.viewByOccupation(field.getText());
                saw = new ShowAll(newX + 50, newY + 50, list);
            } else if (selectPerson.getSelectedItem().equals("Relationship")) {
                list = myNetRunner.viewByRelationship(field.getText());
                saw = new ShowAll(newX + 50, newY + 50,list);
            }
        }
    }
}
