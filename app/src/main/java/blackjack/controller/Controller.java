package blackjack.controller;

import java.io.*;

import blackjack.ControllerInterface;
import blackjack.view.*;
import blackjack.model.*;

public class Controller implements ControllerInterface{

    private User model;
    private BlackJackGui view;
    private Dealer dealer;
    private Table table;
    private LoanShark gustavoGUI;
    private Gustavo gustavo;
    
    public Controller(User model, Dealer dealer, Table table, Gustavo gustavo)
    {
        this.model = model;
        this.dealer = dealer;
        this.table = table;
        this.gustavo = gustavo;
        start();
    }

    public void start(){
        this.gustavoGUI = new LoanShark(this, model, null);  //GUSTAVO NULL RIGHT NOW
        this.view = new BlackJackGui(this, this.model, this.dealer, this.table, this.gustavoGUI);
    }

    // loan shark functionalities
    public void showLoanShark() {
        this.gustavoGUI.showShark();
    }

    public void playerRequest(int num){
        this.gustavo.borrowMoney(num);
    }

    public void payShark(int num) {
        this.gustavo.payGustavo(num);
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
        this.dealer.startDrawing();
    }

    public void userBlackJackorBust(){
        model.stand();
        dealer.flipCard();
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

    public void userQuit(){
        if(!ConfimationText.confirmSaveGame()){
            return;
        }
        try{
            String filePath = GameFileSelector.selectSaveFile();
            System.out.println(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.model.getBalance());
            objectOutputStream.writeObject(this.model.getDebt());
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
