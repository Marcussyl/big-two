import java.awt.*;
import java.util.Arrays;

public class ShapeTester {
    public static void main(String[] args){
        //Initialize the shape object
        Shape shape = new Shape();

        //Test the color field, setColor() and getColor() methods
        Color color = new Color(230,0,0);
        shape.setColor(color);
        Color colorGet = shape.getColor();
        System.out.println("Color of the shape is:"+colorGet);

        //Test the setFilled() and getFilled() methods
        shape.setFilled(true);
        boolean shapeFilled = shape.getFilled();
        System.out.println("Shape is filled?:"+shapeFilled);

        //Test the setTheta() and getTheta() methods
        shape.setTheta(Math.PI/3);
        double theta = shape.getTheta();
        System.out.println("The orientation of the shape:"+theta);

        //Test the setXc() and getXc() methods
        shape.setXc(23);
        double xc = shape.getXc();
        System.out.println("The x-coordinate of the center:"+xc);

        //Test the setYc() and getYc() methods
        shape.setYc(45);
        double yc = shape.getYc();
        System.out.println("The y-coordinate of the center:"+yc);

        //Test the setXLocal() and getXLocal() methods
        shape.setXLocal(new double[]{2,5,6,7});
        double[] xLocal = shape.getXLocal();
        System.out.println("The x-coordinates of the vertices:"+ Arrays.toString(xLocal));

        //Test the setYLocal() and getYLocal() methods
        shape.setYLocal(new double[]{4,9,0,1});
        double[] yLocal = shape.getYLocal();
        System.out.println("The y-coordinates of the vertices:"+ Arrays.toString(yLocal));

        //Test the translate() method
        shape.translate(12,23);
        double newXc = shape.getXc();
        double newYc = shape.getYc();
        System.out.println("The coordinates of the center after translation x:"+newXc+" ,y:"+newYc);

        //Test the rotate() method
        shape.rotate(Math.PI/2);
        double newTheta = shape.getTheta();
        System.out.println("The new theta after rotation:"+newTheta);

        //Test the getX() method
        int[] xCanvas = shape.getX();
        System.out.println("The x-coordinates of the vertices in canvas coordinate system:"+Arrays.toString(xCanvas));

        //Test the getY() method
        int[] yCanvas = shape.getY();
        System.out.println("The y-coordinates of the vertices in canvas coordinate system:"+Arrays.toString(yCanvas));
    }
}
