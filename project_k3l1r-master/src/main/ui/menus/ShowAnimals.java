package ui.menus;




import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


class ShowAnimals extends JFrame implements ActionListener {
    private JPanel panel;
    private int newX;
    private int newY;
    private JLabel label;


    //EFFECT: Showing a picture of puppy;
    //        Create a button to play a sound
    public ShowAnimals(int newX, int newY) {
        this.newX = newX;
        this.newY = newY;
        setVisible(true);
        setBounds(newX, newY, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon1 = new ImageIcon("./data/puppy.jpg");
        label = new JLabel(icon1);
        JButton button = new JButton("Play");
        button.addActionListener(this);
        panel = new JPanel();
        panel.add(label);
        panel.add(button);
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
    }

    //EFFECT: Play a sound
    public void playSound() {
        try {
            File myFile = new File("./data/puppy.wav").getAbsoluteFile();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(myFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }

    //EFFECT: Call playSound() if a button is pressed
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Play")) {
            playSound();
        }
    }
}