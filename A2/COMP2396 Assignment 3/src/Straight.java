import java.util.Arrays;

/**
 * A subclass of Hand class to model a hand of Single.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class Straight extends Hand{
    /**
     * A constructor to initialize straight object.
     * @param player the player who played the hand
     * @param cards the cards in the hand
     */
    public Straight(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * A method to check if the hand is a valid hand of single.
     * @return whether the hand is a valid hand of single
     */
    @Override
    public boolean isValid() {
        int[] amendedRank = new int[5];
        if(this.size() == 5){
            for(int i = 0; i < 5; i++){
                int tempRank = this.getCard(i).getRank();
                if(tempRank < 3){ //make test for this after finish (i.e. K A 2 is valid?)
                    amendedRank[i] = tempRank+14;
                }else {
                    amendedRank[i] = tempRank;
                }
            }
            Arrays.sort(amendedRank);

            for(int i = 0; i < 4; i++){
                if(amendedRank[i+1] - amendedRank[i] != 1){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * A method to get the type of hand.
     * @return the type of hand
     */
    @Override
    public String getType() {
        return "Straight";
    }
}
