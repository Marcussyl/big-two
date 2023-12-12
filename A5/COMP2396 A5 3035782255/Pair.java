/**
 * A Pair class is a subclass of Hand class to model a hand of pair.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class Pair extends Hand{
    /**
     * A constructor for pair objects.
     * @param player the player who played the hand
     * @param cards the cards in hand
     */
    public Pair(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * A method to check if the hand is valid.
     * @return whether the hand is valid
     */
    @Override
    public boolean isValid() {
        if(this.size() == 2){
            return this.getCard(0).getRank() == this.getCard(1).getRank();
        }
        return false;
    }

    /**
     * A method to get the type of hand.
     * @return type of hand
     */
    @Override
    public String getType() {
        return "Pair";
    }
}
