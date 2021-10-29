package ui.menus;

import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewMenu extends JFrame implements ActionListener {
    private JPanel addPanel;

    private int newX;
    private int newY;
    private MyNetRunner myNetRunner;

    private JLabel label;
    private JButton button;

    //modifies: this
    //EFFECT: Create a window to show options to view people
    public ViewMenu(int x, int y, MyNetRunner myNetRunner) {
        newX = x;
        newY = y;
        this.myNetRunner = myNetRunner;
        setVisible(true);
        setBounds(newX, newY, 600, 500);
        addPanel = new JPanel();
        showAll();
        showSpecificPeople();
        showPerson();
        label = new JLabel();
        Container contentPane = getContentPane();
        contentPane.add(addPanel, BorderLayout.CENTER);
        contentPane.add(label, BorderLayout.SOUTH);
    }

    //modifies:this
    //effect: create a button that shows all the people
    public void showAll() {
        JButton button = new JButton("ShowAll");
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(400, 90));
        addPanel.add(button);
    }
    //modifies:this
    //effect: create a button that shows specific people

    public void showSpecificPeople() {
        JButton button = new JButton("Show Specific People");
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(400, 90));
        addPanel.add(button);
    }

    //modifies:this
    //effect:  create a button that shows a person
    public void showPerson() {
        JButton button = new JButton("Find Person");
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(400, 90));
        addPanel.add(button);
    }


    //effect: open a new window of the corresponding button pressed
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("ShowAll")) {
            new ShowAll(newX + 50, newY + 50, myNetRunner.showPeople());
        } else if (cmd.equals("Show Specific People")) {
            new ShowSpecific(newX + 50, newY + 50, myNetRunner);
        } else if (cmd.equals("Find Person")) {
            new ShowPerson(newX + 50, newY + 50,myNetRunner);
        }
    }
}
