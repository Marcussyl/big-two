import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class AvatarPanel extends JPanel {
    private final String[] imgPaths = new String[] {"./avatars/batman_72.png","./avatars/flash_72.png","./avatars/green_lantern_72.png","./avatars/superman_72.png","./avatars/wonder_woman_72.png"};
    private final Random random = new Random();
    private final int randomIdx = random.nextInt(imgPaths.length);
    //private static int imgIdx = 0;

    public AvatarPanel(){
        Border border = BorderFactory.createLineBorder(Color.red, 2);
        setBorder(border);
        //System.out.println("avatarPanel is initialized");
        setOpaque(false);
        Color darkGreen = new Color(30, 160, 100);
        setBackground(darkGreen);

        //imgIdx = (imgIdx + 1) % imgPaths.length;
    }

    public void paintComponent(Graphics g){
        //System.out.println("randomIdx:"+randomIdx);
        //System.out.println("imgPath selected: "+imgPaths[randomIdx]);
        Image selectedAvatar = new ImageIcon(imgPaths[randomIdx]).getImage();
        g.drawImage(selectedAvatar, 0,0,this);
    }

    public Dimension getPreferredSize(){
        Image imageSample = new ImageIcon(imgPaths[0]).getImage();
        return new Dimension(imageSample.getWidth(null), imageSample.getHeight(null));
    }
}
