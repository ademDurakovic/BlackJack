package blackjack.controller;

import blackjack.ControllerInterface;
import blackjack.view.*;
import blackjack.model.*;

public class Controller implements ControllerInterface{

    private User model;
    private BlackJackGui view;
    private Dealer dealer;
    
    public Controller(User model, Dealer dealer)
    {
        this.model = model;
        this.dealer = dealer;

        this.view = new BlackJackGui(this, model, dealer);
    }

    // method to hit (draw new card)
    public void userHit()
    {
        this.model.hit();
    }

    // increase bet amount function
    public void userIncreasedBet(int value){
        this.model.increaseBet(value);
    }

    // tells the model the player placed the bet
    public void userPlacedBet(){
        this.model.placeBet();
      //  this.dealer.initalDeal();
    }

    //tells the model the player stood
    public void userStand(){
        //dealer will start to draw cards.
        this.model.stand();
        System.out.println("HERE");
        this.dealer.startDrawing();
        System.out.println("HERE2");
    }
}
