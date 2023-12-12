public class FlushTester {
    public static void main(String[] args){
        CardGamePlayer player = new CardGamePlayer();
        CardList cards = new CardList();
        cards.addCard(new BigTwoCard(0,2)); //3 of diamond
        cards.addCard(new BigTwoCard(0,3));
        cards.addCard(new BigTwoCard(0,6));
        cards.addCard(new BigTwoCard(0,8));
        cards.addCard(new BigTwoCard(0,10));

        Flush flush = new Flush(player,cards);
        System.out.println(flush.getType());
        System.out.println(flush.getTopCard().toString());
    }
}
