import java.awt.Color;
import java.lang.*;
import java.util.Arrays;

/**
 * a tester class used to test whether the Square class is implemented correctly
 * @author marcussze
 */
public class SquareTestDrive {
    /**
     * a method used to initialize the Square class and test if its fields and methods are implemented correctly
     * @param args the command-line arguments, not used in this tester class
     */
    public static void main(String[] args){
        //Initialize the Shape class
        Square square = new Square();
        System.out.println("Square created");
        System.out.println();

        //Test the color field
        square.color = new Color(23,45,56);
        System.out.println("color is set to rgb(" + square.color.getRed() + "," + square.color.getGreen() + "," + square.color.getBlue() + ")");
        System.out.println();

        //Test the filled field
        square.filled = true;
        System.out.println("Square filled");
        System.out.println();

        //Test the theta field
        square.theta = 23;
        System.out.println("theta is set to " + square.theta);
        System.out.println();

        //Test the xc field
        square.xc = 40;
        System.out.println("xc is set to " + square.xc + " (in canvas coordinate system)");
        System.out.println();

        //Test the yc field
        square.yc = 50;
        System.out.println("yc is set to " + square.yc + " (in canvas coordinate system)");
        System.out.println();

        //Test the setVertices() method
        square.setVertices(100);
        System.out.println("Local coordinates are set with half-side-length of the square equal to 100");
        System.out.println("The x-coordinates are " + Arrays.toString(square.xLocal));
        System.out.println("The y-coordinates are " + Arrays.toString(square.yLocal));
        System.out.println();

        //Test translate() method
        System.out.println("Square is translated along x-axis by 2.34 and y-axis by 5.67");
        System.out.println("The old value of xc and yc are " + square.xc + " and " + square.yc + " respectively");
        square.translate(2.34, 5.67);
        System.out.println("The new value of xc and yc are " + square.xc + " and " + square.yc + " respectively");
        System.out.println();

        //Test rotate() method
        System.out.println("Square is rotated by 23.4 radians");
        System.out.println("The old value of theta is " + square.theta);
        square.rotate(23.4);
        System.out.println("The new value of theta is " + square.theta);
        System.out.println();

        //Test getX() method
        int[] xCanvas;
        xCanvas = square.getX();
        System.out.println("getX() is called");
        System.out.println("The x-coordinates (in canvas coordinate system) of the vertices are " + Arrays.toString(xCanvas));
        System.out.println();

        //Test getY() method
        int[] yCanvas;
        yCanvas = square.getY();
        System.out.println("getY() is called");
        System.out.println("The y-coordinates (in canvas coordinate system) of the vertices are " + Arrays.toString(yCanvas));
    }
}
