import java.util.ArrayList;

/**
 * The BigTwo class implements the CardGame interface and is used to model a Big Two card game.
 */

public class BigTwo {
    /**
     * an int specifying the number of players
     */
    private int numOfPlayers;

    /**
     * a deck of cards.
     */
    private Deck deck;

    /**
     * a list of players.
     */
    private ArrayList<CardGamePlayer> playerList;

    /**
     * a list of hands played on the table.
     */
    private ArrayList<Hand> handsOnTable;

    /**
     * an integer specifying the index of the current player.
     */
    private int currentPlayerIdx;

    /**
     * a BigTwoUI object for providing the user interface.
     */
    private BigTwoUI ui;

    private int checkMoveResult; //0 is pass, 1 is valid and -1 is invalid.

    /**
     * a method for getting the number of players.
     *
     * @return
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * a method for retrieving the deck of cards being used.
     *
     * @return
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * a method for retrieving the list of players.
     *
     * @return
     */
    public ArrayList<CardGamePlayer> getPlayerList() {
        return playerList;
    }

    /**
     * a method for retrieving the list of hands played on the table.
     *
     * @return
     */
    public ArrayList<Hand> getHandsOnTable() {
        return handsOnTable;
    }

    /**
     * a method for retrieving the index of the current player.
     *
     * @return
     */
    public int getCurrentPlayerIdx() {
        return currentPlayerIdx;
    }

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
        this.ui = new BigTwoUI(this);
    }

    /**
     * a method for starting/restarting the game with a given shuffled deck of cards.
     * @param deck A given deck of cards to start the game
     */
    public void start(Deck deck){
        //remove all the cards from players and table
        for (CardGamePlayer player:this.playerList){
            player.removeAllCards();
        }
        for(Hand hand:this.handsOnTable){
            hand.removeAllCards();
        }
        //distribute the cards to players.
        for(int i = 0; i < deck.size(); i++){
            this.playerList.get(i%4).addCard(deck.getCard(i));
        }
        //identify the player who hold the Three of Diamonds;
        for(int i = 0; i < this.numOfPlayers; i++){
            playerList.get(i).sortCardsInHand();
            if(this.playerList.get(i).getCardsInHand().contains(new BigTwoCard(0,2))){
                this.currentPlayerIdx = i;
                ui.setActivePlayer(i);
            }
        }

        while (!this.endOfGame()){
            this.ui.repaint();
            this.ui.promptActivePlayer();
        }

        // Print message for ending the game
        this.ui.setActivePlayer(-1);
        this.ui.repaint();
        System.out.println();
        System.out.println("Game ends");
        for (int i = 0; i < this.numOfPlayers; i++){
            if (playerList.get(i).getNumOfCards() != 0){
                System.out.println("Player "+i+" has "+playerList.get(i).getNumOfCards()+" cards in hand.");
            }
            else{
                System.out.println("Player "+i+" wins the game.");
            }
        }


    }

    /**
     * a method for making a move by a player with the specified index using the cards specified by the list of indices.
     * @param playerIdx player index
     * @param cardIdx card index
     */
    public void makeMove(int playerIdx, int[] cardIdx){
        checkMove(playerIdx, cardIdx);
        if (checkMoveResult == -1){
            System.out.println("Not a legal move!!!");
            ui.promptActivePlayer();

        }
        else if(checkMoveResult == 0){
            System.out.println("{Pass}");
            System.out.println();
            //change current player index.
            currentPlayerIdx = (currentPlayerIdx+1) % 4;
            ui.setActivePlayer(currentPlayerIdx);
        }
        else{
            CardGamePlayer CurrentPlayer = playerList.get(playerIdx);
            CardList Cards = CurrentPlayer.play(cardIdx);
            Hand CurrentHand = composeHand(CurrentPlayer,Cards);
            //change current player index.
            currentPlayerIdx = (currentPlayerIdx+1) % 4;
            ui.setActivePlayer(currentPlayerIdx);
            //add cards to handsOnTable
            this.handsOnTable.add(CurrentHand);
            //remove cards
            playerList.get(playerIdx).removeCards(Cards);

            System.out.print("{"+CurrentHand.getType()+"} ");
            CurrentHand.print(true, false);
            System.out.println();
        }
    }



    /**
     * a method for checking a move made by a player.
     * @param playerIdx player index
     * @param cardIdx card index
     */
    public void checkMove(int playerIdx, int[] cardIdx){
        CardGamePlayer CurrentPlayer = playerList.get(playerIdx);
        if (cardIdx == null){
            if(handsOnTable.isEmpty()){
                checkMoveResult = -1;
            } else if (CurrentPlayer == handsOnTable.get(handsOnTable.size()-1).getPlayer()) {
                checkMoveResult = -1;
            } else{
                checkMoveResult = 0;
            }
        }
        else {
            CardList Cards = CurrentPlayer.play(cardIdx);
            Hand CurrentHand = composeHand(CurrentPlayer,Cards);
            if (CurrentHand == null) {
                checkMoveResult = -1;
            } else {
                if (handsOnTable.isEmpty()) {
                    if (CurrentPlayer.getCardsInHand().contains(new BigTwoCard(0, 2))) {
                        checkMoveResult = 1;
                    }
                    else{
                        checkMoveResult = -1;
                    }
                } else {

                    if (CurrentHand.beats(handsOnTable.get(handsOnTable.size()-1))) {
                        checkMoveResult = 1;
                    }
                    else{
                        checkMoveResult = -1;
                    }
                }
            }
        }
    }

    /**
     * a method for returning a valid hand from the specified list of cards of the player.
     * @param player The current player
     * @param cards Cards in hand
     * @return
     */
    public Hand composeHand(CardGamePlayer player, CardList cards) {
        Single SingleHand = new Single(player, cards);
        Pair PairHand = new Pair(player, cards);
        Triple TripleHand = new Triple(player, cards);
        Quad QuadHand = new Quad(player, cards);
        Straight StraightHand = new Straight(player, cards);
        Flush FlushHand = new Flush(player,cards);
        FullHouse FullHouseHand = new FullHouse(player,cards);
        StraightFlush StraightFlushHand = new StraightFlush(player,cards);
        if(StraightFlushHand.isValid())
        {
            return StraightFlushHand;
        }

        if(QuadHand.isValid())
        {
            return QuadHand;
        }

        if(FullHouseHand.isValid())
        {
            return FullHouseHand;
        }

        if(FlushHand.isValid())
        {
            return FlushHand;
        }

        if(StraightHand.isValid())
        {
            return StraightHand;
        }

        if(TripleHand.isValid())
        {
            return TripleHand;
        }

        if(PairHand.isValid())
        {
            return PairHand;
        }

        if(SingleHand.isValid())
        {
            return SingleHand;
        }
        return null;
    }

    /**
     * a method for checking if the game ends.
     * @return
     */
    public boolean endOfGame(){
        for(CardGamePlayer player: playerList){
            if (player.getCardsInHand().isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BigTwo Game = new BigTwo();
        BigTwoDeck deck = new BigTwoDeck();
        deck.shuffle();
        Game.start(deck);

    }



}
