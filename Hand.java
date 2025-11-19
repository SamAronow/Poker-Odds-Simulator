import java.util.ArrayList;
import java.util.Scanner;

public class Hand {
    public static ArrayList<Card> flop = new ArrayList();
    public static int flopstart;
    public static Deck deck = new Deck();
    public int combo;
    private boolean tie = false;
    private ArrayList<Integer> comboVal = new ArrayList();
    private Card[] pocket;
    private Card[] hand;
    private Scanner kyb = new Scanner(System.in);
    public static Card[] pocketS = new Card[2];

    public Hand(boolean you){
        hand = new Card[7];
        pocket = new Card[2];
        if (you) {
            System.out.println("Enter Your 2 card Hand:");
            pocket[0] = Hand.getValidCard();
            pocket[1] = Hand.getValidCard();
            while (pocket[0].equals(pocket[1])){
                System.out.println("That is the same as the first card, please try again\n");
                pocket[1] = Hand.getValidCard();
            }
            deck.findAndRemove(pocket[0]);
            deck.findAndRemove(pocket[1]);
            System.out.println();
            pocketS = pocket;
        }
        else{
            pocket[0] = Hand.deck.dealCard();
            pocket[1] = Hand.deck.dealCard();
        }
    }

    public static Card getValidCard(){
        Scanner kyb = new Scanner(System.in);
        System.out.print("Enter Card (ex 5h, js):  ");
        String inp = kyb.nextLine();
        try {
            return addCard(inp);
        }
        catch(IllegalArgumentException e){
            System.out.println("Invalid input\n");
            return Hand.getValidCard();
        }
    }

    public static Card addCard(String input){
        Card.Suit suit= Card.Suit.CLUBS;
        Card.Value val = Card.Value.ACE;
        if (input.substring(0,1).equals("a")){
            val = Card.Value.ACE;
        }
        else if (input.substring(0,1).equals("2")){
            val = Card.Value.TWO;
        }
        else if (input.substring(0,1).equals("3")){
            val = Card.Value.THREE;
        }
        else if (input.substring(0,1).equals("4")){
            val = Card.Value.FOUR;
        }
        else if (input.substring(0,1).equals("5")){
            val = Card.Value.FIVE;
        }
        else if (input.substring(0,1).equals("6")){
            val = Card.Value.SIX;
        }
        else if (input.substring(0,1).equals("7")){
            val = Card.Value.SEVEN;
        }
        else if (input.substring(0,1).equals("8")){
            val = Card.Value.EIGHT;
        }
        else if (input.substring(0,1).equals("9")){
            val = Card.Value.NINE;
        }
        else if (input.substring(0,1).equals("1")){
            val = Card.Value.TEN;
        }
        else if (input.substring(0,1).equals("j")){
            val = Card.Value.JACK;
        }
        else if (input.substring(0,1).equals("q")){
            val = Card.Value.QUEEN;
        }
        else if (input.substring(0,1).equals("k")){
            val = Card.Value.KING;
        }
        else{
            throw new IllegalArgumentException();
        }

        if (input.contains("s") ){
            suit = Card.Suit.SPADES;
        }
        else if (input.contains("h")){
            suit = Card.Suit.HEARTS;
        }
        else if (input.contains("c")){
            suit = Card.Suit.CLUBS;
        }
        else if (input.contains("d")){
            suit = Card.Suit.DIAMONDS;
        }
        else {
            throw new IllegalArgumentException();
        }
        return new Card(suit,val);
    }

    public Card[] getPocket(){
        return pocket;
    }

    public static void addFlop(){
        Card toAdd = Hand.getValidCard();
        while (!checkValidFlopEnter(toAdd)){
            System.out.println("Card already used, please try again\n");
            toAdd = Hand.getValidCard();
        }
        Hand.flop.add(toAdd);
    }

    public static boolean checkValidFlopEnter(Card toAdd){
        if(toAdd.equals(pocketS[0]) || toAdd.equals(pocketS[1])){
            return false;
        }
        for (int i=0; i<Hand.flop.size();i++){
            if (toAdd.equals(Hand.flop.get(i))){
                return false;
            }
        }
        return true;
    }

    public static void finishFlop(){
        while (flop.size()!=5){
            flop.add(deck.dealCard());
        }
    }

    public void genCombo() {
        for (int i = 0; i < 7; i++) {
            if (i < 2) {
                hand[i] = (pocket[i]);
            } else {
                hand[i] = flop.get(i - 2);
            }
        }
        if (straightFlush()){
            combo= 8;
            return;
        }
        if (quads()){
            combo= 7;
            return;
        }
        if (fullHouse()){
            combo= 6;
            return;
        }
        if (flush()){
            combo= 5;
            return;
        }
        if (straight()){
            combo= 4;
            return;
        }
        if (triple()){
            combo= 3;
            return;
        }
        if (twoPair()){
            combo= 2;
            return;
        }
        if (pair()){
            combo= 1;
            return;
        }
        highCard();
    }

