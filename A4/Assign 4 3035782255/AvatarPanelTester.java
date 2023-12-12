import javax.swing.*;
import java.awt.*;

public class AvatarPanelTester {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //initialise the frame
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            AvatarPanel avatarPanel = new AvatarPanel();
            //avatarPanel.setBackground(Color.blue);

            //add avatarPanel into frame
            frame.add(avatarPanel, BorderLayout.CENTER);

            //frame.pack();
            frame.setSize(500, 500);
            frame.setVisible(true);
        });
    }
}
