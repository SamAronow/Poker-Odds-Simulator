public class Card {
        enum Suit {
            DIAMONDS,
            HEARTS,
            SPADES,
            CLUBS
        }

        enum Value {
            TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING,ACE;
        }

        private Suit theSuit;
        private Value theValue;
        private int numberValue;
        public Card(Suit s, Value v) {
            theSuit = s;
            theValue = v;
            numberValue = theValue.ordinal();
            if (s == Suit.HEARTS){
                numberValue+=13;
            }
            if (s == Suit.SPADES){
                numberValue+=26;
            }
            if (s == Suit.DIAMONDS){
                numberValue+=39;
            }
        }

        public Suit getSuit() {
            return theSuit;
        }

        public Value getValue() {
            return theValue;
        }

        public String toString() {
            return theValue.toString() + " of " + theSuit.toString();
        }

        public int getNumberValue(){
            return numberValue;
        }

        public boolean equals(Card other){
            return other.getSuit()==this.getSuit() && other.getValue()==this.getValue();
        }

        public int compareTo(Card other){
            return this.numberValue - other.numberValue;
        }
    }


