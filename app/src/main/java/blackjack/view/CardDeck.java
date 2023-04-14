package blackjack.view;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
    //    public final String[] IMAGE_PATHS = {
      //      "ClubAce.png", "DiamondAce.png", "HeartAce.png", "SpadeAce.png"
        //};
        private ClassLoader classLoader = getClass().getClassLoader();  //class loader to load .pnng
        private final String[] suits = {"club", "spade", "diamond", "heart"};  //array of suits so when we call randInt(5) we pick a random suit from here

        private final String[] two_cards = {"2_of_spades.png", "2_of_hearts.png", "2_of_diamonds.png", "2_of_clubs.png"};  //will hold the .png's so we can load them into the hashmap.
        private final String[] three_cards = {"3_of_spades.png", "3_of_hearts.png", "3_of_diamonds.png", "3_of_clubs.png"};
        private final String[] four_cards = {"4_of_spades.png", "4_of_hearts.png", "4_of_diamonds.png", "4_of_clubs.png"};
        private final String[] five_cards = {"5_of_spades.png", "5_of_hearts.png", "5_of_diamonds.png", "5_of_clubs.png"};
        private final String[] six_cards = {"6_of_spades.png", "6_of_hearts.png", "6_of_diamonds.png", "6_of_clubs.png"};
        private final String[] seven_cards = {"7_of_spades.png", "7_of_hearts.png", "7_of_diamonds.png", "7_of_clubs.png"};
        private final String[] eight_cards = {"8_of_spades.png", "8_of_hearts.png", "8_of_diamonds.png", "8_of_clubs.png"};
        private final String[] nine_cards = {"9_of_spades.png", "9_of_hearts.png", "9_of_diamonds.png", "9_of_clubs.png"};
        private final String[] ten_cards = {"10_of_spades.png", "10_of_hearts.png", "10_of_diamonds.png", "10_of_clubs.png", 
            "king_of_spades.png", "king_of_hearts.png", "king_of_diamonds.png", "king_of_clubs.png",
            "queen_of_spades.png", "queen_of_hearts.png", "queen_of_diamonds.png", "queen_of_clubs.png",
            "jack_of_spades.png", "jack_of_hearts.png", "jack_of_diamonds.png", "jack_of_clubs.png",
        };

        private final String[] eleven_cards = {"ace_of_spades.png", "ace_of_hearts.png", "ace_of_diamonds.png", "ace_of_clubs.png"};


    

        private ArrayList<JLabel []> cardDeck = new ArrayList<JLabel []>();

        /*We make arrays of JLabels which we can add to in constructor. */
     
        

        public CardDeck(){
            
           /*  */
      
        }

        public JLabel makeLabel(String pngFile){      
            ImageIcon tempIcon = new ImageIcon(classLoader.getResource(pngFile));
            JLabel returnLabel = new JLabel(tempIcon);
            return returnLabel;
        }


}
