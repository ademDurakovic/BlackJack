package blackjack.view;

import javax.swing.*;

public class BJButtons extends JButton {

    private int index;
    
    public BJButtons(String s, int index) 
    {
        super(s);
        this.index = index;

    }

    public int getIndex()
    {
        return this.index;
    }
}
