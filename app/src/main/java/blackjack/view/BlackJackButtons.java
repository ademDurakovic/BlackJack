package blackjack.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class BlackJackButtons extends JPanel {
    private JButton []buttons;
    
    public BlackJackButtons(){
        this.buttons = new JButton[5];
        String[] actions = {"Bet","Double", "Hit", "Stand","Split"};
        this.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));

        for(int i = 0; i < 5; i++){
            this.buttons[i] = new JButton(actions[i]);
            this.buttons[i].setPreferredSize(new Dimension(150,50));
            this.add(this.buttons[i]);
        }
    }
}