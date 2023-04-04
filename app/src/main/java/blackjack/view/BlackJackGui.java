package blackjack.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BlackJackGui {
    JFrame mainFrame;
    JPanel mainPanel;
    ImageIcon icon;
    JLabel imageLabel; 
    private BlackJackButtons buttons;

    public BlackJackGui(){
        mainFrame = new JFrame("BlackJack");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //used to get screen dimensions
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();

        mainFrame.setPreferredSize(new Dimension((int)screen.getWidth(),(int)screen.getHeight()));

        mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBackground(new Color(151,42,39));
        mainFrame.add(mainPanel);

        //image of table
        icon = new ImageIcon("/Users/amarh/csci2300/team_project-group12/app/src/main/resources/blackjack_table.png");
        imageLabel = new JLabel(icon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);

        //creation of second panel
        JPanel secondPanel = new JPanel();
        secondPanel.setOpaque(false);
        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));

        buttons = new BlackJackButtons();
        buttons.setOpaque(false);

        JTextField betAmount = new JTextField("Enter Bet Amount");

        JTextArea balance = new JTextArea("Balance: $1,000,000");
        balance.setOpaque(false);

        secondPanel.add(betAmount);
        secondPanel.add(buttons);
        secondPanel.add(balance);

        mainPanel.add(secondPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}