    public void sort(boolean suit){
        for (int i = 1; i < 7; ++i) {
            Card cur = hand[i];
            int key = hand[i].getNumberValue();
            if (!suit){
                key = hand[i].getValue().ordinal();
            }
            int j = i - 1;
            if (suit) {
                while (j >= 0 && hand[j].getNumberValue() > key) {
                    hand[j + 1] = hand[j];
                    j = j - 1;
                }
            }
            else{
                while (j >= 0 && hand[j].getValue().ordinal() > key) {
                hand[j + 1] = hand[j];
                j = j - 1;
            }
        }
            hand[j + 1] = cur;
        }
    }

    public boolean straightFlush(){
        sort(false);
        ArrayList<Integer> start= new ArrayList<>();
        ArrayList<ArrayList<Card>> tempHolder = new ArrayList();
        ArrayList<Card>[] contains = new ArrayList[14];
        for (int i=0; i<14; i++){
            contains[i] = new ArrayList();
        }
        for (int i=0;i<7; i++){
            if (hand[i].getValue()==Card.Value.TWO){
                contains[1].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.THREE){
                contains[2].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.FOUR){
                contains[3].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.FIVE){
                contains[4].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.SIX){
                contains[5].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.SEVEN){
                contains[6].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.EIGHT){
                contains[7].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.NINE){
                contains[8].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.TEN){
                contains[9].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.JACK){
                contains[10].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.QUEEN){
                contains[11].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.KING){
                contains[12].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.ACE){
                contains[0].add(hand[i]);
                contains[13].add(hand[i]);
            }
        }
        for (int i=9; i>=0;i--){
            if (contains[i].size()>0 && contains[i+1].size()>0 && contains[i+2].size()>0 && contains[i+3].size()>0 && contains[i+4].size()>0){
                start.add(i);
            }
        }
        for (int i=0; i<start.size(); i++){
            ArrayList<Card> temp = new ArrayList();
            for (int z=start.get(i); z<start.get(i)+5; z++) {
                for (int x = 0; x < contains[z].size(); x++) {
                    temp.add(contains[z].get(x));
                }
            }
            tempHolder.add(temp);
        }
        for (int i=0; i<tempHolder.size(); i++){
            ArrayList<Card> temp = tempHolder.get(i);
            int[] suits = new int[4];
            for (int x=0; x<temp.size(); x++){
                if (temp.get(x).getSuit() == Card.Suit.CLUBS){
                    suits[0]++;
                }
                if (temp.get(x).getSuit() == Card.Suit.HEARTS){
                    suits[1]++;
                }
                if (temp.get(x).getSuit() == Card.Suit.SPADES){
                    suits[2]++;
                }
                if (temp.get(x).getSuit() == Card.Suit.DIAMONDS){
                    suits[3]++;
                }
            }
            for (int x=0; x<4; x++){
                if (suits[x]==5){
                    comboVal.add(start.get(i));
                    return true;
                }
            }
        }
        comboVal = new ArrayList();
        return false;
    }

    public boolean quads(){
        sort(false);
        for (int i=0; i<4; i++){
            if (hand[i].getValue()==hand[i+1].getValue() && hand[i].getValue()==hand[i+2].getValue() && hand[i].getValue()==hand[i+3].getValue()){
                comboVal.add( hand[i].getValue().ordinal());
                return true;
            }
        }
        comboVal = new ArrayList();
        return false;
    }

    public boolean fullHouse(){
        sort(false);
        boolean trip=false;
        ArrayList<Card> temp = new ArrayList<>();
        for (int i=0; i<7; i++){
            temp.add(hand[i]);
        }
        for (int i=6; i>=2; i--){
            if (hand[i].getValue()==hand[i-1].getValue() && hand[i].getValue()==hand[i-2].getValue() && temp.size()==7){
                trip=true;
                comboVal.add(hand[i].getValue().ordinal());
                temp.remove(i);
                temp.remove(i-1);
                temp.remove(i-2);
            }
        }
        if (!trip){
            comboVal = new ArrayList();
            return false;
        }
        for (int i=3; i>0; i--){
            if (temp.get(i).getValue()==temp.get(i-1).getValue()){
                return true;
            }
        }
        comboVal = new ArrayList<>();
        return false;
    }

