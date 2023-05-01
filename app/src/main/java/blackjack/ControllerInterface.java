package blackjack;

public interface ControllerInterface {
    public void userHit();
    public void userIncreasedBet(int value);
    public void userPlacedBet();
    public void userStand();
    public void getWinner();
    public void dealerDraw();
    public void userDoubleDown();      
    public void userBlackJackorBust();
    
    //loan shark controller functionalities
    public void showLoanShark();
    public void playerRequest(int num);
    public void payShark(int num);

    // for loading and saving
    public void userQuit();
 }