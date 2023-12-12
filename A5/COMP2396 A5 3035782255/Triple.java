/**
 * A Triple class is a subclass of Hand class to model a hand of triple.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class Triple extends Hand{
    /**
     * A constructor for triple objects.
     * @param player the player who played the hand
     * @param cards the cards in hand
     */
    public Triple(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * A method to check if the hand is valid.
     * @return whether the hand is valid
     */
    @Override
    public boolean isValid() {
        if(this.size() == 3){
            return this.getCard(0).getRank() == this.getCard(1).getRank() && this.getCard(1).getRank() == this.getCard(2).getRank();
        }
        return false;
    }

    /**
     * A method to get the type of hand.
     * @return type of hand
     */
    @Override
    public String getType() {
        return "Triple";
    }
}
