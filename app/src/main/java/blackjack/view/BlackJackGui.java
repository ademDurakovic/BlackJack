package blackjack.view;

import blackjack.BlackJackObserver;
import blackjack.ControllerInterface;
import blackjack.controller.*;
import blackjack.model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BlackJackGui implements ActionListener, BlackJackObserver{
    JFrame mainFrame;
    JPanel mainPanel;
    ImageIcon ticon;
    JLabel imageLabel; 
    private ControllerInterface controller;
    private BlackJackButtons buttons;
    private User model;
    private JLabel debt;
    private JLabel balance;
    

    public BlackJackGui(Controller controller, User model){

        this.controller = controller;
        this.model = model;

        this.model.register(this);
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

        /* creation pf layered pane with table backround*/
        JLayeredPane layeredPane = new JLayeredPane();   
        layeredPane.setPreferredSize(new Dimension(500, 500));
        JLabel backgroundLabel = new JLabel(ticon);
        backgroundLabel.setBounds(200, 0, ticon.getIconWidth(), ticon.getIconHeight());
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        
        mainPanel.add(layeredPane);

        //creation of second panel for buttons/betting
        JPanel secondPanel = new JPanel();
        secondPanel.setOpaque(false);
        secondPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
        secondPanel.setPreferredSize(new Dimension(100,35));

        buttons = new BlackJackButtons(this);
        buttons.setOpaque(false);

        //creates chips
        ChipButtons chips = new ChipButtons(this);
        chips.setOpaque(false);
        secondPanel.add(chips);
        

        //panel to hold current bet and total balance
        JPanel balanceAndBet = new JPanel();
        balanceAndBet.setLayout(new BoxLayout(balanceAndBet, BoxLayout.Y_AXIS));
        balanceAndBet.setAlignmentY(Component.CENTER_ALIGNMENT);
        balanceAndBet.setOpaque(false);


        this.debt = new JLabel("Debt: $0");
        debt.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        debt.setForeground(new Color(139,0,35));
        debt.setOpaque(false);

        this.balance = new JLabel("Balance: $5,000");
        balance.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        balance.setForeground(new Color(202,151,74));
        balance.setOpaque(false);
        balanceAndBet.add(balance);

        JLabel currentBet = new JLabel("Current Bet: $0");
        currentBet.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        currentBet.setForeground(new Color(202,151,74));
        currentBet.setOpaque(false);
        balanceAndBet.add(currentBet);

        balanceAndBet.add(debt);

        secondPanel.add(buttons);
        secondPanel.add(balanceAndBet);

        mainPanel.add(secondPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    @Override
    public void update() {
        this.balance.setText("Balance: $" + String.valueOf(model.getBalance()));
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        Object source = event.getSource();
        if (source instanceof BJButtons){
            BJButtons button = (BJButtons)source;
            System.out.println(button.getIndex());
            if(button.getIndex() == 2)
            {
                this.controller.userHit();
            }
        } else if (source instanceof ChipButton) {
            ChipButton button = (ChipButton)source;
            System.out.println(button.getValue());
        }
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