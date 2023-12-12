import java.awt.Color;
import java.lang.Math;

/**
 * a subclass of Shape
 * used to model standard triangles
 * @author marcussze
 */
public class Triangle extends Shape{
    /**
     * set the coordinates of the vertices of a standard triangle in its local coordinate system
     * @param d the distance from the center of the triangle to any of its vertices
     */
    @Override
    public void setVertices(double d) {
        xLocal = new double[]{d, (-d)*Math.cos(Math.PI/3), (-d)*Math.cos(Math.PI/3)};
        yLocal = new double[]{0, (-d)*Math.sin(Math.PI/3), d*Math.sin(Math.PI/3)};
    }
}
