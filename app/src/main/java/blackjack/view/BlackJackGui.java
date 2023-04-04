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
    private ChipButtons chips;

    public BlackJackGui(){
        mainFrame = new JFrame("BlackJack");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //used to get screen dimensions
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();

        System.out.println("Width: "+ screen.getWidth());
        System.out.println("height: "+ screen.getHeight());

        //mainFrame.setPreferredSize(new Dimension(1440,700));

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1400,700));
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBackground(new Color(151,42,39));
        mainFrame.add(mainPanel);

        //image of table
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon icon = new ImageIcon(classLoader.getResource("blackjack_table.png"));
        imageLabel = new JLabel(icon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);

        //creation of second panel
        JPanel secondPanel = new JPanel();
        secondPanel.setOpaque(false);

        secondPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));

        buttons = new BlackJackButtons();
        buttons.setOpaque(false);

        //trial for chips panel
        // JPanel chipsPanel = new JPanel();
        // chipsPanel.setOpaque(false);

        // secondPanel.setLayout(new BoxLayout(chipsPanel, BoxLayout.Y_AXIS));

        // chips = new ChipButtons();
        // chips.setOpaque(false);

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
        // secondPanel.add(chipsPanel);
        secondPanel.add(balance);

        mainPanel.add(secondPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}