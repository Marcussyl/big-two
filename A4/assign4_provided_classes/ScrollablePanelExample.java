import javax.swing.*;
import java.awt.*;

public class ScrollablePanelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Scrollable JPanel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            // Create the first JPanel with a layout manager (e.g., GridLayout)
            JPanel panel1 = new JPanel(new GridLayout(10, 1));
            for (int i = 0; i < 20; i++) {
                panel1.add(new JLabel("Panel 1 - Label " + (i + 1)));
            }

            // Create the second JPanel with a layout manager (e.g., GridLayout)
            JPanel panel2 = new JPanel(new GridLayout(10, 1));
            for (int i = 0; i < 20; i++) {
                panel2.add(new JLabel("Panel 2 - Label " + (i + 1)));
            }

            // Create a JScrollPane for each panel
            JScrollPane scrollPane1 = new JScrollPane(panel1);
            JScrollPane scrollPane2 = new JScrollPane(panel2);

            // Set up the frame with BorderLayout
            frame.setLayout(new BorderLayout());

            // Add the scroll panes to the frame
            frame.add(scrollPane1, BorderLayout.WEST);
            frame.add(scrollPane2, BorderLayout.EAST);

            // Set visibility
            frame.setVisible(true);
        });
    }
}
