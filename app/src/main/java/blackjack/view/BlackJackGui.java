package blackjack.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BlackJackGui {
    JFrame mainFrame;
    JPanel mainPanel;
    ImageIcon icon;
    JLabel imageLabel; 
    private bjbuttons buttons;

    public BlackJackGui(){
        JFrame mainFrame = new JFrame("BlackJack");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();

        icon = new ImageIcon("blackjack_table.png");
        imageLabel = new JLabel(icon);
        mainPanel.add(imageLabel);

        buttons = new bjbuttons();
        buttons.setOpaque(false);
        mainPanel.add(buttons);

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    public static void main(String []args){
        bjtest test = new bjtest();
    }
}