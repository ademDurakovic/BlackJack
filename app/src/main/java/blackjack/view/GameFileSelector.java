package blackjack.view;

import javax.swing.JFileChooser;

public class GameFileSelector {
    
    public static String selectLoadFile(){
        return selectFile(false);
    }

    public static String selectSaveFile(){
        return selectFile(true);
    }

    private static String selectFile(boolean toSave){
        String filePath = null;
        JFileChooser fileChooser = new JFileChooser(".");

        int result = 0;
        if (toSave)
        {
            result = fileChooser.showSaveDialog(null);
        }
        else
        {
            result = fileChooser.showOpenDialog(null);
        }

        if (result == JFileChooser.APPROVE_OPTION)
        {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
        return filePath;
    } 
}



