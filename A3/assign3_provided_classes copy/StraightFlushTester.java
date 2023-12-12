public class StraightFlushTester {
    public static void main(String[] args){
        CardGamePlayer player = new CardGamePlayer();
        CardList cards = new CardList();
        cards.addCard(new BigTwoCard(1,10));
        cards.addCard(new BigTwoCard(1,11));
        cards.addCard(new BigTwoCard(1,12));
        cards.addCard(new BigTwoCard(1,0));
        cards.addCard(new BigTwoCard(1,1));
        System.out.println(cards.toString());

        StraightFlush straightFlush = new StraightFlush(player,cards);
        System.out.println(straightFlush.getType());
        System.out.println(straightFlush.getTopCard().toString());
    }
}
