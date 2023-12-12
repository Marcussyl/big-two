import java.awt.Color;
import java.lang.*;
import java.util.Arrays;

/**
 * a tester class used to test whether the Circle class is implemented correctly
 * @author marcussze
 */
public class CircleTestDrive {
    /**
     * a method used to initialize the Circle class and test if its fields and methods are implemented correctly
     * @param args the command-line arguments, not used in this tester class
     */
    public static void main(String[] args){
        //Initialize the Shape class
        Circle circle = new Circle();
        System.out.println("Circle created");
        System.out.println();

        //Test the color field
        circle.color = new Color(23,45,56);
        System.out.println("color is set to rgb(" + circle.color.getRed() + "," + circle.color.getGreen() + "," + circle.color.getBlue() + ")");
        System.out.println();

        //Test the filled field
        circle.filled = true;
        System.out.println("Circle filled");
        System.out.println();

        //Test the theta field
        circle.theta = 23;
        System.out.println("theta is set to " + circle.theta);
        System.out.println();

        //Test the xc field
        circle.xc = 40;
        System.out.println("xc is set to " + circle.xc + " (in canvas coordinate system)");
        System.out.println();

        //Test the yc field
        circle.yc = 50;
        System.out.println("yc is set to " + circle.yc + " (in canvas coordinate system)");
        System.out.println();

        //Test the setVertices() method
        circle.setVertices(100);
        System.out.println("Local coordinates are set with radius equal to 100");
        System.out.println("The x-coordinates are " + Arrays.toString(circle.xLocal));
        System.out.println("The y-coordinates are " + Arrays.toString(circle.yLocal));
        System.out.println();

        //Test translate() method
        System.out.println("Circle is translated along x-axis by 2.34 and y-axis by 5.67");
        System.out.println("The old value of xc and yc are " + circle.xc + " and " + circle.yc + " respectively");
        circle.translate(2.34, 5.67);
        System.out.println("The new value of xc and yc are " + circle.xc + " and " + circle.yc + " respectively");
        System.out.println();

        //Test rotate() method
        System.out.println("Circle is rotated by 23.4 radians");
        System.out.println("The old value of theta is " + circle.theta);
        circle.rotate(23.4);
        System.out.println("The new value of theta is " + circle.theta);
        System.out.println();

        //Test getX() method
        int[] xCanvas;
        xCanvas = circle.getX();
        System.out.println("getX() is called");
        System.out.println("The x-coordinates (in canvas coordinate system) of the vertices are " + Arrays.toString(xCanvas));
        System.out.println();

        //Test getY() method
        int[] yCanvas;
        yCanvas = circle.getY();
        System.out.println("getY() is called");
        System.out.println("The y-coordinates (in canvas coordinate system) of the vertices are " + Arrays.toString(yCanvas));
    }
}
