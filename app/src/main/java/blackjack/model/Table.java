package blackjack.model;

import blackjack.BlackJackObserver;
import blackjack.model.Dealer;
import java.util.ArrayList;

public class Table {

    private ArrayList<BlackJackObserver> observers;
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
        System.out.println("Got heere");
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
        
    }




}