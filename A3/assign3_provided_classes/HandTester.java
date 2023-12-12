public class HandTester {
    public static void main(String[] args){
        BigTwo game = new BigTwo();
        CardList cards = new CardList();
        CardGamePlayer player = new CardGamePlayer();

        cards.addCard(new BigTwoCard(0,10));
        cards.addCard(new BigTwoCard(0,11));
        cards.addCard(new BigTwoCard(0,12));
        cards.addCard(new BigTwoCard(0,0));
        cards.addCard(new BigTwoCard(0,1));

        Hand straightFlush = game.composeHand(player, cards);
        System.out.println(straightFlush.getType());

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,10));
        cards.addCard(new BigTwoCard(1,11));
        cards.addCard(new BigTwoCard(2,12));
        cards.addCard(new BigTwoCard(3,0));
        cards.addCard(new BigTwoCard(0,1));

        Hand straight = game.composeHand(player, cards);
        System.out.println(straight.getType());

        if(straightFlush.beats(straight)){
            System.out.println("Straight flush  beats straight!");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,10));
        cards.addCard(new BigTwoCard(1,10));
        cards.addCard(new BigTwoCard(2,10));
        cards.addCard(new BigTwoCard(3,10));
        cards.addCard(new BigTwoCard(0,1));

        Hand quad1 = game.composeHand(player, cards);
        System.out.println(quad1.getType());

        if(!straight.beats(quad1)){
            System.out.println("Straight cannot beat quad");
        }

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(0,4));
        cards.addCard(new BigTwoCard(1,4));
        cards.addCard(new BigTwoCard(2,4));
        cards.addCard(new BigTwoCard(3,4));
        cards.addCard(new BigTwoCard(0,10));

        Hand quad2 = game.composeHand(player, cards);
        System.out.println(quad2.getType());

        if(!quad2.beats(quad1)){
            System.out.println("Quad2 cannot beat quad1");
        }
    }
}
