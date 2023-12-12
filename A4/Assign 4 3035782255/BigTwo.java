import java.util.ArrayList;
import java.util.Objects;

/**
 * The BigTwo class implements the CardGame interface and is used to model
 * the BigTwo Card Game with private variable storing the basic information
 * of the BigTwo game and method that runs the game.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class BigTwo implements CardGame{
    //An int specifying the number of players.
    private int numOfPlayers;

    //A deck of cards
    private Deck deck = new Deck();

    //A list of players.
    private ArrayList<CardGamePlayer> playerList;

    //A list of hands played on the table.
    private ArrayList<Hand> handsOnTable;

    //An integer specifying the index of the current player.
    private int currentPlayerIdx;

    //A BigTwoGUI object for providing the user interface.
    //private BigTwoGUI ui;
    private BigTwoGUI ui;

    //A String object for restoring the result of checkMove method. 0:Valid 1:Invalid 2:Pass
    private int checkMoveResult;

    /**
     * BigTwo constructor to initialize BigTwo object
     */
    public BigTwo(){
        this.handsOnTable = new ArrayList<>();
        this.playerList = new ArrayList<>();
        this.numOfPlayers = 4;
        for (int i = 0; i < numOfPlayers; i++){
            CardGamePlayer player = new CardGamePlayer();
            playerList.add(player);
        }
        this.ui = new BigTwoGUI(this);
    }

    /**
     * A method for getting the number of players.
     * @return the number of players
     */
    public int getNumOfPlayers(){
        return this.numOfPlayers;
    }

    /**
     * A method for retrieving the deck of cards being used.
     * @return a deck of cards
     */
    public Deck getDeck(){
        return this.deck;
    }

    /**
     * A method for retrieving the list of players.
     * @return a list of player
     */
    public ArrayList<CardGamePlayer> getPlayerList(){
        return this.playerList;
    }

    /**
     * A method for retrieving the list of hands played on the table.
     * @return hands on the table
     */
    public ArrayList<Hand> getHandsOnTable(){
        return this.handsOnTable;
    }

    /**
     * A method for retrieving the index of the current player.
     * @return the index of current player
     */
    public int getCurrentPlayerIdx(){
        return this.currentPlayerIdx;
    }

    /**
     * A method for starting/restarting the game with a given shuffled deck of cards.
     * @param deck the deck of (shuffled) cards to be used in this game
     */
    public void start(Deck deck){
        //remove all the cards from the players as well as from the table
        for (CardGamePlayer player : this.playerList) {
            player.removeAllCards();
        }
        for(Hand hand:this.handsOnTable){
            hand.removeAllCards();
        }

        //Distribute the cards to the players
        for (int i = 0; i < deck.size(); i++){
            this.playerList.get(i%4).addCard(deck.getCard(i));
        }

        //Identify the player who holds the Three of Diamonds, and set both the currentPlayerIdx of the BigTwo object and the activePlayer of the BigTwoUI object to the index of the player who holds the Three of Diamonds;
        for(int i = 0; i < this.numOfPlayers; i++){
            playerList.get(i).sortCardsInHand();
            if(this.playerList.get(i).getCardsInHand().contains(new BigTwoCard(0,2))){
                 this.currentPlayerIdx = i;
                 ui.setActivePlayer(i);
            }
        }

        this.ui.promptActivePlayer();
        this.ui.repaint();
    }

    /**
     * A method for making a move by a player with the specified index using the cards specified by the list of indices.
     * @param playerIdx the index of the player who makes the move
     * @param cardIdx   the list of the indices of the cards selected by the player
     */
    public void makeMove(int playerIdx, int[] cardIdx){ //0:Valid 1:Invalid 2:Pass
        this.checkMove(playerIdx, cardIdx);
        if(this.checkMoveResult == 1){
            this.ui.printMsg("Not a legal move!!!");
            this.ui.promptActivePlayer();
        } else if (this.checkMoveResult == 0) {
            //update currentPlayerIdx and activePlayer
            this.currentPlayerIdx = (playerIdx + 1) % 4;
            this.ui.setActivePlayer(this.currentPlayerIdx);

            CardGamePlayer player = this.playerList.get(playerIdx);
            CardList cardsPlayed = player.play(cardIdx);
            Hand handPlayed = this.composeHand(player, cardsPlayed);

            //add the hand to the handsOnTable
            this.handsOnTable.add(handPlayed);

            //remove the cards played
            playerList.get(playerIdx).removeCards(cardsPlayed);
            this.ui.printMsg("{"+handPlayed.getType()+"} " + handPlayed);
            this.ui.promptActivePlayer();
        } else if (this.checkMoveResult == 2) {
            this.currentPlayerIdx = (playerIdx + 1) % 4;
            this.ui.setActivePlayer(this.currentPlayerIdx);
            this.ui.printMsg("{Pass}");
            this.ui.promptActivePlayer();
        }

        this.ui.repaint();

        if(endOfGame()){
            this.ui.setActivePlayer(-1);
            this.ui.printMsg("Game ends");
            for (int i = 0; i < this.numOfPlayers; i++){
                if (playerList.get(i).getNumOfCards() != 0){
                    this.ui.printMsg("Player "+i+" has "+playerList.get(i).getNumOfCards()+" cards in hand.");
                }
                else{
                    this.ui.printMsg("Player "+i+" wins the game.");
                }
            }
            this.ui.disable();
        }
    }

    /**
     * A method for checking if a move made by a player is valid.
     * @param playerIdx the index of the player who makes the move
     * @param cardIdx   the list of the indices of the cards selected by the player
     */
    public void checkMove(int playerIdx, int[] cardIdx){
        //check for valid pass condition, a player cannot pass when they are the beginner, or they are the one who played the last hand on the table
        if(cardIdx == null){
            if(this.handsOnTable.isEmpty()){
                this.checkMoveResult = 1;
            } else {
                CardGamePlayer lastHandPlayer = this.handsOnTable.get(this.handsOnTable.size() - 1).getPlayer();
                CardGamePlayer currHandPlayer = this.playerList.get(playerIdx);
                if(Objects.equals(lastHandPlayer, currHandPlayer)){
                    this.checkMoveResult = 1;
                    return;
                } else {
                    this.checkMoveResult = 2;
                }
            }
            return;
        } else if (cardIdx.length > 5) {
            //check if the selected cards are more than 5
            this.checkMoveResult = 1;
            return;
        }

        //check if the hand is valid
        CardList cardsPlayed = this.playerList.get(playerIdx).play(cardIdx);
        Hand handPlayed = this.composeHand(this.playerList.get(playerIdx), cardsPlayed);
        if(handPlayed == null){
            this.checkMoveResult = 1;
            return;
        }

        //check for beginner condition
        if (this.handsOnTable.isEmpty()) {
            if(!handPlayed.contains(new Card(0,2))){
                this.checkMoveResult = 1;
            } else {
                this.checkMoveResult = 0;
            }
        } else {
            //check if the hand beats the last hand on the table
            CardGamePlayer lastHandPlayer = this.handsOnTable.get(this.handsOnTable.size() - 1).getPlayer();
            CardGamePlayer currHandPlayer = this.playerList.get(playerIdx);
            if (lastHandPlayer == currHandPlayer){
                this.checkMoveResult = 0;
            } else{
                if(handPlayed.beats(this.handsOnTable.get(this.handsOnTable.size()-1))) {
                    this.checkMoveResult = 0;
                } else {
                    this.checkMoveResult = 1;
                }
            }
        }
    }

    /**
     * A method for checking if the game ends.
     * @return whether the game has ended
     */
    public boolean endOfGame(){
        for(CardGamePlayer player: playerList){
            if(player.getCardsInHand().isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * A method for starting a Big Two card game.
     * @param args args passed by the user
     */
    public static void main(String[] args){
        BigTwo game = new BigTwo();
        BigTwoDeck deck = new BigTwoDeck();
        deck.shuffle();
        game.start(deck);
    }

    /**
     * A method for returning a valid hand from the specified list of cards of the player.
     * @param player the player who played the cards
     * @param cards the cards played by the player
     * @return a type of hand or null if no valid combination if matched
     */
    public Hand composeHand(CardGamePlayer player, CardList cards){
        Single single = new Single(player, cards);
        Pair pair = new Pair(player, cards);
        Triple triple = new Triple(player, cards);
        Straight straight = new Straight(player, cards);
        Flush flush = new Flush(player, cards);
        FullHouse fullHouse = new FullHouse(player, cards);
        Quad quad = new Quad(player,cards);
        StraightFlush straightFlush = new StraightFlush(player, cards);
        
        if(single.isValid()){
            return single;
        } else if (pair.isValid()){
            return pair;
        } else if (triple.isValid()) {
            return triple;
        } else if (straightFlush.isValid()) {
            return straightFlush;
        } else if (straight.isValid()) {
            return straight;
        } else if (flush.isValid()) {
            return flush;
        } else if (fullHouse.isValid()) {
            return fullHouse;
        } else if (quad.isValid()) {
            return quad;
        }

        return null;
    }
}
