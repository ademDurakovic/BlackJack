package blackjack.view;

import blackjack.BlackJackObserver;
import blackjack.ControllerInterface;
import blackjack.model.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class BlackJackGui implements ActionListener, BlackJackObserver{
    JFrame mainFrame;
    JPanel mainPanel;
    ImageIcon ticon;
    JLabel imageLabel; 
    private int cardCoordinateX;
    private int cardCoordinateY;
    private ControllerInterface controller;
    private BlackJackButtons buttons;
    private User model;
    private JLabel debt;
    private JLabel balance;
    private JLabel currentBet;
    private CardDeck deck;
    private JLayeredPane layeredPane;
    private ChipButtons chips;
    private Dealer dealer; //dealer model class
    private Table table;
    private DealerPanel dealerPanel;
    private ArrayList<JLabel> cards;

    public BlackJackGui(ControllerInterface controller, User model, Dealer dealer, Table table){
        this.cardCoordinateX = 610;
        this.cardCoordinateY = 390;
        this.cards = new ArrayList<JLabel>();
        this.controller = controller;
        this.model = model;
        this.dealer = dealer;
        deck = new CardDeck();  //creation of cards.
        this.table = table; // table model, keeps track of winner
        this.model.register(this);
        mainFrame = new JFrame("BlackJack");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //used to get screen dimensions (WILL USE LATER ON.)
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1440,900));
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.setBackground(new Color(151,42,39));
        mainFrame.add(mainPanel);
      
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon ticon = new ImageIcon(classLoader.getResource("blackjack_table.png"));  //blackJack table

        /* creation pf layered pane with table backround*/
        layeredPane = new JLayeredPane();   
        layeredPane.setPreferredSize(new Dimension(500, 500));
        JLabel backgroundLabel = new JLabel(ticon);
        backgroundLabel.setBounds(200, 0, ticon.getIconWidth(), ticon.getIconHeight());
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        
        mainPanel.add(layeredPane);

        dealerPanel = new DealerPanel(dealer, table, this.controller);
        dealerPanel.setBounds(cardCoordinateX - 150, 85, 600, 300);
        layeredPane.add(dealerPanel, 0);

        //creation of second panel for buttons/betting
        JPanel secondPanel = new JPanel();
        secondPanel.setOpaque(false);
        secondPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
        secondPanel.setPreferredSize(new Dimension(100,35));

        buttons = new BlackJackButtons(this);
        buttons.setOpaque(false);

        //creates chips
        this.chips = new ChipButtons(this);
        chips.setOpaque(false);
        secondPanel.add(chips);
        

        //panel to hold current bet and total balance
        JPanel balanceAndBet = new JPanel();
        balanceAndBet.setLayout(new BoxLayout(balanceAndBet, BoxLayout.Y_AXIS));
        balanceAndBet.setAlignmentY(Component.CENTER_ALIGNMENT);
        balanceAndBet.setOpaque(false);

        /*Debt, Balance, and current bet to be displayed to the user: */
        this.debt = new JLabel("Debt: $0");
        debt.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        debt.setForeground(new Color(139,0,35));
        debt.setOpaque(false);

        this.balance = new JLabel("Balance: $5,000");
        balance.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        balance.setForeground(new Color(202,151,74));
        balance.setOpaque(false);
        balanceAndBet.add(balance);

        this.currentBet = new JLabel("Current Bet: $0");
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
        this.debt.setText("Debt: $" + String.valueOf(model.getDebt()));
        this.currentBet.setText("Current Bet: $" + String.valueOf(model.getCurrentBet()));
        System.out.println("Hand: " + model.getHand());

        /*Inital dealing to the user currently. */
        if(model.isUserPlaying())
        {
            if(model.betPlaced())
            {
                buttons.enableButtonsAfterBets();
                this.chips.disableAll();

                JLabel newCard = deck.pullCard(model.pullRandomCard());  //makes whole new card.
                newCard.setBounds(550, 400, 200, 200);
                layeredPane.add(newCard, 0);
                this.cards.add(newCard);

                JLabel newCard2 = deck.pullCard(model.pullRandomCard());  //makes whole new card.
                newCard2.setBounds(580, 395, 200, 200);
                layeredPane.add(newCard2, 0);
                this.cards.add(newCard2);

                this.dealerPanel.update();
                model.setInitialBetPlaced(false);
            }else if(model.didUserHit() && model.isUserAbleToHit()){
                JLabel newCard3 = deck.pullCard(model.pullRandomCard());  //makes whole new card.
                newCard3.setBounds(this.cardCoordinateX, this.cardCoordinateY, 200, 200);
                layeredPane.add(newCard3, 0);
                this.cards.add(newCard3);
                this.cardCoordinateX += 30;
                this.cardCoordinateY -= 5;
                model.setBetPlaced(false);
            }else if(model.isStanding()){
                buttons.disableAll();
            }
            if (model.getHand() >= 21) {
                dealer.startDrawing();
            }
        } else {
            if(model.userLost || model.userDrew || model.userWon){

                for (JLabel card: this.cards) {
                    layeredPane.remove(card);
                }
                this.cards.clear();
                this.cardCoordinateX = 610;
                this.cardCoordinateY = 390;
                System.out.println("HERE5");
                buttons.enableBeforeBetPlaced();
                chips.enableAll();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        Object source = event.getSource();
        if (source instanceof BJButtons){
            BJButtons button = (BJButtons)source;
            int buttonIndex = button.getIndex();

            /*Switch case based on which button the user clicked: */
            switch(buttonIndex){
                case 0: this.controller.userPlacedBet();
                        break;
                case 2: this.controller.userHit();
                        break;
                case 3: this.controller.userStand();
                        break;
    
                default: break;
            }
        } else if (source instanceof ChipButton) {
            ChipButton button = (ChipButton)source;
            this.controller.userIncreasedBet(button.getValue());
        }
    }

}