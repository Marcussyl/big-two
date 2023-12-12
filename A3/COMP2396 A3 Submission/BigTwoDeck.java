/**
 * A BigTwoDeck class is a subclass of Deck class to model a deck in Big Two game.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class BigTwoDeck extends Deck{
    /**
     * A method for initializing a deck of Big Two cards.
     */
    public void initialize() {
        removeAllCards();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                BigTwoCard card = new BigTwoCard(i, j);
                addCard(card);
            }
        }
    }
}
