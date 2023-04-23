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
    public boolean userWon = false;
    public boolean userLost = false;
    public boolean userDrew = false;


    public User(){
        /*Initial Variables. */
        this.initialBetPlaced = false;
        this.observers = new ArrayList<BlackJackObserver>();
        this.playerHand = new ArrayList<Integer>();
        this.currentBalance = 5000;
        this.handIndex = 0;
        this.isPlaying = true;
        this.cardGenerator = new Random();
        this.currentDebt = 0;
        this.currentBet = 0;
        this.userHit = false;
        this.isStanding = false;
        currentTotal = 0;
    }


    public void initialDeal(){
        initialBetPlaced = true;  //bet has been placed, and inital hit takes place.
        initialHit();
    }

    public void initialHit(){
        notifyObservers(); //random cards are pulled at update.

    }

    public void hit(){
        userHit = true;
        notifyObservers();     
    }

    public int pullRandomCard(){
        int num =  cardGenerator.nextInt(10);  //grabs random value, deck takes care of suite.
        playerHand.add(num+2);       //since there is no 0 card we do a +2 increment
        currentTotal = this.addDeck();
        if(currentTotal > 21) {   // we want to see if the user busts so that we can disable buttons. The currentTotal can change if they have an ace so we must check that.
            this.aceCase();
        }
        didUserBust();
        System.out.println(currentTotal);
        return num;
    }
    /*checks score to see if it is 21 or over, if so we stand and the dealer will take control. */
    public void didUserBust(){
        if (currentTotal >= 21){
            this.stand();
        }
    }
    /*COMING IN LATER DELIVERABLE. */
    public void doubleDown(){
        // gives the user a card
        hit();
        // after doubling the players turn is done
        this.isPlaying = false;
    }
    /* sets the bet.*/
    public void setBetPlaced(boolean bet)
    {
        this.userHit = bet;
    }
    /*if the user has made the bet we can deal. */
    public void placeBet(){
        if(currentBet > 0){
            this.initialDeal();
        }
    }
    /*we look to see if the user can afford to increase their bet when they click a chip. */
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

    /*makes sure the user can get more cards. */
    public boolean isUserAbleToHit(){
        if(this.currentTotal > 21){
            this.aceCase();
        }
        return currentTotal < 21;
    }
    /*Checks for aces in deck if they are over 21, if so we change the first 11 value to a 1. */
    public void aceCase(){
        for(int i = 0; i < playerHand.size(); i++){
            if(playerHand.get(i) == 11){
                playerHand.set(i, 1);
                break;
            }
        }
        this.currentTotal = this.addDeck();
    }
    /*user does not get a card and buttons will be disabled in GUI */
    public void stand(){
        this.isPlaying = false;
        this.isStanding = true;
        notifyObservers();
    }

    /*Finds total sum of the users cards */
    public int addDeck() {
        int total = 0;
        for (int i: this.playerHand)
        {
            total += i;
        }

        return total;
    }
    /*Sets inital bet */
    public void setInitialBetPlaced(boolean setter){
        this.initialBetPlaced = setter;
    }

    // betting situations
    public void userWon() {
        this.currentBalance += (currentBet * 2);
        userWon= true;
        System.out.println("userWon");
        notifyObservers();
        newGame();
    }

    public void userLost() {
        userLost= true;
        System.out.println("userLost");
        notifyObservers();
        newGame();
    }

    public void userDraw() {
        this.currentBalance += currentBet;
        userDrew = true;
        notifyObservers();
        
        System.out.println("UserDrew");
        newGame();
    }

    public void newGame() {
        this.currentTotal = 0;
        this.currentBet = 0;
        this.initialBetPlaced = false;
        isStanding = false;
        playerHand.clear();
        System.out.println("newGame");
        this.isPlaying = true;
        notifyObservers();
    }
/*Getters: */

    public int getHand() {
        return this.currentTotal;
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

    public boolean isUserPlaying() {
        return this.isPlaying;
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

    // returns a boolean whether or not the user has clicked "hit"
    public boolean didUserHit(){
        return this.userHit;
    }

    // returns a boolean on whether or not the user chose to stand
    public boolean isStanding(){
        return isStanding;
    }

    
}
