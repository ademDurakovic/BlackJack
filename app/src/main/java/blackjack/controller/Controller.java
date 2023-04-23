package blackjack.controller;

import blackjack.ControllerInterface;
import blackjack.view.*;
import blackjack.model.*;

public class Controller implements ControllerInterface{

    private User model;
    private BlackJackGui view;
    private Dealer dealer;
    private Table table;
    
    public Controller(User model, Dealer dealer, Table table)
    {
        this.model = model;
        this.dealer = dealer;
        this.table = table;

        this.view = new BlackJackGui(this, model, dealer, table);
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

    public void dealerDraw() {
        this.dealer.startDrawing();
    }

    public void getWinner() {
        this.table.checkWinner();
    }

    public void userDoubleDown(){
        if(model.doubleDown()){
            this.model.hit();
            this.model.stand();
            this.dealer.startDrawing();
        }
    }
}
