package blackjack.model;

import java.util.ArrayList;

public class Gustavo {
    private User player;

    public Gustavo(User player){
        this.player = player;
    }

    public void payGustavo(int payment){
        if (payment < 0 ) { return; }
        if(player.getBalance() >= payment){
            //this conditional makes sure the player can only pay up to the debt amount, not over
            if (payment > player.getDebt()) {
                payment = player.getDebt();
            }
            player.setBalance(player.getBalance() - payment);  //pays from balance
            player.setDebt(player.getDebt() - payment);
        }
        else{
            System.out.println("need more bread.");
        }

    }

    /* Gustavo finds out you have 3x the amount he loaned you */
    /*Will be called int the newGame() function of user and can be done bc it is static */

    public void gustavoMad(User player){
        if(player.getBalance() >= player.getDebt() * 3){
            player.setBalance(player.getBalance() - player.getDebt());  //removes debt money from balance.
            player.setDebt(0);  //no more debt
        }
   
    }


    public void borrowMoney(int borrowAmount){
        if (borrowAmount < 0) { return; }
        if(player.getDebt() + borrowAmount <= 10000){
            player.setDebt(player.getDebt() + borrowAmount);
            player.setBalance(player.getBalance() + borrowAmount);
            player.notifyObservers();
        }
        else{
            System.out.println("Seems you owe gustavo too much for this type of loan");
        }
    } 
    
}
