package blackjack.view;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class bjbuttons extends JPanel {
    private JButton []buttons;
    
    public BlackJackButtons(){
        this.buttons = new JButton[4];
        int position = 0;
        String[] actions = {"Double", "Split", "Hit", "Stand"};
        this.setLayout(new GridLayout(1,4));

        for(int i = 0; i < 4; i++){
            this.buttons[position] = new JButton(actions[position]);
            this.add(this.buttons[position]);
            position++;
        }
    }
}