import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static Scanner kyb = new Scanner(System.in);
    public static boolean firstFlopRun=true;
     public static void main(String[] args){
         int wins=0;
         boolean win;
       int players = getPlayers();
       Hand.flopstart=0;
       System.out.println("");
       Hand[] hands = new Hand[players];
       hands[0] = new Hand(true);
       getFlop();
       //for (int x=0; x<5; x++) {
         System.out.print("loading");
           for (int t = 0; t < 1000000; t++) {
               if (t%100000==0){
                   System.out.print(".");
               }
               Hand.deck = new Deck();
               for (int i = 0; i < 2; i++) {
                   Hand.deck.findAndRemove(hands[0].getPocket()[i]);
               }
               while (Hand.flop.size() > Hand.flopstart) {
                   Hand.flop.remove(Hand.flop.size() - 1);
               }
               for (int i = 0; i < Hand.flop.size(); i++) {
                   Hand.deck.findAndRemove(Hand.flop.get(i));
               }
               for (int i = 1; i < players; i++) {
                   hands[i] = new Hand(false);
               }
               Hand.finishFlop();

               for (int i = 0; i < players; i++) {
                   hands[i].genCombo();
               }
               win = true;
               for (int i = 1; i < players; i++) {
                   if (!hands[0].compareTo(hands[i])) {
                       win = false;
                   }
               }
               if (win) {
                   wins++;
               }

               hands[0].genCombo();
               if (hands[0].flush()){
                   wins++;
               }
           }
           System.out.println("\n You have a " + (wins / 10000.0)+"% chance of winning");
           //wins=0;
       //}
       /*
         for (int t = 0; t<5; t++) {
             System.out.println("\n");
             System.out.println(t);
             System.out.println("\n");
             Hand.deck = new Deck();
             for (int i=0; i<2; i++) {
                 Hand.deck.findAndRemove(hands[0].getPocket()[i]);
             }
             while (Hand.flop.size()>Hand.flopstart){
                 Hand.flop.remove(Hand.flop.size()-1);
             }
             for (int i=0; i<Hand.flop.size(); i++){
                 Hand.deck.findAndRemove(Hand.flop.get(i));
             }
             Hand.finishFlop();
                 System.out.println(Hand.flop);
             System.out.println("");
             for (int i=1; i<players; i++){
                 hands[i] = new Hand(false);
             }
             for (int i=0; i<players; i++){
                 hands[i].genCombo();
             }
             for (int i=0; i<players; i++){
                 for (int x=0; x<7; x++) {
                     System.out.print(hands[i].getHand()[x]+", ");
                 }
                 System.out.print(hands[i].getCombo());
                 System.out.println("     "+hands[i].getComboVal());
                 System.out.println();
             }
             win=true;
             for (int i = 1; i < players; i++) {
                 if (!hands[0].compareTo(hands[i])){
                     win=false;
                 }
             }
             if (win){
                 wins++;
             }
             System.out.println(win);
         }
         System.out.println("Wins: "+(wins/5.0));
*/
         /*
         for (int i=1; i<players; i++){
             hands[i] = new Hand(false);
         }
         Hand.finishFlop();
         for (int i=0; i<players; i++){
             hands[i].genCombo();
         }
       for (int i=0; i<players; i++){
           for (int x=0; x<7; x++){
               System.out.println(hands[i].getHand()[x]);
           }
           System.out.println(hands[i].getCombo());
           System.out.println();
       }
       for (int i=1; i<players; i++) {
           System.out.println(hands[0].compareTo(hands[i]));
       }
       */
    }

    public static void getFlop(){
         System.out.print("How many cards are flopped:  ");
         String inp = kyb.nextLine();
         int size=-1;
         try{
             size = Integer.parseInt(inp);
         }
         catch (NumberFormatException e){
             System.out.println("Not valid enter");
             getFlop();
             firstFlopRun=false;
         }
        if (firstFlopRun) {
            Hand.flopstart = size;
            System.out.println("");
            for (int i = 0; i < size; i++) {
                Hand.addFlop();
            }
        }
    }

    public static int getPlayers(){
        System.out.print("How Many Players:  ");
        String inp = kyb.nextLine();
        int size;
        try{
           size= Integer.parseInt(inp);
           if (size<1){
               throw new NumberFormatException();
           }
           return size;
        }
        catch (NumberFormatException e){
            System.out.println("Not valid enter");
            return getPlayers();
        }

    }
}
