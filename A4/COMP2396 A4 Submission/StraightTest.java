public class StraightTest {
    public static void main(String[] args){
        CardGamePlayer player = new CardGamePlayer();
        CardList cards = new CardList();
        cards.addCard(new BigTwoCard(1,10));
        cards.addCard(new BigTwoCard(2,11));
        cards.addCard(new BigTwoCard(2,12));
        cards.addCard(new BigTwoCard(3,0));
        cards.addCard(new BigTwoCard(3,1));
        for(int i = 0; i < cards.size(); i++){
            System.out.print(cards.getCard(i).toString());
        }
        System.out.println();

        Straight straight = new Straight(player,cards);
        System.out.println(straight.getType());
        System.out.println(straight.getTopCard().toString());
    }
}
