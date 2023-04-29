package blackjack.model;

import blackjack.model.User;
import java.util.ArrayList;
import blackjack.BlackJackObserver;

public class Gustavo {
    private User player;
    private ArrayList<BlackJackObserver> observers;

    public Gustavo(User player){
        this.player = player;
        this.observers = new ArrayList<BlackJackObserver>();
    }

    public  void payGustavo(int payment){
        if(player.getBalance() > payment){
            player.setBalance(player.getBalance() - payment);  //pays from balance
            player.setDebt(player.getDebt() - payment);
            player.notifyObservers();
        }
        else{
            System.out.println("need more bread.");
        }
        this.notifyObservers();  //tells loan shark GUI updates the debt.

    }

    /* Gustavo finds out you have 3x the amount he loaned you */
    /*Will be called int the newGame() function of user and can be done bc it is static */

    public static void gustavoMad(User player){
        if(player.getBalance() >= player.getDebt() * 3){
            player.setBalance(player.getBalance() - player.getDebt());  //removes debt money from balance.
            player.setDebt(0);  //no more debt
        }
   
    }


    public void borrowMoney(int borrowAmount){
        
        if(player.getDebt() + borrowAmount <= 10000){
            player.setDebt(player.getDebt() + borrowAmount);
            player.setBalance(player.getBalance() + borrowAmount);
            player.notifyObservers();
        }
        else{
            System.out.println("Seems you owe gustavo too much for this type of loan");
        }
        this.notifyObservers();

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
