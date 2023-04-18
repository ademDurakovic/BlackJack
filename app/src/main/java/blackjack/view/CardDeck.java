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


        //currnet card PNG dealer and user.

    

        private ArrayList<String []> cardDeck = new ArrayList<String []>();
        Random rand;

        /*We make arrays of JLabels which we can add to in constructor. */
        public CardDeck(){

            /*Adds each array to the arrayList. Each array holds Pngs of the images. */

           
            cardDeck.add(two_cards);  //index 0 
            cardDeck.add(three_cards);
            cardDeck.add(four_cards);
            cardDeck.add(five_cards);
            cardDeck.add(six_cards);
            cardDeck.add(seven_cards);
            cardDeck.add(eight_cards);
            cardDeck.add(nine_cards);
            cardDeck.add(ten_cards);
            cardDeck.add(eleven_cards);  // index 9
            rand = new Random();
            
        }

        public JLabel getFlippedCard(){
            ImageIcon tempIcon = new ImageIcon(classLoader.getResource("FlippedCard.png"));
            resize(0.105, tempIcon);
            JLabel flipped = new JLabel(tempIcon);
            return flipped;
        }

        public JLabel pullCard(int cardValue){
            int realIndex = cardValue;
            String [] potentialCards = cardDeck.get(realIndex);
            String cardPng = potentialCards[rand.nextInt(potentialCards.length)];
            return makeLabel(cardPng);
        }

        public JLabel makeLabel(String pngFile){      
            ImageIcon tempIcon = new ImageIcon(classLoader.getResource(pngFile));
            resize(0.15, tempIcon);
            JLabel returnLabel = new JLabel(tempIcon);
            return returnLabel;
        }

        public void resize(double scale, ImageIcon icon)
        {
           int height = icon.getIconHeight();
           int width = icon.getIconWidth();
           
           height = (int)(height * scale);
           width = (int)(width * scale);
           Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
           icon.setImage(image);
        }


}
