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
    
    public Controller() {
        try{
            loadFromFile();
            start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
        this.gustavo.gustavoMad(this.model);  //checks to see if balance in 3x debt.
    }

    public void userDoubleDown(){
        if(model.doubleDown()){
            this.model.hit();
            this.model.stand();
            this.dealer.startDrawing();
        }
    }

    public void userQuit(){
        this.model.newGame();
        if(!ConfimationText.confirmSaveGame()){
            return;
        }
        try{
            String filePath = GameFileSelector.selectSaveFile();
            System.out.println(filePath);
            if(filePath != null){
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(this.model);

                objectOutputStream.close();
                fileOutputStream.close();
            }else{
                return;
            }
        }
        catch (IOException e){
            //System.out.println(e.getMessage());
        }
    }

    public void loadFromFile() throws IOException, ClassNotFoundException{
        String filePath = GameFileSelector.selectLoadFile();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        this.model = (User)objectInputStream.readObject();
        this.dealer = new Dealer();
        this.table = new Table(this.dealer, this.model);
        this.gustavo = new Gustavo(this.model);

        objectInputStream.close();
        fileInputStream.close();
        System.out.println("Game loaded from file");
    }

}