    public boolean flush(){
        sort(true);
        for (int i=6; i>3;i--){
            if (hand[i].getSuit() ==hand[i-1].getSuit() && hand[i].getSuit() ==hand[i-2].getSuit()){
                if (hand[i].getSuit() ==hand[i-3].getSuit() && hand[i].getSuit() ==hand[i-4].getSuit()) {
                    comboVal.add(hand[i].getValue().ordinal());
                    comboVal.add(0,hand[i-1].getValue().ordinal());
                    comboVal.add(0,hand[i-2].getValue().ordinal());
                    comboVal.add(0,hand[i-3].getValue().ordinal());
                    comboVal.add(0,hand[i-4].getValue().ordinal());
                    return true;
                }
            }
        }
        comboVal = new ArrayList();
        return false;
    }

    public boolean straight(){
        sort(false);
        ArrayList<Card>[] contains = new ArrayList[14];
        for (int i=0; i<14; i++){
            contains[i] = new ArrayList();
        }
        for (int i=0;i<7; i++){
            if (hand[i].getValue()==Card.Value.TWO){
                contains[1].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.THREE){
                contains[2].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.FOUR){
                contains[3].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.FIVE){
                contains[4].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.SIX){
                contains[5].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.SEVEN){
                contains[6].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.EIGHT){
                contains[7].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.NINE){
                contains[8].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.TEN){
                contains[9].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.JACK){
                contains[10].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.QUEEN){
                contains[11].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.KING){
                contains[12].add(hand[i]);
            }
            if (hand[i].getValue()==Card.Value.ACE){
                contains[0].add(hand[i]);
                contains[13].add(hand[i]);
            }
        }
        for (int i=9; i>=0;i--){
            if (contains[i].size()>0 && contains[i+1].size()>0 && contains[i+2].size()>0 && contains[i+3].size()>0 && contains[i+4].size()>0){
               comboVal.add(i);
                return true;
            }
        }
            comboVal = new ArrayList();
            return false;
    }

    public boolean triple(){
        sort(false);
        for (int i=6; i>=2; i--){
            if (hand[i].getValue()==hand[i-1].getValue() && hand[i].getValue()==hand[i-2].getValue()){
                comboVal.add(hand[i].getValue().ordinal());
                return true;
            }
        }
        comboVal = new ArrayList();
        return false;
    }

    public boolean twoPair(){
        sort (false);
        int counter=0;
        ArrayList<Card> temp = new ArrayList();
        for (int i=0; i<7; i++){
            temp.add(hand[i]);
        }
        for (int i=6; i>0; i--){
            if (hand[i].getValue()==hand[i-1].getValue()){
                comboVal.add(0,hand[i].getValue().ordinal());
                temp.remove(i);
                temp.remove(i-1);
                i--;
                counter++;
            }
            if (counter==2){
                comboVal.add(0,temp.get(2).getValue().ordinal());
                return true;
            }
        }
        comboVal = new ArrayList();
        return false;
    }

    public boolean pair(){
        sort (false);
        ArrayList<Card> temp = new ArrayList();
        for (int i=0; i<7; i++){
            temp.add(hand[i]);
        }
        for (int i=6; i>0; i--){
            if (hand[i].getValue()==hand[i-1].getValue()){
                comboVal.add(hand[i].getValue().ordinal());
                temp.remove(i);
                temp.remove(i-1);
                for (int x=4; x>=2; x--){
                    comboVal.add(0,temp.get(x).getValue().ordinal());
                }
                return true;
            }
        }
        comboVal = new ArrayList();
        return false;
    }

    public void highCard(){
        combo=0;
        sort(false);
        for (int i=2; i<7; i++){
            comboVal.add(hand[i].getValue().ordinal());
        }
    }

    public boolean compareTo(Hand other){
        if (combo>other.combo){
            return true;
        }
        if (combo<other.combo){
            return false;
        }
        else{
            int lim=0;
            if (comboVal.size()<other.comboVal.size()){
                lim = comboVal.size();
            }
            else{
                lim = other.comboVal.size();
            }
            for (int i=0; i<lim; i++){
                if (comboVal.get(comboVal.size()-1-i)>other.comboVal.get(other.comboVal.size()-1-i)){
                    return true;
                }
                if (comboVal.get(comboVal.size()-1-i)<other.comboVal.get(other.comboVal.size()-1-i)){
                    return false;
                }
            }
        }
        tie = true;
        return false;
    }

    public Card[] getHand(){
        return hand;
    }

    public int getCombo(){
        return combo;
    }

    public boolean getTie(){
        return tie;
    }

    public ArrayList<Integer> getComboVal(){
        return comboVal;
    }
}
