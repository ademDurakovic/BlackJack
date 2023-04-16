package blackjack.controller;

import blackjack.ControllerInterface;
import blackjack.view.*;
import blackjack.model.*;

public class Controller implements ControllerInterface{

    private User model;
    private BlackJackGui view;
    
    public Controller(User model)
    {
        this.model = model;

        this.view = new BlackJackGui(this, model);
    }

    public void userHit()
    {
        this.model.hit();
    }

    public void userIncreasedBet(int value){
        this.model.increaseBet(value);
    }

    public void userPlacedBet(){
        this.model.placeBet();
    }

    public void userStand(){
        this.model.stand();
    }
}
