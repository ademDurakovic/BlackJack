package blackjack.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class BlackJackButtons extends JPanel {
    private BJButtons []buttons;
    
    public BlackJackButtons(ActionListener buttonClickListener){
        this.buttons = new BJButtons[5];
        String[] actions = {"Bet","Double", "Hit", "Stand","Go To LoanShark"};
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        for(int i = 0; i < 5; i++){
            this.buttons[i] = new BJButtons(actions[i], i);
            this.buttons[i].setPreferredSize(new Dimension(150,50));
            this.buttons[i].setForeground(new Color(202,151,74));
            this.buttons[i].addActionListener(buttonClickListener);

            // the logic behind is that at the start, the user can only place a bet or go to the loan shark
            if(i == 1 || i==2 || i==3 )
            {
                this.buttons[i].setEnabled(false);
            }

            this.add(this.buttons[i]);
        }
    }

    //disables all buttons, used for when the player stands
    public void disableAll()
    {
        for (BJButtons i: this.buttons)
        {
            i.setEnabled(false);
        }
    }


    public void enableAll()
    {
        for (BJButtons i: this.buttons)
        {
            i.setEnabled(true);
        }
    }

    // disables the bet button after bet is placed and enables every other buttons
    public void enableButtonsAfterBets()
    {
        for (BJButtons i: this.buttons)
        {
            i.setEnabled(true);
        }
        this.buttons[0].setEnabled(false);
        this.buttons[4].setEnabled(false);
    }

    public void enableBeforeBetPlaced() {
        for(int i = 0; i < 5; i++){
            if(i == 1 || i==2 || i==3 )
            {
                buttons[i].setEnabled(false);
            }else {
                buttons[i].setEnabled(true);
            }
        }
    }
}