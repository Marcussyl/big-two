import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanelTester {
    public static void main(String[] args){
        ArrayList<CardGamePlayer> playerList = new ArrayList<>();
        Deck deck = new Deck();

        //add 4 players tp the playerList
        for (int i = 0; i < 4; i++){
            CardGamePlayer player = new CardGamePlayer();
            playerList.add(player);
        }

        //Distribute the cards to the players
        for (int i = 0; i < deck.size(); i++){
            playerList.get(i%4).addCard(deck.getCard(i));
        }

        //initialise frame
        JFrame frame = new JFrame("PlayerPanel Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color darkGreen = new Color(30, 160, 100);
        frame.getContentPane().setBackground(darkGreen);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //add playerPanel into frame
        PlayerPanel playerPanel1 = new PlayerPanel(playerList.get(0), true);
        PlayerPanel playerPanel2 = new PlayerPanel(playerList.get(1), true);
        PlayerPanel playerPanel3 = new PlayerPanel(playerList.get(2), true);
        PlayerPanel playerPanel4 = new PlayerPanel(playerList.get(3), true);
        mainPanel.add(playerPanel1);
        mainPanel.add(playerPanel2);
        mainPanel.add(playerPanel3);
        mainPanel.add(playerPanel4);

        frame.add(mainPanel);
        //frame.setSize(500,500);
        frame.pack();
        frame.setVisible(true);
    }
}
