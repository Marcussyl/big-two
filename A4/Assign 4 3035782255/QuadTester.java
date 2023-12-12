public class QuadTester {
    public static void main(String[] args){
        CardGamePlayer player = new CardGamePlayer();
        CardList cards = new CardList();
        cards.addCard(new BigTwoCard(0,2)); //3 of diamond
        cards.addCard(new BigTwoCard(1,2)); //3 of club
        cards.addCard(new BigTwoCard(2,2)); //3 of heart
        cards.addCard(new BigTwoCard(3,2)); //3 of spade
        cards.addCard(new BigTwoCard(2,6)); //7 of heart

        Quad quad = new Quad(player,cards);
        System.out.println(quad.getType());
        System.out.println(quad.getTopCard().toString());

        cards.removeAllCards();
        cards.addCard(new BigTwoCard(2,2)); //3 of heart
        cards.addCard(new BigTwoCard(0,4)); //5 of diamond
        cards.addCard(new BigTwoCard(1,4)); //5 of club
        cards.addCard(new BigTwoCard(2,4)); //5 of heart
        cards.addCard(new BigTwoCard(3,4)); //5 of spade

        quad = new Quad(player,cards);
        System.out.println(quad.getType());
        System.out.println(quad.getTopCard().toString());

    }
}
