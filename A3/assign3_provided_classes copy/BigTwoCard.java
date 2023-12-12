public class BigTwoCard extends Card{
    BigTwoCard(int suit, int rank){
        super(suit, rank);
    }

    public int compareTo(Card card) {
        int this_rank = this.rank;
        int card_rank = card.rank;

        if(this_rank < 2) {
            this_rank = this_rank + 13;
        }
        if(card_rank < 2) {
            card_rank = card_rank + 13;
        }
        if (this_rank > card_rank) {
            return 1;
        } else if (this_rank < card_rank) {
            return -1;
        } else if (this.suit > card.suit) {
            return 1;
        } else if (this.suit < card.suit) {
            return -1;
        } else {
            return 0;
        }
    }
}