import java.util.Arrays;

/**
 * A class used to model regular n-sided polygons.
 * @author marcussze
 */
public class RegularPolygon extends Shape{
    /**
     * A constructor for building a regular n-sided polygon with a radius of r.
     * @param n the number of sides of the polygon, if its value is less than 3, the number of sides will be set to 3
     * @param r the radius, if its value is less than 0, it will be set to 0
     */
    public RegularPolygon(int n, double r){
        numOfSides = Math.max(3, n);
        radius = Math.max(0, r);
        this.setVertices();
    }

    /**
     * A constructor for building a regular n-sided polygon with a radius of 1.0.
     * @param n the number of sides of the polygon, if its value is less than 3, the number of sides will be set to 3
     */
    public RegularPolygon(int n){
        numOfSides = Math.max(3, n);
        radius = 1;
        this.setVertices();
    }

    /**
     * A constructor for building a regular 3-sided polygon with a radius of 1.0.
     */
    public RegularPolygon(){
        numOfSides = 3;
        radius = 1;
        this.setVertices();
    }

    //an integer value specifying the number of sides of the regular n-sided polygon
    private int numOfSides;

    //a double value specifying the radius of the regular n-sided polygon
    private double radius;

    /**
     * A method for retrieving the number of sides of the regular polygon.
     * @return the number of sides of the regular polygon
     */
    public int getNumOfSides(){
        return numOfSides;
    }

    /**
     * A method for retrieving the radius of the regular polygon.
     */
    public double getRadius(){
        return radius;
    }

    /**
     * A method for setting the number of sides of the regular n-sided polygon, this method will reset the local coordinates of the vertices of the regular n-sided polygon.
     */
    public void setNumOfSides(int n){
        numOfSides = Math.max(3, n);
        this.setVertices();
    }

    /**
     * A method for setting the radius of the regular n-sided polygon,
     * this method will reset the local coordinates of the vertices of the regular n-sided polygon.
     */
    public void setRadius(double r){
        radius = Math.max(0, r);
        this.setVertices();
    }

    /**
     * A method for setting the local coordinates of the vertices of the regular n-sided polygon based on its number of sides and radius.
     */
    private void setVertices(){
        double alpha;
        double theta = (2*Math.PI)/numOfSides;
        if(numOfSides%2==0)
        {
            alpha = Math.PI/numOfSides;
        }
        else {
            alpha = 0;
        }
        double [] xPolygonLocal = new double[numOfSides];
        double [] yPolygonLocal = new double[numOfSides];
        for(int i=0;i<numOfSides;i++)
        {
            xPolygonLocal[i] = radius*Math.cos(alpha - i*theta);
            yPolygonLocal[i] = radius*Math.sin(alpha - i*theta);
        }
        setXLocal(xPolygonLocal);
        setYLocal(yPolygonLocal);
    }

    /**
     * A method for determining if a point (x, y) in the canvas coordinate system is contained by the regular n-sided polygon,
     * a point is considered to be contained by a polygon if it lies either completely inside the polygon, or on any of the sides or vertices of the polygon.
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @return true if the point is contained by the regular n-sided polygon, otherwise false
     */
    public boolean contains(double x, double y){
        double xLocalToCheck = (x-getXc()) * Math.cos(-getTheta()) - (y-getYc()) * Math.sin(-getTheta());
        double yLocalToCheck = (y-getYc()) * Math.sin(-getTheta()) - (y-getYc()) * Math.cos(-getTheta());
        double [] xLocal = getXLocal();
        double minXLocal = Arrays.stream(xLocal).min().getAsDouble();
        double newXLocalToCheck = xLocalToCheck;
        double theta = (2*Math.PI)/numOfSides;
        for(int i = 0; i < numOfSides; i++){
            if(newXLocalToCheck < minXLocal){
                return false;
            }
            newXLocalToCheck = xLocalToCheck * Math.cos(i*theta) - yLocalToCheck * Math.sin(i*theta);
        }
        return true;
    }
}
