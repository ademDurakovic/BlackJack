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
        mainPanel.setPreferredSize(new Dimension(1440,900));
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBackground(new Color(151,42,39));
        mainFrame.add(mainPanel);
      
      
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon ticon = new ImageIcon(classLoader.getResource("blackjack_table.png"));
        ImageIcon king = new ImageIcon(classLoader.getResource("king.png"));
        ImageIcon back = new ImageIcon(classLoader.getResource("FlippedCard.png"));

        /* creation pf layered pane with table backround*/
        JLayeredPane layeredPane = new JLayeredPane();   
        layeredPane.setPreferredSize(new Dimension(500, 500));
        JLabel backgroundLabel = new JLabel(ticon);
        backgroundLabel.setBounds(200, 0, ticon.getIconWidth(), ticon.getIconHeight());
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        /*Following is process to create a card and manually add to scene. */
        resize(.125, king);
        JLabel kingLabel = new JLabel(king);
        kingLabel.setBounds(700, 460, king.getIconWidth(), king.getIconHeight());  //can use x and y to give exact cords.
        layeredPane.add(kingLabel, Integer.valueOf(1));

        JLabel kingLabel2 = new JLabel(king);
        kingLabel2.setBounds(670, 480, king.getIconWidth(), king.getIconHeight());
        layeredPane.add(kingLabel2, Integer.valueOf(1));

        JLabel kingLabel3 = new JLabel(king);
        kingLabel3.setBounds(620, 143, king.getIconWidth(), king.getIconHeight());
        layeredPane.add(kingLabel3, Integer.valueOf(1));

        resize(.09, back);
        JLabel backCard = new JLabel(back);
        backCard.setBounds(700, 132, back.getIconWidth(), back.getIconHeight());
        layeredPane.add(backCard, Integer.valueOf(1));
        mainPanel.add(layeredPane);

        //creation of second panel for buttons/betting
        JPanel secondPanel = new JPanel();
        secondPanel.setOpaque(false);
        secondPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
        secondPanel.setPreferredSize(new Dimension(100,35));

        buttons = new BlackJackButtons();
        buttons.setOpaque(false);

        //creates chips
        ChipButtons chips = new ChipButtons();
        chips.setOpaque(false);
        secondPanel.add(chips);
        

        //panel to hold current bet and total balance
        JPanel balanceAndBet = new JPanel();
        balanceAndBet.setLayout(new BoxLayout(balanceAndBet, BoxLayout.Y_AXIS));
        balanceAndBet.setAlignmentY(Component.CENTER_ALIGNMENT);
        balanceAndBet.setOpaque(false);


        JLabel debt = new JLabel("Debt: $5,000");
        debt.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        debt.setForeground(new Color(139,0,35));
        debt.setOpaque(false);

        JLabel balance = new JLabel("Balance: $1,000,000");
        balance.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        balance.setForeground(new Color(202,151,74));
        balance.setOpaque(false);
        balanceAndBet.add(balance);

        JLabel currentBet = new JLabel("Current Bet: $25,000");
        currentBet.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        currentBet.setForeground(new Color(202,151,74));
        currentBet.setOpaque(false);
        balanceAndBet.add(currentBet);

        balanceAndBet.add(debt);

        JButton loaButton = new JButton("Need more $$$?");
        loaButton.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        balanceAndBet.add(loaButton);

        secondPanel.add(buttons);
        secondPanel.add(balanceAndBet);

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