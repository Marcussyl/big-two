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
 */
public class BigTwoGUI implements CardGameUI{
    private final static int MAX_CARD_NUM = 13; // max. no. of cards each player holds
    private BigTwo game; //a Big Two card game associated with this GUI
    private boolean[] selected = new boolean[MAX_CARD_NUM]; // selected cards; //a boolean array indicating which cards are being selected
    private int activePlayer; //an integer specifying the index of the active player
    private JFrame frame; //the main window of the application
    private JPanel bigTwoPanel; //a panel for showing the cards of each player and the cards played on the table
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
        this.frame = new JFrame("Big Two");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu msgMenu = new JMenu("Message");
        menuBar.add(gameMenu);
        menuBar.add(msgMenu);
        JMenuItem restartItem = new JMenuItem("Restart");
        JMenuItem quitItem = new JMenuItem("Quit");
        gameMenu.add(restartItem);
        gameMenu.add(quitItem);
        restartItem.addActionListener(new RestartMenuItemListener());
        quitItem.addActionListener(new QuitMenuItemListener());
        frame.setJMenuBar(menuBar);

        //bigTwoPanel
        bigTwoPanel = new BigTwoPanel();
        repaint();
        frame.add(bigTwoPanel, BorderLayout.CENTER);

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

        frame.setSize(800,900);
        frame.setVisible(true);
    }

    /**
     * a method for setting the index of the active player
     * @param activePlayer an int value representing the index of the active player
     */
    public void setActivePlayer(int activePlayer){
        if (activePlayer < 0 || activePlayer >= playerList.size()) {
            this.activePlayer = -1;
        } else {
            this.activePlayer = activePlayer;
        }
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
        msgArea.repaint();
    }

    /**
     * a method for clearing the message area of the GUI
     */
    public void clearMsgArea(){
        msgArea.setText(null);
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
        playButton.setEnabled(false);
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
            addMouseListener(this);
            this.setBackground(darkGreen);
            avatarPaths = new ArrayList<>();
            avatarPaths.add("./src/avatars/batman_72.png");
            avatarPaths.add("./src/avatars/flash_72.png");
            avatarPaths.add("./src/avatars/green_lantern_72.png");
            avatarPaths.add("./src/avatars/superman_72.png");
            avatarPaths.add("./src/avatars/wonder_woman_72.png");
            Collections.shuffle(avatarPaths);
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

            for(int playerIdx = 0; playerIdx < playerList.size(); playerIdx++){
                playerImgContainerY = baseY+playerIdx*playerContainerHeight+playerNameHeight;
                cardsInHand = playerList.get(playerIdx).getCardsInHand();
                if(playerIdx == activePlayer || activePlayer == -1){
                    g.setColor(Color.GREEN);
                    g.drawString(playerList.get(playerIdx).getName(), baseX, baseY+playerIdx*playerContainerHeight);

                    g.drawImage(new ImageIcon(avatarPaths.get(playerIdx)).getImage(), baseX, playerImgContainerY, this);

                    for(int cardIdx = 0; cardIdx < cardsInHand.size(); cardIdx++){
                        cardPath = getCardPath(cardsInHand.getCard(cardIdx));
                        if(selected[cardIdx]){
                            g.drawImage(new ImageIcon(cardPath).getImage(), baseX+80+cardIdx*overlapGap, playerImgContainerY-10, this);
                        } else {
                            g.drawImage(new ImageIcon(cardPath).getImage(), baseX+80+cardIdx*overlapGap, playerImgContainerY, this);
                        }
                    }
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawString(playerList.get(playerIdx).getName(), baseX, baseY+playerIdx*playerContainerHeight);

                    g.drawImage(new ImageIcon(avatarPaths.get(playerIdx)).getImage(), baseX, playerImgContainerY, this);

                    cardPath = "./src/cards/b.gif";
                    for(int cardIdx = 0; cardIdx < cardsInHand.size(); cardIdx++){
                        g.drawImage(new ImageIcon(cardPath).getImage(), baseX+80+cardIdx*overlapGap, playerImgContainerY, this);
                    }
                }
                g.drawLine(0, playerContainerHeight*(playerIdx+1)-10, getWidth(), playerContainerHeight*(playerIdx+1)-10);
            }

            //table
            if(handsOnTable.isEmpty()){
                g.setColor(Color.BLACK);
                g.drawString("Table is Empty", baseX,baseY+4*playerContainerHeight);
                g.setColor(darkGreen);
                g.fillRect(baseX,baseY+4*playerContainerHeight+playerNameHeight+5, 100, 100);
            } else {
                Hand lastHandOnTable = handsOnTable.get(handsOnTable.size()-1);
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
            //check if the player's mouse click is on the active player
            if(playerClicked == activePlayer) {
                //check if the click's x and y coordinates are valid
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
                game.makeMove(activePlayer, getSelected());
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
            game.makeMove(activePlayer, null);
            repaint();
        }
    }

    //an inner class that implements the ActionListener interface to handle menu-item-click events for the “Restart” menu item
    private class RestartMenuItemListener implements ActionListener{
        /**
         * Invoked when an action occurs
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
            BigTwoDeck deck = new BigTwoDeck();
            deck.shuffle();
            game.start(deck);
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
            chatArea.append(userInput+"\n");
            repaint();
            chatInput.setText("");
        }
    }

    //an inner class that implements the ActionListener interface to handle message inputs when user click "Send" button
    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userInput = chatInput.getText();
            chatArea.append(userInput+"\n");
            repaint();
            chatInput.setText("");
        }
    }
}
