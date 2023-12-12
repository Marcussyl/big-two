import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private final Color lightGreen = new Color(13,217,37);
    private final JLabel playerLabel;
    private JPanel imgPanel;
    private AvatarPanel avatarPanel;
    private CardsPanel cardsPanel;
    private boolean showFront;
    public PlayerPanel(CardGamePlayer player, boolean showFront){
        //System.out.println("cardsInHand in "+player.getName()+":"+player.getCardsInHand().toString());
        System.out.println("playerPanel is initialised");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setAlignmentX(Component.LEFT_ALIGNMENT);
        setBorder(new MatteBorder(0, 0, 1, 0,Color.BLACK)); //set the border at the bottom to black
        this.showFront = showFront;

        playerLabel = new JLabel(player.getName());
        Border border = BorderFactory.createLineBorder(Color.red, 2);
        playerLabel.setBorder(border);
        playerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        cardsPanel = new CardsPanel(player.getCardsInHand(), showFront); //showFront might not update accordingly since it's not padded by reference
        cardsPanel.setBorder(border);
        cardsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        //cardsPanel.setOpaque(false);
        avatarPanel = new AvatarPanel();
        avatarPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        avatarPanel.setBorder(border);

        imgPanel = new JPanel();
        imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.X_AXIS));
        imgPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        imgPanel.setBorder(border);
        imgPanel.setOpaque(false);

        imgPanel.add(avatarPanel);
        imgPanel.add(cardsPanel);

        add(playerLabel);
        add(imgPanel);
    }

    public void paintComponent(Graphics g){
        if(showFront){//set the text color to green for active player
            playerLabel.setForeground(lightGreen);
        } else{
            playerLabel.setForeground(Color.black);
        }
    }
}
