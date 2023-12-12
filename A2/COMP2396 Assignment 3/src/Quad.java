/**
 * Quad is subclass of Hand, which model the quad hand type.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class Quad extends Hand{
    /**
     * Constructor for Quad
     * @param player the player who played full house
     * @param cards the cards in this hand
     */
    public Quad(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * A method to check if the hand is a valid hand of quad.
     * @return whether the hand is a valid hand of quad
     */
    @Override
    public boolean isValid() {
        if(this.size() == 5){
            this.sort();
            int card1Rank = this.getCard(0).getRank();
            int card2Rank = this.getCard(1).getRank();
            int card3Rank = this.getCard(2).getRank();
            int card4Rank = this.getCard(3).getRank();
            int card5Rank = this.getCard(4).getRank();
            if(card1Rank == card2Rank && card2Rank == card3Rank && card3Rank == card4Rank ||
                    card2Rank == card3Rank && card3Rank == card4Rank && card4Rank == card5Rank){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * A method to get the type of hand.
     * @return type of hand
     */
    @Override
    public String getType() {
        return "Quad";
    }
}
