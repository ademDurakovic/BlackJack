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

        private final String[] heartPngs = {"adem", "amar", "jack", "test"};  //will hold the .png's so we can load them into the hashmap.
        private final String[] clubPngs = {"adem", "amar", "jack", "test"};
        private final String[] spadePngs = {"adem", "amar", "jack", "test"};
        private final String[] diamondPngs = {"adem", "amar", "jack", "test"};
    

        private ArrayList<String> hearts = new ArrayList<String>();
        private ArrayList<String> clubs = new ArrayList<String>();
        private ArrayList<String> spades = new ArrayList<String>();
        private ArrayList<String> diamonds = new ArrayList<String>();
        



        /*Suit is the key, ArrayList of .png or we can use ImageIcons is the value.*/
        HashMap<String, ArrayList<String>> cardMap;  

        public CardDeck(){
            /*Adding Png's to the arrayList that will be the value in the hashmap. */
            //WE CAN CHANGE THIS TO BE IMAGEICONS.
            for(int i = 0; i < 13; i++){
                hearts.add(heartPngs[i]);
                clubs.add(clubPngs[i]);
                spades.add(spadePngs[i]);
                diamonds.add(diamondPngs[i]);
            }
            /*Map the suit to an array of image paths. */
            cardMap = new HashMap<>();

            /*Hashmap additions. */
            cardMap.put("heart", hearts);
            cardMap.put("club", clubs);
            cardMap.put("spade", spades);   
            cardMap.put("diamonds", diamonds); 
        }


        /* 
        public JLabel getCard(){
            Random rand = new Random();
            //grab from the suit list at random.
            int randSuit = rand.nextInt(5);  //5 not inclusive.
            int randNum = rand.nextInt(14); //used to get card # in the suit array.
            ArrayList<String> potentialCard = cardMap.get(suits[randSuit]);  //array of .pngs that we can use to make an ImageIcon and JLabl.
            String newCard = potentialCard.get(randNum); //gets the random .png from the deck.

            //if randNum is >= 10 the value is 10.

            //TODO:
            //creation of JLabel to pass over to main GUI so that it can be placed.

        }
        */
  




        
        
}
