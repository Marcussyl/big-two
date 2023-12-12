public class SingleTester {
    public static void main(String[] args){
        CardGamePlayer player = new CardGamePlayer();
        CardList cards = new CardList();
        cards.addCard(new BigTwoCard(0,2)); //3 of diamond

        Single single = new Single(player,cards);
        System.out.println(single.getType());
        System.out.println(single.getTopCard().toString());
    }
}
