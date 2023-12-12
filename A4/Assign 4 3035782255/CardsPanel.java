import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardsPanel extends JPanel {
    private CardList cards;
    private boolean showFront;
    private StringBuilder imgPath = new StringBuilder("./cards/");
    public final static int overlap = 15;

    public CardsPanel(CardList cards, boolean showFront ) {
        this.cards = cards;
        this.showFront = showFront;
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        int startX = 0;
        if(showFront){
            System.out.println("cards front printing");
            for(int i = 0; i < cards.size(); i++){
                imgPath.append(cards.getCard(i).getRank()+1);
                switch(cards.getCard(i).getSuit()){
                    case 0: imgPath.append("d"); break;
                    case 1: imgPath.append("c"); break;
                    case 2: imgPath.append("h"); break;
                    case 3: imgPath.append("s"); break;
                }
                imgPath.append(".gif");
                //System.out.println("imgPath:"+imgPath);
                Image image = new ImageIcon(imgPath.toString()).getImage();
                g.drawImage(image, startX + overlap * i, 0, this);
                imgPath.setLength(0);
                imgPath.append("./cards/");
            }
            imgPath.setLength(0);
            imgPath.append("./cards/");
        } else{
            System.out.println("card back printing");
            imgPath.append("b.gif");
            Image image = new ImageIcon(imgPath.toString()).getImage();
            //System.out.println("imgPath:"+imgPath);
            for(int i = 0; i < cards.size(); i++){
                g.drawImage(image, startX + overlap * i, 0, this);
            }
            imgPath.setLength(0);
            imgPath.append("./cards/");
        }
    }

    public Dimension getPreferredSize(){
        Image imageSample = new ImageIcon("./cards/2c.gif").getImage();
        return new Dimension(imageSample.getWidth(null)+overlap*(cards.size()-1), imageSample.getHeight(null));
    }

}
