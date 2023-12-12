import java.awt.Color;
import java.lang.*;
import java.util.Arrays;

/**
 * a tester class used to test whether the Triangle class is implemented correctly
 * @author marcussze
 */
public class TriangleTestDrive {
    /**
     * a method used to initialize the Triangle class and test if its fields and methods are implemented correctly
     * @param args the command-line arguments, not used in this tester class
     */
    public static void main(String[] args){
        //Initialize the Shape class
        Shape triangle = new Triangle();
        System.out.println("Triangle created");
        System.out.println();

        //Test the color field
        triangle.color = new Color(23,45,56);
        System.out.println("color is set to rgb(" + triangle.color.getRed() + "," + triangle.color.getGreen() + "," + triangle.color.getBlue() + ")");
        System.out.println();

        //Test the filled field
        triangle.filled = true;
        System.out.println("Triangle filled");
        System.out.println();

        //Test the theta field
        triangle.theta = 23;
        System.out.println("theta is set to " + triangle.theta);
        System.out.println();

        //Test the xc field
        triangle.xc = 40;
        System.out.println("xc is set to " + triangle.xc + " (in canvas coordinate system)");
        System.out.println();

        //Test the yc field
        triangle.yc = 50;
        System.out.println("yc is set to " + triangle.yc + " (in canvas coordinate system)");
        System.out.println();

        //Test the setVertices() method
        triangle.setVertices(100);
        System.out.println("Local coordinates of the vertices are set with the distance from the center of the triangle to any of its vertices equal to 100");
        System.out.println("The x-coordinates are " + Arrays.toString(triangle.xLocal));
        System.out.println("The y-coordinates are " + Arrays.toString(triangle.yLocal));
        System.out.println();

        //Test translate() method
        System.out.println("Triangle is translated along x-axis by 2.34 and y-axis by 5.67");
        System.out.println("The old value of xc and yc are " + triangle.xc + " and " + triangle.yc + " respectively");
        triangle.translate(2.34, 5.67);
        System.out.println("The new value of xc and yc are " + triangle.xc + " and " + triangle.yc + " respectively");
        System.out.println();

        //Test rotate() method
        System.out.println("Triangle is rotated by 23.4 radians");
        System.out.println("The old value of theta is " + triangle.theta);
        triangle.rotate(23.4);
        System.out.println("The new value of theta is " + triangle.theta);
        System.out.println();

        //Test getX() method
        int[] xCanvas;
        xCanvas = triangle.getX();
        System.out.println("getX() is called");
        System.out.println("The x-coordinates (in canvas coordinate system) of the vertices are " + Arrays.toString(xCanvas));
        System.out.println();

        //Test getY() method
        int[] yCanvas;
        yCanvas = triangle.getY();
        System.out.println("getY() is called");
        System.out.println("The y-coordinates (in canvas coordinate system) of the vertices are " + Arrays.toString(yCanvas));
    }
}
