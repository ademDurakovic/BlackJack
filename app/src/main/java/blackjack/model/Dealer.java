package blackjack.model;

import blackjack.BlackJackObserver;

import java.util.Random;
import java.util.ArrayList;

import blackjack.BlackJackObserver;

public class Dealer {
    private ArrayList<BlackJackObserver> observers;
    private boolean initalDeal = false;
    private Random cardGenerator;
    private ArrayList<Integer> dealerHand;
    private int dealerTotal;
    private boolean delaerIsHitting; // will be used in dealerPanel to se eif hes hitting 

    public Dealer(){
        cardGenerator = new Random();
        dealerHand = new ArrayList<Integer>(); 
        observers = new ArrayList<BlackJackObserver>();
        dealerTotal = 0; 
        delaerIsHitting = false; //we must wait till user clicks stand for dealer to hit.
    }

    /*Should call this when dealer places bet giving the dealer it's inital card. */
    public void initalDeal(){
        notifyObservers();  //notify bet was made. We need to add a blank card and a real one.
        initalDeal = true; //inital has been dealt.
    }


    public void register(BlackJackObserver o){
        this.observers.add(o);
    }

    public int pullRandomCard(){
        int num =  this.cardGenerator.nextInt(10);  //grabs random value, deck takes care of suite.
        dealerHand.add(num+2);       //since there is no 0 card we do a +2 increment
        dealerTotal = this.addDeck();
        if(dealerTotal > 21) {   // we want to see if the user busts so that we can disable buttons. The currentTotal can change if they have an ace so we must check that.
            this.aceCase();
        }
        // make functiom
        return num;
    }

    /*THIS IS CALLED WHEN PLAYER STANDS. */
    public void startDrawing(){
        this.delaerIsHitting = true;
        this.aceCase();
        dealerTotal = this.addDeck();  //making sure we get most recent total of dealer deck.
        while(dealerTotal < 17){

            this.notifyObservers();  
            this.aceCase();  //checks for aces
            dealerTotal = this.addDeck();
        }
        this.delaerIsHitting = false;
    }

    public int addDeck() {
        int total = 0;
        for (int i: this.dealerHand)
        {
            total += i;
        }

        return total;
    }

    public void aceCase(){
        for(int i = 0; i < dealerHand.size(); i++){
            if(dealerHand.get(i) == 11){
                dealerHand.set(i, 1);
                break;
            }
        }
        this.dealerTotal = this.addDeck();
    }

    public void notifyObservers()
    {
        for(BlackJackObserver o: this.observers)
        {
            o.update();
        }
    }

    public boolean getDealerHitting(){
        return delaerIsHitting;
    }
    public boolean isInitalDealt(){
        return initalDeal;
    }

    public void setInitalDealt(boolean setter){
        initalDeal = true;
    }
}
