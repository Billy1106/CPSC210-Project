package ui.menus;

import model.Person;
import ui.MyNetRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ShowPerson extends JFrame implements ActionListener {


    private JPanel panel;
    private List<Integer> id;
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
    //effect: create a window to show a person
    public ShowPerson(int x, int y, MyNetRunner myNetRunner) {
        newX = x;
        newY = y;
        this.myNetRunner = myNetRunner;
        setBounds(newX, newY, 300, 100);
        setVisible(true);
        id = new ArrayList<>();

        label = new JLabel();

        people = getPeople().toArray(new String[getPeople().size()]);
        selectPerson = new JComboBox(people);
        button = new JButton("Show Detail");
        button.addActionListener(this);
        panel = new JPanel();

        panel.add(selectPerson);
        panel.add(button);
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
    }

    //modifies: this
    //effect: return a list of Strings consist of ID and Name of all the person in MyNet
    private List<String> getPeople() {
        List<String> people = new ArrayList<>();

        for (Person ps:myNetRunner.showPeople()) {
            id.add(ps.getId());
            people.add(ps.getId() + ": " + ps.getFirstName() + " " + ps.getSecondName());
        }
        return people;
    }


    //modifies: this
    //effect: if a button is pressed then open a new window to show the detail of a person
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        List<Person> list = new ArrayList<>();
        if (cmd.equals("Show Detail")) {
            if (selectPerson.getSelectedIndex() != -1) {
                int myId = id.get(selectPerson.getSelectedIndex());
                list.add(myNetRunner.findPerson(myId));
                saw = new ShowAll(newX + 50, newY + 50,list);
            }
        }
    }
}
