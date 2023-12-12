import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FooterPanelTester extends JPanel {
    public static void main(String[] args){
        Border border = BorderFactory.createLineBorder(Color.red, 2);

        JButton playButton = new JButton("Play");
        JButton passButton = new JButton("Pass");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        passButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(playButton);
        buttonPanel.add(passButton);
        buttonPanel.setBorder(border);

        JPanel msgPanel = new JPanel();
        JTextField chatInput = new JTextField();
        JLabel msgLabel = new JLabel("Message: ");
        msgPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        msgPanel.add(msgLabel);
        msgPanel.add(chatInput);
        msgPanel.setBorder(border);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(buttonPanel);
        mainPanel.add(msgPanel);

        JFrame frame = new JFrame("FooterPanel Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
