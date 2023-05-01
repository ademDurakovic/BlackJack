package blackjack.view;

import javax.swing.JOptionPane;

public class ConfimationText {

    public static boolean confirmSaveGame(){
        int input = JOptionPane.showConfirmDialog(null, "Do you want to save this session", "Select Yes or No", JOptionPane.YES_NO_OPTION);
        return interpretInput(input);
    }

    public static boolean confirmLoadGame(){
        int input = JOptionPane.showConfirmDialog(null, "Do you want to continue a previous session?", "Select Yes or No", JOptionPane.YES_NO_OPTION);
        return interpretInput(input);
    }

    public static boolean interpretInput(int input){
        if(input == 0){
            return true;
        }
        return false;
    }
}
