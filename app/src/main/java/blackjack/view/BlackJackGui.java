package blackjack.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BlackJackGui {
    JFrame mainFrame;
    JPanel mainPanel;
    ImageIcon ticon;
    JLabel imageLabel; 
    private BlackJackButtons buttons;
    

    public BlackJackGui(){
        mainFrame = new JFrame("BlackJack");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //used to get screen dimensions
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();

        System.out.println("Width: "+ screen.getWidth());
        System.out.println("height: "+ screen.getHeight());

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1400,750));
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBackground(new Color(151,42,39));
        mainFrame.add(mainPanel);
      
      
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon ticon = new ImageIcon(classLoader.getResource("blackjack_table.png"));
        ImageIcon king = new ImageIcon(classLoader.getResource("king.png"));
        ImageIcon back = new ImageIcon(classLoader.getResource("FlippedCard.png"));

        imageLabel = new JLabel(ticon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridLayout gridLayout = new GridLayout(2, 2);
        imageLabel.setLayout(gridLayout);

        gridLayout.setHgap(-850);
        gridLayout.setVgap(-200);
        mainPanel.add(imageLabel);

        resize(.145, king);
        resize(.1, back);
        JLabel imageLabelCard = new JLabel(back);
        imageLabelCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.add(imageLabelCard);


        JLabel imageLabelCard2 = new JLabel(king);
        imageLabelCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.add(imageLabelCard2);

        JLabel imageLabelCard3 = new JLabel(king);
        imageLabelCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.add(imageLabelCard3);

        JLabel imageLabelCard4 = new JLabel(king);
        imageLabelCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.add(imageLabelCard4);


        //creation of second panel
        JPanel secondPanel = new JPanel();
        secondPanel.setOpaque(false);

        secondPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));

        buttons = new BlackJackButtons();
        buttons.setOpaque(false);

        JPanel betPanel = new JPanel();
        betPanel.setOpaque(false);
        betPanel.setLayout(new BoxLayout(betPanel, BoxLayout.Y_AXIS));
        secondPanel.add(betPanel);

        JLabel betArea = new JLabel("Enter Bet Amount:");
        betArea.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        betArea.setForeground(new Color(202,151,74));
        betArea.setOpaque(false);

        JTextField betAmount = new JTextField();
        betAmount.setHorizontalAlignment(JTextField.CENTER);
        betAmount.setText("$1000");
        betAmount.setEnabled(false);

        JLabel balance = new JLabel("Balance: $1,000,000");
        balance.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        balance.setForeground(new Color(202,151,74));
        balance.setOpaque(false);

        betPanel.add(betArea);
        betPanel.add(betAmount);

        secondPanel.add(buttons);
        secondPanel.add(balance);

        mainPanel.add(secondPanel);
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