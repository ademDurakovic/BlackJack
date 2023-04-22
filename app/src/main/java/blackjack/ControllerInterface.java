package blackjack;

public interface ControllerInterface {
    public void userHit();
    public void userIncreasedBet(int value);
    public void userPlacedBet();
    public void userStand();
    public void getWinner();
}