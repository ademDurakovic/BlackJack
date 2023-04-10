package blackjack.view;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class LoanShark {
    public JFrame mainFrame;
    public JPanel mainPanel;

    public LoanShark(){
        mainFrame = new JFrame("LoanShark");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        //mainPanel.setPreferredSize(new Dimension(1000,625));
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);  //top down
        mainPanel.setLayout(layout);
        mainPanel.setBackground(new Color(151,42,39));

        JLayeredPane layeredPane = new JLayeredPane();   
        layeredPane.setPreferredSize(new Dimension(1000, 625));

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon sharkIcon = new ImageIcon(classLoader.getResource("LoanShark.png"));
        resize(0.25, sharkIcon);
        JLabel sharkLabel = new JLabel(sharkIcon);
        sharkLabel.setBounds(387, 290, sharkIcon.getIconWidth(), sharkIcon.getIconHeight());

        ImageIcon officeIcon = new ImageIcon(classLoader.getResource("Office.png"));
        JLabel officeLabel = new JLabel(officeIcon);
        officeLabel.setBounds(0, 0, officeIcon.getIconWidth(), officeIcon.getIconHeight());

        layeredPane.add(officeLabel,1);
        layeredPane.add(sharkLabel,0);
        mainPanel.add(layeredPane);
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);


    }

    public void resize(double scale, ImageIcon icon)
   {
      int height = icon.getIconHeight();
      int width = icon.getIconWidth();
      
      height = (int)(height * scale);
      width = (int)(width * scale);
      Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
      icon.setImage(image);
   }

}
