package blackjack.model;

import java.util.Random;
import java.util.ArrayList;

import blackjack.BlackJackObserver;

public class User{
    private int[] playerHand;
    private int handIndex;
    private boolean isPlaying;
    private Random cardGenerator;
    private int currentBalance;
    private ArrayList<BlackJackObserver> observers;
    private int currentDebt;
    private int currentBet;
    private boolean betsPlaced;

    public User(){
        betsPlaced = false;
        this.observers = new ArrayList<BlackJackObserver>();
        this.playerHand = new int[0];
        this.currentBalance = 5000;
        this.handIndex = 0;
        this.isPlaying = true;
        this.cardGenerator = new Random();
        this.currentDebt = 0;
        this.currentBet = 0;
    }


    public void initialDeal(){
        hit();
        hit();
        betsPlaced = true;
        notifyObservers();
    }

    public void hit(){
        notifyObservers();     
    }

    public int pullRandomCard(){
        return cardGenerator.nextInt(10);
    }

    public void doublDown(){
        // gives the user a card
        hit();

        // after doubling the players turn is done
        this.isPlaying = false;
    }

    public void placeBet(){
        this.initialDeal();
    }

    public void increaseBet(int bet){

        if( bet <= this.currentBalance){  //checks if user can afford bet.
            this.currentBet += bet;
            this.currentBalance -= bet;
        }
        else{
            System.out.println("You cannot afford this bet, visit gustavo.");
        }

        notifyObservers();
    }

    public boolean betPlaced() 
    {
        return this.betsPlaced;
    }
    public int getBalance()
    {
        return this.currentBalance;
    }

    public int getDebt()
    {
        return this.currentDebt;
    }

    public int getCurrentBet() {
        return this.currentBet;
    }

    public int getHandIndex(){
        return this.handIndex;  //returns hand index.
    }

    public void register(BlackJackObserver o)
    {
        this.observers.add(o);
    }

    public void notifyObservers()
    {
        for(BlackJackObserver o: this.observers)
        {
            o.update();
        }
    }


}
