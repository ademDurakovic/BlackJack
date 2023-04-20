package blackjack.view;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

// poker/gambling chips
public class ChipButton extends JButton {
    private ImageIcon png;
    private ClassLoader classLoader;
    private int value;

    // accepts a string and a value
    public ChipButton(String image, int value){
        super(); // inherits from JButton
        this.value = value;
        this.classLoader = getClass().getClassLoader();
        this.png = new ImageIcon(classLoader.getResource(image));
        ImageResizer.resize(.15, this.png); // resizes the chip
        this.setIcon(this.png);
        this.setBorderPainted(false); // removes background color so that only the chip is the button
        this.setBackground(new Color(151,42,39));
        this.setOpaque(true);
    }

    // returns value of chip
    public int getValue() {
        return this.value;
    }

}
