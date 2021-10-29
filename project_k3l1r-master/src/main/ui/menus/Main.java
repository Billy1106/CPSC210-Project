package ui.menus;


import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;


public class Main extends JFrame implements ActionListener,WindowListener {

    private JPanel panel;
    private final int initialX = 100;
    private final int initialY = 100;
    private MyNetRunner myNetRunner = new MyNetRunner();


    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    //MODIFIES: this
    //EFFECT: Create main menu for MyNetApp
    public Main() {
        setBounds(initialX, initialY, 600, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        addWindowListener(new WindowClosing());
        addWindowListener(this);
        panel = new JPanel();
        creatAddButton();
        creatDeleteButton();
        creatViewButton();
        creatSaveButton();
        creatLoadButton();
        secretFunction();
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
    }

    //EFFECT: Create Add button
    public void creatAddButton() {
        JButton button1 = new JButton("Add");
        button1.setPreferredSize(new Dimension(400, 90));
        button1.addActionListener(this);
        panel.add(button1);
    }

    //EFFECT: Create Delete button
    public void creatDeleteButton() {
        JButton button1 = new JButton("Delete");
        button1.setPreferredSize(new Dimension(400, 90));
        button1.addActionListener(this);
        panel.add(button1);
    }

    //EFFECT: Create View button

    public void creatViewButton() {
        JButton button1 = new JButton("View");
        button1.setPreferredSize(new Dimension(400, 90));
        button1.addActionListener(this);
        panel.add(button1);
    }

    //EFFECT: Create Save button
    public void creatSaveButton() {
        JButton button1 = new JButton("Save");
        button1.setPreferredSize(new Dimension(400, 90));
        button1.addActionListener(this);
        panel.add(button1);
    }

    //EFFECT: Create Load button
    public void creatLoadButton() {
        JButton button1 = new JButton("Load");
        button1.setPreferredSize(new Dimension(400, 90));
        button1.addActionListener(this);
        panel.add(button1);
    }

    //EFFECT: Create Secret Function button
    public void secretFunction() {
        JButton button = new JButton("Do you want to be healed? Click here !!!");
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(400, 20));
        panel.add(button,BorderLayout.CENTER);
    }

    //MODIFIES:myNetrunner
    //EFFECT: if mouse pressed a button, open a new window of the corresponding button pressed
    //        or save/load the data
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Add")) {
            new AddMenu(initialX + 50, initialY + 50,myNetRunner);
        } else if (cmd.equals("Delete")) {
            new RemoveMenu(initialX + 50,initialY + 50,myNetRunner);
        } else if (cmd.equals("View")) {
            new ViewMenu(initialX + 50, initialY + 50,myNetRunner);
        } else if (cmd.equals("Save")) {
            saveFile();
        } else if (cmd.equals("Load")) {
            loadFile();
        } else if (cmd.equals("Do you want to be healed? Click here !!!")) {
            new ShowAnimals(initialX + 50, initialY + 50);
        }
    }

    public void loadFile() {
        try {
            myNetRunner.loadMynNet();
        } catch (FileNotFoundException ex) {

            JLabel msg = new JLabel("Sorry, file not found :(");
            JOptionPane.showMessageDialog(this, msg);
        }
    }

    public void saveFile() {
        try {
            myNetRunner.saveMyNet();
        } catch (FileNotFoundException ed) {
            JLabel msg = new JLabel(ed.getMessage());
            JOptionPane.showMessageDialog(this, msg);
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {
        int ans = JOptionPane.showConfirmDialog(Main.this, "Load data?");
        if (ans == JOptionPane.YES_OPTION) {
            loadFile();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int ans = JOptionPane.showConfirmDialog(Main.this, "Save data?");
        if (ans == JOptionPane.YES_OPTION) {
            saveFile();
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}