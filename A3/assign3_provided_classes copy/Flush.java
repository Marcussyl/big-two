/**
 * Flush class is a subclass of Hand class to model a hand of flush.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class Flush extends Hand{
    /**
     * A constructor for Flush objects
     * @param player the player who played the hand
     * @param cards the cards in hand
     */
    public Flush(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * A method to check is the hand is valid.
     * @return whether the hand is valid
     */
    @Override
    public boolean isValid() {
        if(this.size() == 5){
            for(int i = 0; i < 4; i++){
                if(this.getCard(i+1).getSuit() != this.getCard(i).getSuit()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * A method to get the type of hand.
     * @return type of hand
     */
    @Override
    public String getType() {
        return "Flush";
    }
}
