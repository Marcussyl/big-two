import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * a class used to model a Big Two game client that is responsible for establishing a connection and communicating with the Big Two game server.
 *
 * @author Sze Yu Lam (UID: 3035782255)
 */
public class BigTwoClient implements NetworkGame{

    private BigTwo game; // a BigTwo object for the Big Two card game.
    private BigTwoGUI gui; // a BigTwoGUI object for the Big Two card game.
    private Socket sock; // a socket connection to the game server.
    private ObjectOutputStream oos; // an ObjectOutputStream for sending messages to the server.
    private int playerID; // an integer specifying the playerID (i.e., index) of the local player.
    private String playerName; // a string specifying the name of the local player.
    private String serverIP = "127.0.0.1"; // a string specifying the IP address of the game server.
    private int serverPort = 2396; // an integer specifying the TCP port of the game server.

    /**
     * a class that implements the NetworkGame interface, used for establishing a connection to the Big Two game server and handling the communications with the game server.
     * @param game the BigTwo object for the client
     * @param gui the BigTwoGUI object for the client
     */
    public BigTwoClient(BigTwo game, BigTwoGUI gui) {
        this.game = game;
        this.gui = gui;
        this.connect();
    }

    //a method for asking for player's input of their name and update their name in the player list
    private void askPlayerName() {
        //ask player to enter his/her name
        boolean enteredName = false;
        while(!enteredName){
            this.playerName = JOptionPane.showInputDialog(this.gui.getFrame(), "Please input your name: ");
            enteredName = !this.playerName.isEmpty();
        }

        //add the player to the playerList and update the GUI
        try{
            this.game.getPlayerList().get(this.playerID).setName(this.playerName);
            this.gui.setTitle("Big Two ("+this.playerName+")");
            this.gui.repaint();
        } catch (Exception e){
            System.out.println("Error when updating the player name for local player and repaint the GUI");
        }


        //send CardGameMessage.JOIN to the server
        try{
            this.oos.writeObject(new CardGameMessage(CardGameMessage.JOIN, this.playerID, this.playerName));
        } catch (IOException e){
            System.out.println("Error when sending CardGameMessage.JOIN to the server");
        }
    }

    /**
     * Returns the playerID (index) of the local player.
     *
     * @return the playerID (index) of the local player
     */
    @Override
    public int getPlayerID() {
        return this.playerID;
    }

    /**
     * Sets the playerID (index) of the local player.
     *
     * @param playerID the playerID (index) of the local player.
     */
    @Override
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * Returns the name of the local player.
     *
     * @return the name of the local player
     */
    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Sets the name of the local player.
     *
     * @param playerName the name of the local player
     */
    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Returns the IP address of the server.
     *
     * @return the IP address of the server
     */
    @Override
    public String getServerIP() {
        return this.serverIP;
    }

    /**
     * Sets the IP address of the server.
     *
     * @param serverIP the IP address of the server
     */
    @Override
    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    /**
     * Returns the TCP port of the server.
     *
     * @return the TCP port of the server
     */
    @Override
    public int getServerPort() {
        return this.serverPort;
    }

    /**
     * Sets the TCP port of the server
     *
     * @param serverPort the TCP port of the server
     */
    @Override
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Makes a network connection to the server.
     */
    @Override
    public void connect() {
        try {
            this.sock = new Socket(this.serverIP, this.serverPort);
            System.out.println("Has established connection to "+this.serverIP+":"+this.serverPort);
            this.oos = new ObjectOutputStream(this.sock.getOutputStream());

            Thread t = new Thread(new ServerHandler(this.sock));
            t.start();
        } catch (IOException e) {
            // Handle exceptions, such as failure to establish a connection
            e.printStackTrace();
        }
    }


