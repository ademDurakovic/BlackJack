package blackjack.view;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

public class ChipButton extends JButton {
    private ImageIcon png;
    private ClassLoader classLoader;

    public ChipButton(String image){
        super();
        this.classLoader = getClass().getClassLoader();
        this.png = new ImageIcon(classLoader.getResource(image));
        resize(.15, this.png);
        this.setIcon(this.png);
        this.setBackground(new Color(255,255,255));
    }

    public void resize(double scale, ImageIcon icon){
        int height = icon.getIconHeight();
        int width = icon.getIconWidth();
        
        height = (int)(height * scale);
        width = (int)(width * scale);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon.setImage(image);
    }
}
