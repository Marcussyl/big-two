import java.awt.*;
import java.lang.Math;

/**
 * a class used to model general shapes.
 * @author marcussze
 */
public class Shape {
    /**
     * a Color object specifying the color of the shape
     */
    public Color color;
    /**
     * a boolean value specifying whether the shape is filled or not filled
     */
    public boolean filled;
    /**
     * a double value specifying the orientation (in radians) of the shape (with respect to its center) in the canvas coordinate system
     */
    public double theta;
    /**
     * a double value specifying the x-coordinate of the center of the shape in the canvas coordinate system
     */
    public double xc;
    /**
     * a double value specifying the y-coordinate of the center of the shape in the canvas coordinate system
     */
    public double yc;
    /**
     * an array of double values specifying the x-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     */
    public double[] xLocal;
    /**
     * an array of double values specifying the y-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     */
    public double[] yLocal;

    /**
     * a method for setting the local coordinates of the vertices of a shape, this is a dummy method which will be overwritten by subclasses
     * @param d the value used to specify the dimension of the Shape
     */
    public void setVertices(double d){

    }

    /**
     * a method for translating the center of the shape by dx and dy, respectively, along the x and y directions of the canvas coordinate system
     * @param dx value that the center of the shape will translate along x-axis
     * @param dy value that the center of the shape will translate along y-axis
     */
    public void translate(double dx, double dy){
        xc += dx;
        yc += dy;
    }

    /**
     * a method for rotating the shape about its center by an angle of dt (in radians)
     * @param dt the angle (in radians) by which the shape will rotate
     */
    public void rotate(double dt) {
        theta += dt;
    }

    /**
     * a method for retrieving the x-coordinates of the vertices (in counterclockwise order) of the shape in the canvas coordinate system (rounded to nearest integers)
     * @return x-coordinates of the vertices of the shape in the canvas coordinate system
     */
    public int[] getX() {
        int[] xCanvas = new int[xLocal.length];
        for (int i = 0; i < xLocal.length; i++){
            xCanvas[i] = (int)Math.round(xLocal[i]*Math.cos(theta) - yLocal[i]*Math.sin(theta) + xc);
        }
        return xCanvas;
    }

    /**
     * a method for retrieving the y-coordinates of the vertices (in counterclockwise order) of the shape in the canvas coordinate system (rounded to nearest integers)
     * @return y-coordinates of the vertices of the shape in the canvas coordinate system
     */
    public int[] getY() {
        int[] yCanvas = new int[yLocal.length];
        for (int i = 0; i < yLocal.length; i++){
            yCanvas[i] = (int)Math.round(xLocal[i]*Math.sin(theta) - yLocal[i]*Math.cos(theta) + yc);
        }
        return yCanvas;
    }
}
