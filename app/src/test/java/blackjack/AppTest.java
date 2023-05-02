/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package blackjack;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.*;
import blackjack.model.*;

public class AppTest {

    User user;
    Dealer dealer;
    Table table;
    Gustavo loanShark;
    
    @Before
    public void setUp() {
        user = new User();
        dealer = new Dealer();
        table = new Table(dealer, user);
        loanShark = new Gustavo(user);
    }

    @After
    public void tearDown(){
        user = null;
        dealer = null;
        table = null;
    }

    @Test
    public void userTestHandSize(){
        user.pullRandomCard();
        user.pullRandomCard();

        assertEquals("Should have 2 cards", 2, user.getTotalCards());
    }

    @Test
    public void userTestBust(){
        /*Pulling 11 cards to guarantee bust. */
        user.pullRandomCard();
        user.pullRandomCard();
        user.pullRandomCard();
        user.pullRandomCard();
        user.pullRandomCard();
        user.pullRandomCard();
        user.pullRandomCard();
        user.pullRandomCard();
        user.pullRandomCard();
        user.pullRandomCard();
        user.pullRandomCard();

        assertEquals("User stands after busting", true, user.isStanding());
    }

    @Test 
    public void userLoanTest(){
        user.playerLoaned(100);

        assertEquals("Debt total", 100, user.getDebt());
        assertEquals("balance total" , 5100, user.getBalance());
    }

    @Test
    public void userBetTest() {
        user.increaseBet(1000);

        assertEquals("bet Total", 1000, user.getCurrentBet());
    }

    @Test 
    public void payOutTest() {
        user.increaseBet(1000);
        user.userWon();

        assertEquals("Balance after winning: ", 6000, user.getBalance());
    }

    @Test
    public void payOutLoseTest() {
        user.increaseBet(5000);
        user.userLost();
        
        assertEquals("Balance after losing: ", 0, user.getBalance());
    }

    @Test 
    public void payOutDrawTest() {
        user.increaseBet(5000);
        user.userDraw();

        assertEquals("balance after drawing: ", 5000, user.getBalance());
    }

    @Test 
    public void payOutSequence() {
        user.increaseBet(1000);
        user.userWon();

        assertEquals("Balance after winning: ", 6000, user.getBalance());

        user.increaseBet(6000);
        user.userDraw();

        assertEquals("Balance after drawing: ", 6000, user.getBalance());

        user.increaseBet(3000);
        user.userLost();

        assertEquals("Balance after losing: ", 3000, user.getBalance());
    }

    @Test 
    public void userStandTest() {
        user.stand();

        assertEquals("Player should not be playing: ", false, user.isUserPlaying());
    }

    @Test 
    public void payingLoanSharkTest() {
        user.playerLoaned(1000);
        loanShark.payGustavo(1000);

        assertEquals("Debt should be 0", 0, user.getDebt());
        
    }

    @Test 
    public void overPayingLoanSharkTest() {
        user.playerLoaned(2000);
        loanShark.payGustavo(3000);

        assertEquals("User should still have 5000: ", 5000, user.getBalance());
        assertEquals("Debt should be 0", 0, user.getDebt());
    }
}
