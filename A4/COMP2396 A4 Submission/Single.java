import java.lang.*;

/**
 * A Single class is a subclass of Hand class to model a hand of single.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class Single extends Hand{
    /**
     * A constructor for single objects.
     * @param player the player who played the hand
     * @param cards the cards in hand
     */
    public Single(CardGamePlayer player, CardList cards){super(player,cards);}

    /**
     * A method to get the type of hand.
     * @return type of hand
     */
    @Override
    public String getType() {
        return "Single";
    }

    /**
     * A method to check if the hand is valid.
     * @return whether the hand is valid
     */
    @Override
    public boolean isValid() {
        return this.size() == 1;
    }
}
