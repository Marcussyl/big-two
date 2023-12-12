import java.util.Arrays;

/**
 * StraightFlush is a subclass of Hand, which models the hand type of straight flush.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class StraightFlush extends Hand{
    /**
     * Constructor for stright flush
     * @param player the player who played full house
     * @param cards the cards in this hand
     */
    public StraightFlush(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * A method to check if the hand is a valid straight flush.
     * @return whether the hand is a valid straight flush
     */
    @Override
    public boolean isValid() {
        int[] updatedRank = new int[5];
        if(this.size() == 5){
            for(int i = 0; i < 5; i++){
                int tempRank = this.getCard(i).getRank() < 2 ? this.getCard(i).getRank()+13 : this.getCard(i).getRank();
                updatedRank[i] = tempRank+13;
            }
            Arrays.sort(updatedRank);

            //make sure the ranks are consecutive
            for(int i = 0; i < 4; i++){
                if(updatedRank[i+1] - updatedRank[i] != 1){
                    return false;
                }
            }

            //make sure the suit are the same
            return this.getCard(0).getSuit() == this.getCard(1).getSuit() && this.getCard(1).getSuit() == this.getCard(2).getSuit() && this.getCard(2).getSuit() == this.getCard(3).getSuit() && this.getCard(3).getSuit() == this.getCard(4).getSuit();
        }
        return false;
    }

    /**
     * A method to get the type of hand.
     * @return type of hand
     */
    @Override
    public String getType() {
        return "Straight Flush";
    }
}
