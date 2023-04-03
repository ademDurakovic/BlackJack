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

        mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBackground(Color.RED);
        mainPanel.setPreferredSize(new Dimension((int)screen.getWidth(),(int)screen.getHeight()));
        mainFrame.add(mainPanel);

        icon = new ImageIcon("/Users/amarh/csci2300/team_project-group12/app/src/main/resources/blackjack_table.png");
        imageLabel = new JLabel(icon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(imageLabel);

        buttons = new BlackJackButtons();
        buttons.setOpaque(false);
        mainPanel.add(buttons);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}