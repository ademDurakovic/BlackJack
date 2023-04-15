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

    public User(){
        this.observers = new ArrayList<BlackJackObserver>();
        this.playerHand = new int[0];
        this.currentBalance = 5000;
        this.handIndex = 0;
        this.isPlaying = true;
        this.cardGenerator = new Random();

    }

    public void deal(){
        hit();
        hit();
        notifyObservers();
    }

    public int hit(){
        //generates a random number from 0 to 11
        int cardToGet = cardGenerator.nextInt(12);

        // adds the card to the array but +2 becuase of the cards deck difference
        //this.playerHand[this.handIndex] = (cardToGet + 2);

        //increment the hand index
        this.handIndex++;

        notifyObservers();

        //returns the cards to get to the gui to make the card
        return cardToGet;
    }

    public void doublDown(){
        // gives the user a card
        hit();

        // after doubling the players turn is done
        this.isPlaying = false;
    }

    public int getBalance()
    {
        return this.currentBalance;
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

    public int getHandIndex(){
        return this.handIndex;
    }
}
