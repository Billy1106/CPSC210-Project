package ui.menus;

import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import model.Person;

public class RemoveMenu extends JFrame implements ActionListener {
    private String[] people;
    private List<Integer> id;
    private JPanel panel;
    private JButton button;
    private JLabel label;
    private JComboBox selectPerson;
    private int newY;
    private int newX;
    private MyNetRunner myNetRunner;
    private ShowAll saw;

    //modifies: this
    //effect: create a window to chose a person to be removed
    //        from MyNet
    public RemoveMenu(int x,int y,MyNetRunner myNetRunner) {
        newX = x;
        newY = y;
        this.myNetRunner = myNetRunner;
        setBounds(newX, newY, 300, 100);
        setVisible(true);
        saw = new ShowAll(newX + 300,newY,myNetRunner.showPeople());
        label = new JLabel();
        id = new ArrayList<>();

        people = getPeople().toArray(new String[getPeople().size()]);

        selectPerson = new JComboBox(people);
        button = new JButton("Remove");
        button.addActionListener(this);
        panel = new JPanel();

        panel.add(selectPerson);
        panel.add(button);
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
    }

    //effect: create a list of people in mynet and return it
    private List<String> getPeople() {
        List<String> people = new ArrayList<>();

        for (Person ps:myNetRunner.showPeople()) {
            id.add(ps.getId());
            people.add(ps.getId() + ": " + ps.getFirstName() + " " + ps.getSecondName());
        }
        return people;
    }


    //modifies: myNetRunner
    //effect: when button is pressed and chose "yes" on pop-up window, remove
    //        the person; otherwise, do nothing
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Remove")) {
            if (selectPerson.getSelectedIndex() != -1) {
                JLabel msg = new JLabel("Are you sure you want to remove this person ?", JLabel.CENTER);
                label.setForeground(Color.RED);
                int n = JOptionPane.showConfirmDialog(this, msg, "Hi", JOptionPane.OK_CANCEL_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    System.out.println(id.get(selectPerson.getSelectedIndex()));
                    myNetRunner.removePerson(id.get(selectPerson.getSelectedIndex()));
                    setVisible(false);
                    saw.setVisible(false);
                }
            }
        }
    }


}