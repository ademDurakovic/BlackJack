package blackjack.view;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;

//panel for chips
public class ChipButtons extends JPanel{
    private ChipButton []chips;

    public ChipButtons(ActionListener buttonClickListener){
        this.chips = new ChipButton[5];
        this.setLayout(new GridLayout(2, 4));

        String[] chipPngs = {"greenChip.png", "orangeChip.png", "blueChip.png", "redChip.png", "purpleChip.png"};
        int[] chipValues = {1,25,50,100,500};
        
        for(int i = 0; i < 5; i++){
            this.chips[i] = new ChipButton(chipPngs[i],chipValues[i]); //instantiates the chip (chip image, chip value)
            this.chips[i].setPreferredSize(new Dimension(60,60)); // swets size
            this.chips[i].addActionListener(buttonClickListener); //adds an action listener
            this.add(chips[i]);
        }
    }

    // disables all chips, will be used after user places bets
    public void disableAll()
    {
        for(ChipButton chip: this.chips)
        {
            chip.setEnabled(false);
        }
    }

    // enables all chips, when new round or game is played
    public void enableAll()
    {
        for(ChipButton chip: this.chips)
        {
            chip.setEnabled(true);
        }
    }
}
