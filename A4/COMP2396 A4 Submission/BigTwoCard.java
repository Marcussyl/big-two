/**
 * A subclass of the Card class and is used to model a card used in a Big Two card game.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class BigTwoCard extends Card{
    /**
     * A constructor for building a card with the specified suit and rank.
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public BigTwoCard(int suit, int rank){
        super(suit, rank);
    }

    /**
     * A method for comparing the order of this card with the specified card.
     * @param card the object to be compared
     * @return a negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the specified card
     */
    public int compareTo(Card card){
        int currCardRank = this.rank < 2 ? this.rank+13 : this.rank;
        int anotherCardRank = card.getRank() < 2 ? card.getRank()+13 : card.getRank();

        if(currCardRank < anotherCardRank ){
            return -1;
        } else if(currCardRank > anotherCardRank){
            return 1;
        } else if(this.suit > card.suit){
            return 1;
        } else if(this.suit < card.suit){
            return -1;
        } else {
            return 0;
        }
    }
}
