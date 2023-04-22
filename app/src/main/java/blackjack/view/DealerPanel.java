package blackjack.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import blackjack.BlackJackObserver;
import blackjack.ControllerInterface;
import blackjack.model.*;

public class DealerPanel extends JPanel implements BlackJackObserver {
    Dealer dealer;
    private CardDeck deck;
    JLabel flippedCard;

    public DealerPanel(Dealer dealer){
        this.dealer = dealer;
        dealer.register(this);  //Dealer Panel now will get upddates from Dealer class.
        /*We will use a flow layout so cards will just show from left to right. */
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(layout);
        this.deck = new CardDeck();  //we pull cards from here.
        this.setOpaque(false);
        flippedCard = deck.getFlippedCard();
    }

    public void update(){
        
        /*Keep adding cards, the model will take care of whenevrt the dealer goes over 17. */
        if(dealer.getDealerHitting()){
            this.remove(flippedCard);
            JLabel newCard = deck.pullCard(dealer.pullRandomCard());  //makes whole new card.
            this.add(newCard);
            this.revalidate(); // validate the layout to update the display
            this.repaint(); // repaint the panel to ensure that the new card is displayed
        }
        /*triggered when user clicks bet.  */
        else if (dealer.isInitalDealt() == false){
            System.out.println("HERE5");
            this.add(flippedCard);
            JLabel firstCard = deck.pullCard(dealer.pullRandomCard());
            this.add(firstCard);
            this.revalidate(); // validate the layout to update the display
            this.repaint(); // repaint the panel to ensure that the new card is displayed
        }
    }
    
}

