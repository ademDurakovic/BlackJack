package blackjack.view;

import javax.swing.*;

// buttons on lower part of screen
public class BJButtons extends JButton {

    private int index;
    
    public BJButtons(String s, int index) 
    {
        super(s); //inherits from JButton
        this.index = index; // index of button in respect to its position on the JPanel

    }

    public int getIndex()
    {
        return this.index; //returns the buttons index
    }
}
