package blackjack.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.BoxLayout;
import java.awt.event.*;
import javax.swing.*;

public class ChipButtons extends JPanel{
    private JButton []chipValues;

    public ChipButtons(){
        this.chipValues = new JButton[8];
        String[] Values = {"$1","$5", "$10", "$20","$25", "$100", "$500", "$1,000"};
        int[] Colors = {0xffffff, 0xeb1f10, 0xed9209, 0xf7e40a, 0x018508, 0x000000, 0x5e07ba, 0x4a071d};
        
        for(int i = 0; i < 8; i++){
            this.chipValues[i] = new JButton(Values[i]);
            this.chipValues[i].setPreferredSize(new Dimension(150,50));
            this.chipValues[i].setForeground(new Color(Colors[i]));
            this.add(this.chipValues[i]);
        }
        
    }
}
