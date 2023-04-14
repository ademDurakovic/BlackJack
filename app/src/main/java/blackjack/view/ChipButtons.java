package blackjack.view;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;

public class ChipButtons extends JPanel{
    private ChipButton []chips;

    public ChipButtons(ActionListener buttonClickListener){
        this.chips = new ChipButton[5];
        this.setLayout(new GridLayout(2, 4));

        String[] chipPngs = {"greenChip.png", "orangeChip.png", "blueChip.png", "redChip.png", "purpleChip.png"};
        int[] chipValues = {1,25,50,100,500};
        
        for(int i = 0; i < 5; i++){
            this.chips[i] = new ChipButton(chipPngs[i],chipValues[i]);
            this.chips[i].setPreferredSize(new Dimension(60,60));
            this.chips[i].addActionListener(buttonClickListener);
            this.add(chips[i]);
        }
    }
}