    /**
     * Parses the specified message received from the server.
     *
     * @param message the specified message received from the server
     */
    @Override
    public void parseMessage(GameMessage message) {
        switch (message.getType()){
            case CardGameMessage.PLAYER_LIST:
                //playerID: the playerID of the local player
                //data: a reference to a regular array of strings specifying the names of the players. A null value in the array means that particular player does not exist.

                //print "successful connected to server..." to msgArea
                this.gui.printMsg("Connected to server at "+this.serverPort+":"+this.serverIP);

                //set the playerID of the local player
                this.playerID = message.getPlayerID();

                //update the name of the players in playerList
                try{
                    String[] playerNames = (String[]) message.getData();
                    if(playerNames.length > 1){
                        for(int playerIdx = 0; playerIdx < playerNames.length; playerIdx++){
                            if(playerNames[playerIdx] != null && playerIdx != this.playerID){ //the client will receive this message only when he/she first join the game, thus he/she will only receive playerList that contain players that joined before him/her
                                //System.out.println("PlayerID:"+playerIdx+", Player name:"+playerNames[playerIdx]);
                                //this.game.getPlayerList().add(playerIdx, new CardGamePlayer(this.playerName));
                                this.game.getPlayerList().get(playerIdx).setName(playerNames[playerIdx]);
                            }
                        }
                    }
                } catch (Exception e){
                    System.out.println("Error when updating player names");
                }

                //set the player name of the local player and send CardGameMessage.JOIN to the server
                this.askPlayerName();

                break;
            case CardGameMessage.JOIN:
                //playerID: the playerID of the new player
                //data: a reference to a string specifying the name of this new player
                if(message.getPlayerID() == this.playerID){
                    try{
                        //If the playerID is identical to the local player, the client should send a message of type READY, with playerID and data being -1 and null, respectively
                        this.oos.writeObject(new CardGameMessage(CardGameMessage.READY, -1, null));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                } else {
                    //add a new player to the player list by updating his/her name
                    String playerName = (String)message.getData();
                    this.game.getPlayerList().get(message.getPlayerID()).setName(playerName);
                    this.gui.printMsg(playerName+" joins the game.");
                }
                break;
            case CardGameMessage.FULL:
                //playerID: -1
                //data: null
                this.gui.printMsg("The server is full, you cannot join the game, restart the game.");
                break;
            case CardGameMessage.QUIT:
                //playerID:the playerID of the player who leaves the game
                //data: a reference to a string representing the IP address and TCP port of this player
                String leftPlayerName = this.game.getPlayerList().get(message.getPlayerID()).getName();
                this.game.getPlayerList().get(message.getPlayerID()).setName("");
                this.gui.printMsg(leftPlayerName+" leaves the game room");
                this.gui.disable();
                this.sendMessage(new CardGameMessage(CardGameMessage.READY, -1, null));
                break;
            case CardGameMessage.READY:
                //playerID: the playerID (i.e., index) of the player who is ready
                //data: is null which can simply be ignored
                try{
                    this.gui.printMsg(game.getPlayerList().get(message.getPlayerID()).getName()+" is ready.");
                } catch (Exception e){
                    System.out.println("Error when printing msg to gui");
                }

                break;
            case CardGameMessage.START:
                //playerID: -1, which can be simply ignored
                //data: a reference to a (shuffled) BigTwoDeck object
                BigTwoDeck deck = (BigTwoDeck) message.getData();
                this.gui.clearMsgArea();
                this.gui.printMsg("All players are ready. Game starts.");
                this.game.start(deck);
                break;
            case CardGameMessage.MOVE:
                //playerID: the playerID (i.e., index) of the player who makes the move
                //data: a reference to a regular array of integers specifying the indices of the cards selected by the player.
                if(message.getData() == null){
                    this.game.makeMove(message.getPlayerID(), null);
                } else {
                    int[] cardIndices = (int[]) message.getData();
                    this.game.makeMove(message.getPlayerID(), cardIndices);
                }
                break;
            case CardGameMessage.MSG:
                //playerID: the playerID (i.e., index) of the player who sends out the chat message
                //data: a formatted string including the player’s name, his/her IP address and TCP port, and the original chat message
                this.gui.printChatMsg((String)message.getData());
                break;
        }
    }

    /**
     * Sends the specified message to the server.
     *
     * @param message the specified message to be sent the server
     */
    @Override
    public void sendMessage(GameMessage message) {
        try{
            this.oos.writeObject(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //a class used to handle communication between server and the client
    private class ServerHandler implements Runnable{
        private Socket serverSocket; // socket connection to the client
        private ObjectInputStream oistream; // ObjectInputStream of the client

        public ServerHandler(Socket serverSocket){
            this.serverSocket = serverSocket;
            try {
                // creates an ObjectInputStream
                // and chains it to the InputStream of the client socket
                oistream = new ObjectInputStream(serverSocket.getInputStream());
            } catch (Exception ex) {
                System.out.println("Error in creating an ObjectInputStream for the client at "
                        + serverSocket.getRemoteSocketAddress());
                ex.printStackTrace();
            }
        }

        /**
         * Runs this operation.
         */
        @Override
        public void run() {
            CardGameMessage message;
            try {
                while((message = (CardGameMessage) oistream.readObject()) != null){
                    parseMessage(message);
                }
            } catch (Exception e){
                System.out.println("Error in receiving messages from server at " + serverSocket.getRemoteSocketAddress());
            }
        }
    }
}
