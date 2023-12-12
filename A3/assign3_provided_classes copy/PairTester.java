public class PairTester {
    public static void main(String[] args){
        CardGamePlayer player = new CardGamePlayer();
        CardList cards = new CardList();
        cards.addCard(new BigTwoCard(0,2)); //3 of diamond
        cards.addCard(new BigTwoCard(1,2)); //3 of club
        Pair pair = new Pair(player,cards);
        System.out.println(pair.getType());
        System.out.println(pair.getTopCard().toString());
    }
}
