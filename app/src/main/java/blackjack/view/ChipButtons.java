package blackjack.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChipButtons extends JPanel{
    private JButton []chipValues;

    public ChipButtons(){
        this.chipValues = new JButton[5];
        String[] Values = {"$1","$5", "$10", "$20","$50", "$100", "$500", "$1,000"};
        int[] Colors = {0x4a071d, 0xeb1f10, 0xed9209, 0xf7e40a, 0x018508, 0x000000, 0x5e07ba, 0x4a071d};
        this.setLayout(new GridLayout(3, 3));

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon greenChip = new ImageIcon(classLoader.getResource("greenChip.png"));
        ImageIcon orangeChip = new ImageIcon(classLoader.getResource("orangeChip.png"));
        ImageIcon blueChip = new ImageIcon(classLoader.getResource("blueChip.png"));
        ImageIcon redChip = new ImageIcon(classLoader.getResource("redChip.png"));
        ImageIcon purpleChip = new ImageIcon(classLoader.getResource("purpleChip.png"));

        ImageIcon[] chipPngs = {greenChip, orangeChip, blueChip, redChip, purpleChip};
        
        for(int i = 0; i < 5; i++){
            resize(.1, chipPngs[i]);
            this.chipValues[i] = new JButton(chipPngs[i]);
            this.chipValues[i].setPreferredSize(new Dimension(75,45));
            this.add(this.chipValues[i]);
        }
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
