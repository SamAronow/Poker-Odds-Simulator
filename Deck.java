import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Collections;
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();

    public Deck(){
        for(Card.Value v : Card.Value.values()) {
            for(Card.Suit s : Card.Suit.values()) {
                deck.add(new Card(s,v));
            }
        }
        Collections.shuffle(deck);
    }

    public void findAndRemove(Card rCard){
        int pos =0;
        for (int i=0; i<deck.size(); i++){
            if (deck.get(i).getSuit()==rCard.getSuit() && deck.get(i).getValue()==rCard.getValue()){
                deck.remove(i);
            }
        }
    }

    public Card dealCard() {
        return deck.remove(0);
    }

    public void removeCard(int numCard){
        for (int i=0; i<numCard; i++){
            deck.remove(0);
        }
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }
}

