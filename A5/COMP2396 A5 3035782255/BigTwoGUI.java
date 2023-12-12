import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A class simulate the GUI for Big Two game to handle all user actions
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class BigTwoGUI implements CardGameUI{
    private final static int MAX_CARD_NUM = 13; // max. no. of cards each player holds
    private BigTwo game; //a Big Two card game associated with this GUI
    private boolean[] selected = new boolean[MAX_CARD_NUM]; // selected cards; //a boolean array indicating which cards are being selected
    private int activePlayer = -2; //an integer specifying the index of the active player
    private JFrame frame; //the main window of the application
    private BigTwoPanel bigTwoPanel; //a panel for showing the cards of each player and the cards played on the table
    private JButton playButton; //a “Play” button for the active player to play the selected cards
    private JButton passButton; //a “Pass” button for the active player to pass his/her turn to the next player
    private JTextArea msgArea; //a text area for showing the current game status as well as end of game messages
    private JTextArea chatArea; //a text area for showing chat messages sent by the players
    private JTextField chatInput; //a text field for players to input chat messages

    //additional fields
    private ArrayList<CardGamePlayer> playerList; // the list of players
    private ArrayList<Hand> handsOnTable; // the list of hands played on the
    private Border border = BorderFactory.createLineBorder(Color.red, 2); // border object
    private Color darkGreen = new Color(30, 160, 100); // color object used for the background of the BigTwoPanel


    /**
     * a constructor for creating a BigTwoGUI
     * @param game a reference to a Big Two card game associated with this GUI
     */
    public BigTwoGUI(BigTwo game){
        this.game = game;
        this.playerList = game.getPlayerList();
        this.handsOnTable = game.getHandsOnTable();
        initialize();
    }

    //a method for initialing the GUI when BigTwoGUI is constructed
    private void initialize(){
        frame = new JFrame("Big Two");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu msgMenu = new JMenu("Message");
        menuBar.add(gameMenu);
        menuBar.add(msgMenu);
        JMenuItem connectItem = new JMenuItem("Connect");
        JMenuItem quitItem = new JMenuItem("Quit");
        gameMenu.add(connectItem);
        gameMenu.add(quitItem);
        connectItem.addActionListener(new ConnectMenuItemListener());
        quitItem.addActionListener(new QuitMenuItemListener());
        frame.setJMenuBar(menuBar);

        //bigTwoPanel
        bigTwoPanel = new BigTwoPanel();
        bigTwoPanel.addMouseListener(bigTwoPanel);
        frame.add(bigTwoPanel, BorderLayout.CENTER);
        repaint();

        //msgArea and chatArea
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        msgArea = new JTextArea(15,30);
        msgArea.setEditable(false);
        JScrollPane msgScrollPane = new JScrollPane(msgArea);
        msgScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        msgScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        chatArea = new JTextArea(15,30);
        chatArea.setEditable(false);
        chatArea.setForeground(Color.BLUE);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);


        infoPanel.add(msgScrollPane);
        infoPanel.add(chatScrollPane);
        frame.add(infoPanel, BorderLayout.EAST);

        //button and chat
        JPanel footerPanel = new JPanel();

        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play");
        passButton = new JButton("Pass");
        playButton.addActionListener(new PlayButtonListener());
        passButton.addActionListener(new PassButtonListener());
        buttonPanel.add(playButton);
        buttonPanel.add(passButton);

        JPanel chatPanel = new JPanel();
        JLabel msgLabel = new JLabel("Message: ");
        chatInput = new JTextField(15);
        chatInput.addActionListener(new EnterListener());
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        chatPanel.add(msgLabel);
        chatPanel.add(chatInput);
        chatPanel.add(sendButton);

        footerPanel.add(buttonPanel);
        footerPanel.add(chatPanel);
        frame.add(footerPanel, BorderLayout.SOUTH);

        frame.setSize(800,850);
        frame.setVisible(true);
    }

    /**
     * a method for setting the index of the active player
     * @param activePlayer an int value representing the index of the active player
     */
    public void setActivePlayer(int activePlayer){
        this.activePlayer = activePlayer;
    }

    /**
     * a method for repainting the GUI
     */
    public void repaint(){
        resetSelected();
        frame.repaint();
    }

    /**
     * a method for printing the specified string to the message area of the GUI
     * @param msg the string to be printed to the message area of the card game user interface
     */
    public void printMsg(String msg){
        msgArea.append(msg+"\n");
        repaint();
    }

    /**
     * a method for printing the specified string to the chat area of the GUI
     * @param msg the string to be printed to the chat area of the GUI
     */
    public void printChatMsg(String msg){
        chatArea.append(msg+"\n");
        repaint();
    }

    /**
     * a method to get the frame of the GUI
     * @return the frame of the GUI
     */
    public JFrame getFrame(){
        return frame;
    }

    /**
     * a method for setting the title of the window
     * @param title the title by which the window title will be set
     */
    public void setTitle(String title){
        frame.setTitle(title);
    }

    /**
     * a method for clearing the message area of the GUI
     */
    public void clearMsgArea(){
        msgArea.setText(null);
        repaint();
    }

    /**
     * a method for resetting the GUI
     */
    public void reset(){
        resetSelected();
        clearMsgArea();
        enable();
    }

    /**
     * a method for enabling user interactions with the GUI
     */
    public void enable(){
        playButton.setEnabled(true);
        passButton.setEnabled(true);
        bigTwoPanel.setEnabled(true);
    }

    /**
     * a method for disabling user interactions with the GUI
     */
    public void disable(){
        playButton.setEnabled(false);
        passButton.setEnabled(false);
        bigTwoPanel.setEnabled(false);
    }

    /**
     * a method for prompting the active player to select cards and make his/her move
     */
    public void promptActivePlayer(){
        printMsg(playerList.get(activePlayer).getName()+"'s turn:");
    }

    //an inner class that extends the JPanel class and implements the MouseListener interface
    private class BigTwoPanel extends JPanel implements MouseListener{
        private ArrayList<String> avatarPaths;
        private final int overlapGap = 15;
        private final int playerNameHeight = 10;
        private final int playerContainerHeight = 145;
        private final int baseY = 15;
        private final int baseX = 10;

        /**
         * a constructor for BigTwoPanel
         */
        public BigTwoPanel(){
//            addMouseListener(this);
            this.setBackground(darkGreen);
            avatarPaths = new ArrayList<>();
            avatarPaths.add("./src/avatars/batman_72.png");
            avatarPaths.add("./src/avatars/flash_72.png");
            avatarPaths.add("./src/avatars/green_lantern_72.png");
            avatarPaths.add("./src/avatars/superman_72.png");
            avatarPaths.add("./src/avatars/wonder_woman_72.png");
            //Collections.shuffle(avatarPaths);
        }

        /**
         * a method for getting the filepath of the card image
         * @param card the card image need to find
         * @return the filepath of the card image
         */
        private String getCardPath(Card card){
            StringBuilder strBuilder = new StringBuilder("./src/cards/");
            strBuilder.append(card.getRank()+1);
            switch (card.getSuit()){
                case 0: strBuilder.append("d.gif"); break;
                case 1: strBuilder.append("c.gif"); break;
                case 2: strBuilder.append("h.gif"); break;
                case 3: strBuilder.append("s.gif"); break;
            }
            return strBuilder.toString();
        }

        /**
         * to draw the player avatars and cards in hand
         * @param g the Graphics object to protect
         */
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            CardList cardsInHand;
            String cardPath;
            int playerImgContainerY;

            //draw the lines
            for(int lineIdx = 0; lineIdx < 4; lineIdx++){
                g.drawLine(0, playerContainerHeight*(lineIdx+1)-10, getWidth(), playerContainerHeight*(lineIdx+1)-10);
            }

            //draw the player name, avatar and cards in hand for each player
            for(int playerIdx = 0; playerIdx < playerList.size(); playerIdx++){
                playerImgContainerY = baseY+playerIdx*playerContainerHeight+playerNameHeight;
                cardsInHand = playerList.get(playerIdx).getCardsInHand();
                if(playerList.get(playerIdx).getName() != "pending"){ //check if the player has joined the game
                    if(game.getClient().getPlayerID() == playerIdx || activePlayer == -1){ //check if the player is the local player or the game has ended
                        Color textColor = (activePlayer == playerIdx) ? Color.GREEN : (game.getClient().getPlayerID() == playerIdx) ? Color.BLUE : Color.BLACK;
                        String playerName = (playerIdx == game.getClient().getPlayerID()) ? "You" : playerList.get(playerIdx).getName();
                        g.setColor(textColor);
                        g.drawString(playerName, baseX, baseY+playerIdx*playerContainerHeight);

                        //if the player name is empty string (the player leaves the game)
                        if(playerList.get(playerIdx).getName() != "") {
                            g.drawImage(new ImageIcon(avatarPaths.get(playerIdx)).getImage(), baseX, playerImgContainerY, this);
                        }

                        for(int cardIdx = 0; cardIdx < cardsInHand.size(); cardIdx++){
                            cardPath = getCardPath(cardsInHand.getCard(cardIdx));
                            if(selected[cardIdx]){
                                g.drawImage(new ImageIcon(cardPath).getImage(), baseX+80+cardIdx*overlapGap, playerImgContainerY-10, this);
                            } else {
                                g.drawImage(new ImageIcon(cardPath).getImage(), baseX+80+cardIdx*overlapGap, playerImgContainerY, this);
                            }
                        }
                    } else {
                        Color textColor = (activePlayer == playerIdx) ? Color.GREEN : Color.BLACK;
                        g.setColor(textColor);

                        g.drawString(playerList.get(playerIdx).getName(), baseX, baseY+playerIdx*playerContainerHeight);

                        if(playerList.get(playerIdx).getName() != "") {
                            g.drawImage(new ImageIcon(avatarPaths.get(playerIdx)).getImage(), baseX, playerImgContainerY, this);
                        }

                        cardPath = "./src/cards/b.gif";
                        for(int cardIdx = 0; cardIdx < cardsInHand.size(); cardIdx++){
                            g.drawImage(new ImageIcon(cardPath).getImage(), baseX+80+cardIdx*overlapGap, playerImgContainerY, this);
                        }
                    }
                }

            }

            //draw the last hand on table
            if(handsOnTable.isEmpty()){
                g.setColor(Color.BLACK);
                g.drawString("Table is Empty", baseX,baseY+4*playerContainerHeight);
                g.setColor(darkGreen);
                g.fillRect(baseX,baseY+4*playerContainerHeight+playerNameHeight+5, 100, 100);
            } else {
                Hand lastHandOnTable = handsOnTable.get(handsOnTable.size()-1);
                g.setColor(Color.BLACK);
                g.drawString("Played by "+lastHandOnTable.getPlayer().getName(), baseX,baseY+4*playerContainerHeight);
                for(int cardIdx = 0; cardIdx < lastHandOnTable.size(); cardIdx++){
                    cardPath = getCardPath(lastHandOnTable.getCard(cardIdx));
                    g.drawImage(new ImageIcon(cardPath).getImage(), baseX+cardIdx*overlapGap, baseY+4*playerContainerHeight+playerNameHeight+5,this);
                }
            }

        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            int cardWidth = new ImageIcon("./src/cards/2c.gif").getImage().getWidth(null);
            int playerClicked = (e.getY()-baseY) / playerContainerHeight;

            if(playerClicked == game.getClient().getPlayerID() && playerClicked == activePlayer) {
                int numOfCardsInHand = playerList.get(activePlayer).getNumOfCards();
                if(e.getY() > baseY+activePlayer*playerContainerHeight+playerNameHeight && e.getX() > baseX+80 && e.getX() < baseX+80+(numOfCardsInHand-1)*overlapGap+cardWidth){
                    int cardClicked = Math.min(((e.getX() - 80 - baseX) / overlapGap), numOfCardsInHand - 1);
                    selected[cardClicked] = !selected[cardClicked];
                    repaint();
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    /**
     * Returns an array of indices of the cards selected through the UI.
     *
     * @return an array of indices of the cards selected, or null if no valid cards
     *         have been selected
     */
    private int[] getSelected() {
        int[] cardIdx = null;
        int count = 0;
        for (int j = 0; j < selected.length; j++) {
            if (selected[j]) {
                count++;
            }
        }

        if (count != 0) {
            cardIdx = new int[count];
            count = 0;
            for (int j = 0; j < selected.length; j++) {
                if (selected[j]) {
                    cardIdx[count] = j;
                    count++;
                }
            }
        }
        return cardIdx;
    }

    //Resets the list of selected cards to an empty list.
    private void resetSelected() {
        Arrays.fill(selected, false);
    }

    //an inner class that implements the ActionListener interface to handle button-click events for the “Play” button
    private class PlayButtonListener implements ActionListener {
        /**
         * Invoked when an action occurs
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (getSelected() == null)
            {
                printMsg("Select card(s) to play.");
            }
            else {
                //game.makeMove(activePlayer, getSelected());
                game.getClient().sendMessage(new CardGameMessage(CardGameMessage.MOVE, activePlayer, getSelected()));
            }
            repaint();
        }
    }

    //an inner class that implements the ActionListener interface to handle button-click events for the “Pass” button
    private class PassButtonListener implements ActionListener{
        /**
         * Invoked when an action occurs
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //game.makeMove(activePlayer, null);
            game.getClient().sendMessage(new CardGameMessage(CardGameMessage.MOVE, activePlayer, null));
            repaint();
        }
    }

    //an inner class that implements the ActionListener interface to handle menu-item-click events for the “Connect” menu item
    private class ConnectMenuItemListener implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            game.getClient().connect();
        }
    }

    //an inner class that implements the ActionListener interface to handle menu-item-click events for the “Quit” menu item
    private class QuitMenuItemListener implements ActionListener {
        /**
         * Invoked when an action occurs
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    //an inner class that implements the ActionListener interface to handle message inputs when user hit "Enter"
    private class EnterListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = chatInput.getText();
            game.getClient().sendMessage(new CardGameMessage(CardGameMessage.MSG, -1, userInput));
            //printChatMsg(userInput);
            repaint();
            chatInput.setText("");
        }
    }

    //an inner class that implements the ActionListener interface to handle message inputs when user click "Send" button
    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = chatInput.getText();
            game.getClient().sendMessage(new CardGameMessage(CardGameMessage.MSG, -1, userInput));
            //printChatMsg(userInput);
            repaint();
            chatInput.setText("");
        }
    }
}
