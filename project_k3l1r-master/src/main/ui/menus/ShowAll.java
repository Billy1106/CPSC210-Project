package ui.menus;

import model.Person;
import ui.MyNetRunner;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ShowAll extends JFrame {

    private JPanel showPanel;

    private JLabel id;
    private JLabel firstName;
    private JLabel secondName;
    private JLabel age;
    private JLabel occupation;
    private String  relationship;
    private JLabel label;


    //modifies: this
    //effect:create a window to show all the people in MyNet
    public ShowAll(int x, int y, List<Person> list) {
        setVisible(true);
        setBounds(x, y, 300, 400);
        showPanel = new JPanel();
        showAllPeople(list);
        Container contentPane = getContentPane();
        JScrollPane scrollpane = new JScrollPane(showPanel);
        contentPane.add(scrollpane, BorderLayout.CENTER);
    }

    //modifies: this
    //effect:show all the people in list
    private void showAllPeople(List<Person> list) {

        for (Person ps:list) {
            JPanel panel = new JPanel();
            id = new JLabel(String.format("%-15s", "ID: ")  + ps.getId());
            firstName = new JLabel(String.format("%-15s", "First Name: ") + ps.getFirstName());
            secondName = new JLabel(String.format("%-15s", "Second Name: ") + ps.getSecondName());
            age = new JLabel(String.format("%-15s", "Age: ") + ps.getAge());
            occupation = new JLabel(String.format("%-15s", "Occupation: ") + ps.getOccupation());
            relationship = ps.getRelation();

            Font font = new Font("Courier",Font.PLAIN,20);
            id.setFont(font);
            firstName.setFont(font);
            secondName.setFont(font);
            age.setFont(font);
            occupation.setFont(font);

            createProfile(panel);

        }
    }

    //modifies: this
    //effect: Create a profile of a person
    private void createProfile(JPanel panel) {
        panel.add(id,BorderLayout.CENTER);
        panel.add(firstName,BorderLayout.CENTER);
        panel.add(secondName,BorderLayout.CENTER);
        panel.add(age,BorderLayout.CENTER);
        panel.add(occupation,BorderLayout.CENTER);
        panel.setBorder(new TitledBorder(relationship));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        showPanel.add(panel,BorderLayout.WEST);
        showPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

}
