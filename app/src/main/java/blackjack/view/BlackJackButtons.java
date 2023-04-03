package blackjack.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class BlackJackButtons extends JPanel {
    private JButton []buttons;
    
    public BlackJackButtons(){
        this.buttons = new JButton[4];
        String[] actions = {"Double", "Hit", "Stand","Split"};
        //this.setLayout(new GridLayout(1,4));

        for(int i = 0; i < 4; i++){
            this.buttons[i] = new JButton(actions[i]);
            this.buttons[i].setPreferredSize(new Dimension(200,50));
            this.add(this.buttons[i]);
        }
    }
}