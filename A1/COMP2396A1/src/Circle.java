import java.awt.Color;
import java.lang.Math;

/**
 * a subclass of Shape
 * used to model standard circles
 * @author marcussze
 */
public class Circle extends Shape{
    /**
     * set the coordinates of the lower right and top left vertices of an axis-aligned bounding box of a standard circle in its local coordinate system
     * @param d the radius of the circle
     */
    @Override
    public void setVertices(double d) {
        xLocal = new double[]{-d,d};
        yLocal = new double[]{-d,d};
    }

    /**
     * get the x-coordinates of the lower right and top left vertices of an axis-aligned bounding box of the circle in canvas coordinate system (rounded to nearest integers)
     * @return an array of x-coordinates of the lower right and top left vertices of an axis-aligned bounding box of the circle
     */
    @Override
    public int[] getX(){
        int[] xCanvas = new int[xLocal.length];
        for(int i = 0; i < xLocal.length; i++){
            xCanvas[i] = (int)Math.round(xLocal[i] + xc);
        }
        return xCanvas;
    }

    /**
     * get the y-coordinates of the lower right and top left vertices of an axis-aligned bounding box of the circle in canvas coordinate system (rounded to nearest integers)
     * @return an array of y-coordinates of the lower right and top left vertices of an axis-aligned bounding box of the circle
     */
    @Override
    public int[] getY(){
        int[] yCanvas = new int[yLocal.length];
        for(int i = 0; i < yLocal.length; i++){
            yCanvas[i] = (int)Math.round(yLocal[i] + yc);
        }
        return yCanvas;
    }
}
