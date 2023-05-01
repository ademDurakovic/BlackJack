package blackjack.model;

import blackjack.BlackJackObserver;

import java.util.ArrayList;

public class Table {

    private Dealer dealer;
    private User user;
    private boolean draw;
    private boolean userWon;


    public Table(Dealer dealer, User user) {
        this.dealer = dealer;
        this.user = user;
      
    }
    
    // checks the betting situations mentioned in the requirements doc
    public void checkWinner() {
        if (user.getHand() > 21) {
            user.userLost();
        } else if (dealer.getHand() > 21 ) {         
            user.userWon();
            
        } else {
            if (user.getHand() > dealer.getHand()){             
                user.userWon();
            } else if (user.getHand() == dealer.getHand()) {           
                user.userDraw();
            } else if (dealer.getHand() > user.getHand()) {
                user.userLost();
            }
        }
        dealer.reset();
        
    }
}