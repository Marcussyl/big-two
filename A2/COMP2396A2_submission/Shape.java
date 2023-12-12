import java.awt.*;

/**
 * A class used to model general shapes
 * @author marcussze
 */
public class Shape {
    //a Color object specifying the color of the shape
    private Color color;

    //a boolean value specifying whether the shape is filled or not filled
    private boolean filled;

    //a double value specifying the orientation (in radians) of the shape (with respect to its center) in the canvas coordinate system
    private double theta;

    //a double value specifying the x-coordinate of the center of the shape in the canvas coordinate system
    private double xc;

    //a double value specifying the y-coordinate of the center of the shape in the canvas coordinate system
    private double yc;

    //an array of double values specifying the x-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
    private double[] xLocal;

    //an array of double values specifying the y-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
    private double[] yLocal;

    /**
     * A method for retrieving the color of the shape
     * @return the color of the shape
     */
    public Color getColor(){
        return color;
    }

    /**
     * A method for retrieving the fill-type of the shape
     * @return the fill-type of the shape
     */
    public boolean getFilled(){
        return filled;
    }

    /**
     * A method for retrieving the orientation (in radians) of the shape
     * (with respect to its center) in the canvas coordinate system.
     * @return the orientation of the shape with respect to its center in the canvas coordinate system
     */
    public double getTheta(){
        return theta;
    }

    /**
     * A method for retrieving the x-coordinate of the center of the shape in
     * the canvas coordinate system.
     * @return the x-coordinate of the center of the shape in the canvas coordinate system
     */
    public double getXc(){
        return xc;
    }

    /**
     * A method for retrieving the y-coordinate of the center of the shape in
     * the canvas coordinate system.
     * @return the y-coordinate of the center of the shape in the canvas coordinate system
     */
    public double getYc(){
        return yc;
    }

    /**
     * A method for retrieving the x-coordinates of the vertices (in
     * counter-clockwise order) of the shape in its local coordinate system.
     * @return the x-coordinate of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     */
    public double[] getXLocal(){
        return xLocal;
    }

    /**
     * A method for retrieving the y-coordinates of the vertices (in
     * counter-clockwise order) of the shape in its local coordinate system.
     * @return the y-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     */
    public double[] getYLocal(){
        return yLocal;
    }

    /**
     * A method for setting the color of the shape.
     * @param color the color of the shape
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * A method for setting the fill-type of the shape.
     * @param filled the filled-type of the shape
     */
    public void setFilled(boolean filled){
        this.filled = filled;
    }

    /**
     * A method for setting the orientation of the shape.
     * @param theta the orientation of the shape
     */
    public void setTheta(double theta){
        this.theta = theta;
    }

    /**
     * A method for setting the x-coordinate of the center of the
     * shape in the canvas coordinate system.
     * @param xc the x-coordinate of the center of the shape in the canvas coordinate system
     */
    public void setXc(double xc){
        this.xc = xc;
    }

    /**
     * A method for setting the y-coordinate of the center of the
     * shape in the canvas coordinate system.
     * @param yc the y-coordinate of the center of the shape in the canvas coordinate system
     */
    public void setYc(double yc){
        this.yc = yc;
    }

    /**
     * A method for setting the x-coordinates of the
     * vertices (in counter-clockwise order) of the shape in its local coordinate system.
     * @param xLocal the x-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     */
    public void setXLocal(double[] xLocal){
        this.xLocal = xLocal;
    }

    /**
     * A method for setting the y-coordinates of the
     * vertices (in counter-clockwise order) of the shape in its local coordinate system.
     * @param yLocal the y-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system
     */
    public void setYLocal(double[] yLocal){
        this.yLocal = yLocal;
    }

    /**
     * A method for translating the center of the
     * shape by dx and dy, respectively, along the x and y directions of the canvas coordinate system.
     * @param dx x-coordinate by which the center of the shape will be translated along the x-asis
     * @param dy y-coordinate by which the center of the shape will be translated along the y-axis
     */
    public void translate(double dx, double dy){
        xc += dx;
        yc += dy;
    }

    /**
     * A method for rotating the shape about its center by an angle of dt (in radians).
     * @param dt the angle (in radians) by which the shape will be rotated about its center
     */
    public void rotate(double dt){
        theta += dt;
    }

    /**
     * A method for retrieving the x-coordinates of the vertices (in counterclockwise order) of
     * the shape in the canvas coordinate system.
     * @return the x-coordinates of the vertices (in counterclockwise order) of the shape in the canvas coordinate system
     */
    public int[] getX(){
        int[] xCanvas = new int[xLocal.length];
        for (int i = 0; i < xLocal.length; i++){
            xCanvas[i] = (int)Math.round(xLocal[i]*Math.cos(theta) - yLocal[i]*Math.sin(theta) + xc);
        }
        return xCanvas;
    }

    /**
     * A method for retrieving the y-coordinates of the vertices (in counterclockwise order) of
     * the shape in the canvas coordinate system.
     * @return the y-coordinates of the vertices (in counterclockwise order) of the shape in the canvas coordinate system
     */
    public int[] getY(){
        int[] yCanvas = new int[yLocal.length];
        for (int i = 0; i < yLocal.length; i++){
            yCanvas[i] = (int)Math.round(xLocal[i]*Math.sin(theta) - yLocal[i]*Math.cos(theta) + yc);
        }
        return yCanvas;
    }
}
