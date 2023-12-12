import java.awt.Color;
import java.lang.*;
import java.util.Arrays;

/**
 * a tester class used to test whether the Shape class is implemented correctly
 * @author marcussze
 */
public class ShapeTestDrive {
    /**
     * a method used to initialize the Shape class and test if its fields and methods are implemented correctly
     * @param args the command-line arguments, not used in this tester class
     */
    public static void main(String[] args){
        //Initialize the Shape class
        Shape s = new Shape();
        System.out.println("Shape created");
        System.out.println();

        //Test the color field
        s.color = new Color(23,45,56);
        System.out.println("color is set to rgb(" + s.color.getRed() + "," + s.color.getGreen() + "," + s.color.getBlue() + ")");
        System.out.println();

        //Test the filled field
        s.filled = true;
        System.out.println("Shape filled");
        System.out.println();

        //Test the theta field
        s.theta = 23;
        System.out.println("theta is set to " + s.theta);
        System.out.println();

        //Test the xc field
        s.xc = 40;
        System.out.println("xc is set to " + s.xc + " (in canvas coordinate system)");
        System.out.println();

        //Test the yc field
        s.yc = 50;
        System.out.println("yc is set to " + s.yc + " (in canvas coordinate system)");
        System.out.println();

        //Test the xLocal field
        s.xLocal = new double[]{2.34, 4.56};
        System.out.println("xLocal is set to arbitrary coordinates " + Arrays.toString(s.xLocal));
        System.out.println();

        //Test the yLocal field
        s.yLocal = new double[]{5.67,6.78};
        System.out.println("yLocal is set to arbitrary coordinates " + Arrays.toString(s.yLocal));
        System.out.println();

        //Test the setVertices() method
        s.setVertices(100);
        System.out.println("setVertices() is called with a arbitrary value, since this is a dummy method, there is no change to xLocal and yLocal");
        System.out.println();

        //Test translate() method
        System.out.println("Shape is translated along x-axis by 2.34 and y-axis by 5.67");
        System.out.println("The old value of xc and yc are " + s.xc + " and " + s.yc + " respectively");
        s.translate(2.34, 5.67);
        System.out.println("The new value of xc and yc are " + s.xc + " and " + s.yc + " respectively");
        System.out.println();

        //Test rotate() method
        System.out.println("Shape is rotated by 23.4 radians");
        System.out.println("The old value of theta is " + s.theta);
        s.rotate(23.4);
        System.out.println("The new value of theta is " + s.theta);
        System.out.println();

        //Test getX() method
        int[] xCanvas;
        xCanvas = s.getX();
        System.out.println("getX() is called");
        System.out.println("The x-coordinates (in canvas coordinate system) of the vertices are " + Arrays.toString(xCanvas));
        System.out.println();

        //Test getY() method
        int[] yCanvas;
        yCanvas = s.getY();
        System.out.println("getY() is called");
        System.out.println("The y-coordinates (in canvas coordinate system) of the vertices are " + Arrays.toString(yCanvas));
    }
}
