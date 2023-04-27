package blackjack.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

import blackjack.BlackJackObserver;
import blackjack.ControllerInterface;
import blackjack.model.*;

public class DealerPanel extends JLayeredPane implements BlackJackObserver {
    private Dealer dealer;
    private CardDeck deck;
    private Table table;
    private JLabel flippedCard;
    private ControllerInterface controller;
    private int xCord = 100;
    private int yCord = 15;
    private ArrayList<JLabel> cards;


    public DealerPanel(Dealer dealer, Table table, ControllerInterface controller){
        super();
        this.cards = new ArrayList<JLabel>();
        this.dealer = dealer;
        this.table = table;
        this.controller = controller;
        dealer.register(this);  //Dealer Panel now will get updates from Dealer class.
        /*We will use a flow layout so cards will just show from left to right. */
        this.setPreferredSize(new Dimension(300, 400));

        this.deck = new CardDeck();  //we pull cards from here.
        this.setOpaque(false);
        flippedCard = deck.getFlippedCard();
        flippedCard.setBounds(xCord, yCord, 200, 200);
    }

    public void removeCards(){
        for (JLabel card : this.cards){
            this.remove(card);
        }
        this.cards.clear();
        xCord = 100;
        yCord = 15;
        this.revalidate(); // validate the layout to update the display
        this.repaint();

    }

    public void update(){
        
        /*Keep adding cards, the model will take care of whenever the dealer goes over 17. */
        if(dealer.getDealerHitting() || dealer.getUserBJCase() == true){
            /*Incrementing cards. */
            xCord += 10;
            yCord += 10;
            this.remove(flippedCard);
            JLabel newCard = deck.pullCard(dealer.pullRandomCard());  //makes whole new card.
            newCard.setBounds(xCord , yCord, 200, 200);
            this.add(newCard, 0);
            this.cards.add(newCard);
            this.revalidate(); // validate the layout to update the display
            this.repaint(); // repaint the panel to ensure that the new card is displayed 
        }
        /*triggered when user clicks bet.  */
        else if (dealer.isInitalDealt() == false){
            initialDealer();
        }
        
        else if (dealer.isDone()) {
            System.out.println("getting winner!");
            controller.getWinner();
            xCord = 100;
            yCord = 15;
        }
    }

    public void initialDealer() {
        this.add(flippedCard, 0);
        xCord += 10;
        yCord += 10;
        JLabel firstCard = deck.pullCard(dealer.pullRandomCard());
        firstCard.setBounds(xCord, yCord, 200, 200);
        this.add(firstCard, 0);
        this.cards.add(firstCard);
        dealer.setInitalDealt(true);
        this.revalidate(); // validate the layout to update the display
        this.repaint(); // repaint the panel to ensure that the new card is displayed
    }
    
}

