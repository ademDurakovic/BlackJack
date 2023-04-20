package blackjack.model;

import java.util.Random;
import java.util.ArrayList;

import blackjack.BlackJackObserver;

public class User{
    private ArrayList<Integer> playerHand;
    private int handIndex;
    private boolean isPlaying;
    private Random cardGenerator;
    private int currentBalance;
    private ArrayList<BlackJackObserver> observers;
    private int currentDebt;
    private int currentBet;
    private boolean initialBetPlaced;
    private boolean userHit;
    private int currentTotal;
    private boolean isStanding;

    public User(){
        initialBetPlaced = false;
        this.observers = new ArrayList<BlackJackObserver>();
        this.playerHand = new ArrayList<Integer>();
        this.currentBalance = 5000;
        this.handIndex = 0;
        this.isPlaying = true;
        this.cardGenerator = new Random();
        this.currentDebt = 0;
        this.currentBet = 0;
        userHit = false;
        this.isStanding = false;
        currentTotal = 0;
    }


    public void initialDeal(){
        initialBetPlaced = true;
        initialHit();
    }

    public void initialHit(){
        notifyObservers();

    }

    public void hit(){
        userHit = true;
        notifyObservers();     
    }

    public int pullRandomCard(){
        int num =  cardGenerator.nextInt(10);
        playerHand.add(num+2);
        currentTotal = this.addDeck();

        if(currentTotal > 21) {
            this.aceCase();
        }
        didUserBust();
        System.out.println(currentTotal);
        return num;
    }

    public void didUserBust(){
        if (currentTotal >= 21){
            this.stand();

        }
    }
    public void doublDown(){
        // gives the user a card
        hit();

        // after doubling the players turn is done
        this.isPlaying = false;
    }

    public void setBetPlaced(boolean bet)
    {
        this.userHit = bet;
    }

    public void placeBet(){
        if(currentBet > 0){
            this.initialDeal();
        }
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
        return this.initialBetPlaced;
    }

    public boolean isUserAbleToHit(){
        if(this.currentTotal > 21){
            this.aceCase();
        }
        return currentTotal < 21;
    }
    public void aceCase(){
        for(int i = 0; i < playerHand.size(); i++){
            if(playerHand.get(i) == 11){
                playerHand.set(i, 1);
                break;
            }
        }
        this.currentTotal = this.addDeck();
    }

    public void stand(){
        this.isPlaying = false;
        this.isStanding = true;
        notifyObservers();
    }

    public int addDeck() {
        int total = 0;
        for (int i: this.playerHand)
        {
            total += i;
        }

        return total;
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

    public void setInitialBetPlaced(boolean setter){
        this.initialBetPlaced = setter;
    }

    public boolean didUserHit(){
        return this.userHit;
    }

    public boolean isStanding(){
        return isStanding;
    }

    
}
