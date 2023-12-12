public class BigTwoCardTester {
    public static void main(String[] args){
        //0 = Diamond, 1 = Club, 2 = Heart, 3 = Spade
        //0 = 'A', 1 = '2', 2 = '3', ..., 8 = '9', 9 = '0', 10 = 'J', 11 ='Q', 12 = 'K'
        BigTwoCard threeOfDiamond = new BigTwoCard(0,2);

        BigTwoCard threeOfDiamond2 = new BigTwoCard(0,2);

        BigTwoCard threeOfSpade = new BigTwoCard(3,2);

        BigTwoCard twoOfDiamond = new BigTwoCard(0,1);

        BigTwoCard aOfDiamond = new BigTwoCard(0, 0);

        if(threeOfDiamond.compareTo(threeOfSpade) == -1){
            System.out.println("three of diamond is smaller than three of spade");
        }

        if(threeOfDiamond.compareTo(twoOfDiamond) == -1){
            System.out.println("three of diamond is smaller than two of spade");
        }

        if(threeOfDiamond.compareTo(threeOfDiamond2) == 0){
            System.out.println("three of diamond is equal to three of spade");
        }

        if(aOfDiamond.compareTo(twoOfDiamond) == -1){
            System.out.println("A of diamond is smaller than two of diamond");
        }
    }
}
