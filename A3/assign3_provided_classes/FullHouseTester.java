public class FullHouseTester {
    public static void main(String[] args){
        CardGamePlayer player = new CardGamePlayer();
        CardList cards = new CardList();
        cards.addCard(new BigTwoCard(0,2)); //3 of diamond
        cards.addCard(new BigTwoCard(1,2)); //3 of club
        cards.addCard(new BigTwoCard(2,2)); //3 of heart
        cards.addCard(new BigTwoCard(2,5));
        cards.addCard(new BigTwoCard(3,5));
        for(int i = 0; i < cards.size(); i++){
            System.out.print(cards.getCard(i).toString());
        }
        System.out.println();

        FullHouse fullHouse = new FullHouse(player,cards);
        System.out.println(fullHouse.getType());
        System.out.println(fullHouse.getTopCard().toString());
    }
}
