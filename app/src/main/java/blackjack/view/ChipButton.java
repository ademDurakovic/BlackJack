package blackjack.view;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

public class ChipButton extends JButton {
    private ImageIcon png;
    private ClassLoader classLoader;
    private int value;

    public ChipButton(String image, int value){
        super();
        this.value = value;
        this.classLoader = getClass().getClassLoader();
        this.png = new ImageIcon(classLoader.getResource(image));
        ImageResizer.resize(.15, this.png);
        this.setIcon(this.png);
        this.setBorderPainted(false);
        this.setBackground(new Color(151,42,39));
        this.setOpaque(true);
    }

    public int getValue() {
        return this.value;
    }

}
