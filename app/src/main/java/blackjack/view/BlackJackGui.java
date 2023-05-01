package blackjack.view;

import blackjack.BlackJackObserver;
import blackjack.ControllerInterface;
import blackjack.model.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.Serializable;

public class BlackJackGui implements ActionListener, BlackJackObserver, Serializable{
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
    
    /*FOR LOAN SHARK? */
    private LoanShark gustavoGUI;

    public BlackJackGui(ControllerInterface controller, User model, Dealer dealer, Table table, LoanShark gustavo){
        this.cardCoordinateX = 610;
        this.cardCoordinateY = 390;
        this.cards = new ArrayList<JLabel>();
        this.controller = controller;
        this.model = model;
        this.dealer = dealer;
        deck = new CardDeck();  //creation of cards.
        this.table = table; // table model, keeps track of winner
        this.model.register(this);
        this.gustavoGUI = gustavo; //instance of gustavo THE CONTORLELR WILL MAKE.
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
        this.debt = new JLabel("Debt: $" + this.model.getDebt());
        debt.setFont(new Font("serif", Font.CENTER_BASELINE, 24));
        debt.setForeground(new Color(139,0,35));
        debt.setOpaque(false);

        this.balance = new JLabel("Balance: $" + this.model.getBalance());
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

        // for saving the game
        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e){
            controller.userQuit();
           } 
        });

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    @Override
    public void update() {
        this.balance.setText("Balance: $" + String.valueOf(model.getBalance()));
        this.debt.setText("Debt: $" + String.valueOf(model.getDebt()));
        this.currentBet.setText("Current Bet: $" + String.valueOf(model.getCurrentBet()));
        this.gustavoGUI.update();  //CHANGES DEBT AMOUNT ON GUI.
        System.out.println("Hand: " + model.getHand());
        layeredPane.repaint();
        dealerPanel.repaint();

        /*Inital dealing to the user currently. */
        if(model.isUserPlaying())
        {
            if(model.betPlaced())
            {
                initialDealing();
                dealerPanel.initialDealer();
                buttons.enableButtonsAfterBets();
                this.chips.disableAll();
                model.setInitialBetPlaced(false);
            }
            else if(model.didUserHit() && model.isUserAbleToHit()){
                JLabel newCard3 = deck.pullCard(model.pullRandomCard());  //makes whole new card.
                newCard3.setBounds(this.cardCoordinateX, this.cardCoordinateY, 200, 200);
                layeredPane.add(newCard3, 0);
                this.cards.add(newCard3);
                this.cardCoordinateX += 30;
                this.cardCoordinateY -= 5;
                model.setBetPlaced(false);
            } 
            /*Here we check to see if the user busted or  */
            if(model.getHand() >= 21) {
                if(model.getTotalCards() == 2 || model.getHand() > 21){
                    controller.userBlackJackorBust();
                }
                else{
                    controller.userStand();
                }
            }
        }else if (model.getStatus() && model.isStanding()){
                System.out.println("in update of new Game.");
                buttons.enableBeforeBetPlaced();
                chips.enableAll();
                mainPanel.revalidate();
                mainPanel.repaint();
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
                case 1: this.controller.userDoubleDown();
                        break;
                case 2: this.controller.userHit();
                        break;
                case 3: this.controller.userStand();
                        break;
                case 4: this.controller.showLoanShark();
                        break;
    
                default: break;
            }
        } else if (source instanceof ChipButton) {
            clearTable();
            ChipButton button = (ChipButton)source;
            this.controller.userIncreasedBet(button.getValue());
        }
    }

    public void clearTable() {
        for (JLabel card: this.cards) {
            layeredPane.remove(card);
        }
        this.cards.clear();
        mainPanel.repaint();
        dealerPanel.removeCards();
    }

    public void initialDealing() {
        this.cardCoordinateX = 610;
        this.cardCoordinateY = 390;

        JLabel newCard = deck.pullCard(model.pullRandomCard());  //makes whole new card.
        newCard.setBounds(550, 400, 200, 200);
        layeredPane.add(newCard, 0);
        this.cards.add(newCard);

        JLabel newCard2 = deck.pullCard(model.pullRandomCard());  //makes whole new card.
        newCard2.setBounds(580, 395, 200, 200);
        layeredPane.add(newCard2, 0);
        this.cards.add(newCard2);

    }

}