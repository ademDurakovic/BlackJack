package blackjack.view;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;

public class ChipButtons extends JPanel{
    private JButton []chips;

    public ChipButtons(){
        this.chips = new ChipButton[5];
        this.setLayout(new GridLayout(3, 3));

        String[] chipPngs = {"greenChip.png", "orangeChip.png", "blueChip.png", "redChip.png", "purpleChip.png"};
        
        for(int i = 0; i < 5; i++){
            this.chips[i] = new ChipButton(chipPngs[i]);
            this.chips[i].setPreferredSize(new Dimension(60,60));
            this.add(chips[i]);
        }
    }
}
