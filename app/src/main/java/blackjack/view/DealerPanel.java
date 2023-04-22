package blackjack.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import blackjack.BlackJackObserver;
import blackjack.ControllerInterface;
import blackjack.model.*;

public class DealerPanel extends JLayeredPane implements BlackJackObserver {
    private Dealer dealer;
    private CardDeck deck;
    private JLabel flippedCard;
    private int xCord = 100;
    private int yCord = 15;


    public DealerPanel(Dealer dealer){
        this.dealer = dealer;
        dealer.register(this);  //Dealer Panel now will get upddates from Dealer class.
        /*We will use a flow layout so cards will just show from left to right. */
        this.setPreferredSize(new Dimension(300, 400));

        this.deck = new CardDeck();  //we pull cards from here.
        this.setOpaque(false);
        flippedCard = deck.getFlippedCard();
        flippedCard.setBounds(xCord, yCord, 200, 200);
    }

    public void update(){
        
        /*Keep adding cards, the model will take care of whenevrt the dealer goes over 17. */
        if(dealer.getDealerHitting()){
            /*Incrementing cords. */
            xCord += 25;
            yCord += 15;
            this.remove(flippedCard);
            JLabel newCard = deck.pullCard(dealer.pullRandomCard());  //makes whole new card.
            newCard.setBounds(xCord , yCord, 200, 200);
            this.add(newCard, 0);
            this.revalidate(); // validate the layout to update the display
            this.repaint(); // repaint the panel to ensure that the new card is displayed
        }
        /*triggered when user clicks bet.  */
        else if (dealer.isInitalDealt() == false){
            this.add(flippedCard, 0);
            xCord += 25;
            yCord += 15;
            JLabel firstCard = deck.pullCard(dealer.pullRandomCard());
            firstCard.setBounds(xCord, yCord, 200, 200);
            this.add(firstCard, 0);
            dealer.setInitalDealt(true);
            this.revalidate(); // validate the layout to update the display
            this.repaint(); // repaint the panel to ensure that the new card is displayed
        }
    }
    
}

