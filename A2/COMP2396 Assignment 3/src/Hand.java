import java.util.Objects;

/**
 * The Hand class is a subclass of the CardList class and is used to model a hand of cards.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public abstract class Hand extends CardList{
    /**
     * A private field to store the player who plays this hand.
     */
    private CardGamePlayer player;

    /**
     * A constructor for building a hand with the specified player and list of cards.
     * @param player the player who played the hand
     * @param cards the cards in the hand
     */
    public Hand(CardGamePlayer player, CardList cards){
        this.player = player;
        for (int i = 0; i < cards.size(); i++) {
            this.addCard(cards.getCard(i));
        }
    }

    /**
     * A method to get the player
     * @return the player who played the hand
     */
    public CardGamePlayer getPlayer(){
        return this.player;
    }

    /**
     *A method for retrieving the top card of the hand.
     * @return the top card of the hand
     */
    public Card getTopCard(){
        this.sort(); //card will be arranged in ascending order base on compareTo() in Card class
        if(this.size() != 5){
            return this.getCard(4);
        } else{
            //quad1
            if(this.getCard(0).getRank() == this.getCard(1).getRank() &&
                    this.getCard(1).getRank() == this.getCard(2).getRank() &&
                    this.getCard(2).getRank() == this.getCard(3).getRank()){
                return this.getCard(3);
            } //quad2
            else if (this.getCard(1).getRank() == this.getCard(2).getRank() &&
                    this.getCard(2).getRank() == this.getCard(3).getRank() &&
                    this.getCard(3).getRank() == this.getCard(4).getRank()) {
                return this.getCard(4);
            } //full house1
            else if (this.getCard(0).getRank() == this.getCard(1).getRank() &&
                    this.getCard(1).getRank() == this.getCard(2).getRank() &&
                    this.getCard(3).getRank() == this.getCard(4).getRank()) {
                return this.getCard(2);
            } //full house2
            else if (this.getCard(0).getRank() == this.getCard(1).getRank() &&
                    this.getCard(2).getRank() == this.getCard(3).getRank() &&
                    this.getCard(3).getRank() == this.getCard(4).getRank()) {
                return this.getCard(4);
            } //straight, flush, straight flash -> return the last card
            else {
                return this.getCard(4);
            }
        }
    }

    /**
     * A method for checking if this hand beats a specified hand.
     * @param hand the hand which will be checked if it beats this hand
     * @return whether the hand beats this hand
     */
    public boolean beats(Hand hand){
        Card topCard = this.getTopCard();
        if(hand.getTopCard().rank != topCard.rank){
            return hand.getTopCard().rank < topCard.rank;
        } else{
            return hand.getTopCard().suit < topCard.suit;
        }
    }

    /**
     * A abstract method for checking if the hand is valid.
     * @return whether the hand is valid
     */
    public abstract boolean isValid();

    /**
     * A method for returning a string specifying the type of hand.
     * @return type of hand
     */
    public abstract String getType();
}
