import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class TablePanel extends JPanel {
    private JLabel playerLabel;
    private CardsPanel lastHandOnTablePanel;
    private Border border = BorderFactory.createLineBorder(Color.red, 2);
    public TablePanel(ArrayList<Hand> handsOnTable){
        //System.out.println("tablePanel is initialised");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBorder(border);

        if(!handsOnTable.isEmpty()){
            System.out.println("there is hand on table, printing out ...");
            Hand lastHandOnTable = handsOnTable.get(handsOnTable.size()-1);

            this.playerLabel = new JLabel(lastHandOnTable.getPlayer().getName());
            playerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            this.lastHandOnTablePanel = new CardsPanel(lastHandOnTable, true);
            lastHandOnTablePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            add(playerLabel);
            add(lastHandOnTablePanel);
        }else {
            Image imageSample = new ImageIcon("./cards/2c.gif").getImage();
            this.playerLabel = new JLabel("Empty");
            playerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerLabel.setBorder(border);
            JPanel emptyPanel = new JPanel();
            emptyPanel.setOpaque(false);
            emptyPanel.setBorder(border);
            emptyPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            emptyPanel.setSize(imageSample.getWidth(null), imageSample.getHeight(null));
            add(playerLabel);
            add(emptyPanel);
        }
    }

    public Dimension getPreferredSize(){
        Image imageSample = new ImageIcon("./cards/2c.gif").getImage();
        return new Dimension(imageSample.getWidth(null)+15*10, imageSample.getHeight(null));
    }
}
