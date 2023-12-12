import java.util.Objects;

public class BigTwoTester {
    public static void main(String[] args){
        BigTwo game = new BigTwo();
        CardGamePlayer player = new CardGamePlayer();

        CardList cards = new CardList();
        cards.addCard(new BigTwoCard(0,3));
        cards.addCard(new BigTwoCard(1,3));
        cards.addCard(new BigTwoCard(2,3));
        cards.addCard(new BigTwoCard(3,3));
        cards.addCard(new BigTwoCard(0,8));
        Hand hand = game.composeHand(player, cards);
        if(hand.getType() == "Quad"){
            System.out.println("Quad1 is composed");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,9));
        cards.addCard(new BigTwoCard(1,3));
        cards.addCard(new BigTwoCard(2,3));
        cards.addCard(new BigTwoCard(3,3));
        cards.addCard(new BigTwoCard(0,3));
        hand = game.composeHand(player, cards);
        if(hand.getType() == "Quad"){
            System.out.println("Quad2 is composed");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,9));
        hand = game.composeHand(player, cards);
        if (hand.getType() == "Single"){
            System.out.println("Single is composed");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,9));
        cards.addCard(new BigTwoCard(1,9));
        hand = game.composeHand(player, cards);
        if (hand.getType() == "Pair"){
            System.out.println("Pair is composed");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,9));
        cards.addCard(new BigTwoCard(1,9));
        cards.addCard(new BigTwoCard(2,9));
        hand = game.composeHand(player, cards);
        if (hand.getType() == "Triple"){
            System.out.println("Triple is composed");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,9));
        cards.addCard(new BigTwoCard(1,10));
        cards.addCard(new BigTwoCard(2,11));
        cards.addCard(new BigTwoCard(3,12));
        cards.addCard(new BigTwoCard(2,0));
        hand = game.composeHand(player, cards);
        if (hand.getType() == "Straight"){
            System.out.println("Straight is composed");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,9));
        cards.addCard(new BigTwoCard(1,10));
        cards.addCard(new BigTwoCard(3,11));
        cards.addCard(new BigTwoCard(2,12));
        cards.addCard(new BigTwoCard(0,0));
        hand = game.composeHand(player, cards);
        if (hand.getType() == "Flush"){
            System.out.println("Flush is composed");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,12));
        cards.addCard(new BigTwoCard(1,12));
        cards.addCard(new BigTwoCard(1,10));
        cards.addCard(new BigTwoCard(3,10));
        cards.addCard(new BigTwoCard(2,10));
        hand = game.composeHand(player, cards);
        if (hand.getType() == "Full House"){
            System.out.println("Full House is composed");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,9));
        cards.addCard(new BigTwoCard(0,10));
        cards.addCard(new BigTwoCard(0,11));
        cards.addCard(new BigTwoCard(0,12));
        cards.addCard(new BigTwoCard(0,0));
        hand = game.composeHand(player, cards);
        if (hand.getType() == "Straight Flush"){
            System.out.println("Straight Flush is composed");
        }
    }

}
