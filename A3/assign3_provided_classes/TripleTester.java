public class TripleTester {
    public static void main(String[] args){
        CardGamePlayer player = new CardGamePlayer();
        CardList cards = new CardList();
        cards.addCard(new BigTwoCard(0,2)); //3 of diamond
        cards.addCard(new BigTwoCard(1,2)); //3 of club
        cards.addCard(new BigTwoCard(2,2)); //3 of heart

        Triple triple = new Triple(player,cards);
        System.out.println(triple.getType());
        System.out.println(triple.getTopCard().toString());
    }
}
