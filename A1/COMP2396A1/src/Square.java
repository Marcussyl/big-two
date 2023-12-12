/**
 * a subclass of Shape
 * used to model a standard Square
 * @author marcussze
 */
public class Square extends Shape{
    /**
     * set the coordinates of the vertices of a standard square in its local coordinate system
     * @param d half-the-length of a side of the square
     */
    @Override
    public void setVertices(double d) {
        xLocal = new double[]{d,d,-d,-d};
        yLocal = new double[]{d,-d,-d,d};
    }
}
