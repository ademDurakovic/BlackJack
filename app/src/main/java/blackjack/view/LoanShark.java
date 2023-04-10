package blackjack.view;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class LoanShark {
    public JFrame mainFrame;
    public JPanel mainPanel;

    LoanShark(){
        mainFrame = new JFrame("LoanShark");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1000,1000));
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);  //top down
        mainPanel.setLayout(layout);
        mainPanel.setBackground(new Color(151,42,39));

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon sharkIcon = new ImageIcon(classLoader.getResource("loanshark.png"));
        JLabel sharkLabel = new JLabel(sharkIcon);

        mainPanel.add(sharkLabel);

        










    }

}
