import java.util.Arrays;

public class RegularPolygonTester {
    public static void main(String[] args){
        //Test the constructor with parameters n, r
        RegularPolygon regularPolygon_two = new RegularPolygon(4, 10);
        System.out.println("The number of sides after initialization with parameters (n,r):"+regularPolygon_two.getNumOfSides());
        System.out.println("Radius after initialization with parameters (n,r):"+regularPolygon_two.getRadius());
        System.out.println("The local x-coordinates after initialization with parameters (n,r):"+ Arrays.toString(regularPolygon_two.getXLocal()));
        System.out.println("The local y-coordinates after initialization with parameters (n,r):"+ Arrays.toString(regularPolygon_two.getYLocal()));
        System.out.println();

        //Test the constructor with parameters n
        RegularPolygon regularPolygon_one = new RegularPolygon(6);
        System.out.println("The number of sides after initialization with parameters n:"+regularPolygon_one.getNumOfSides());
        System.out.println("Radius after initialization with parameters n:"+regularPolygon_one.getRadius());
        System.out.println("The local x-coordinates after initialization with parameters n:"+ Arrays.toString(regularPolygon_one.getXLocal()));
        System.out.println("The local y-coordinates after initialization with parameters n:"+ Arrays.toString(regularPolygon_one.getYLocal()));
        System.out.println();

        //Test the constructor without parameters
        RegularPolygon regularPolygon = new RegularPolygon();
        System.out.println("The number of sides after initialization without parameters:"+regularPolygon.getNumOfSides());
        System.out.println("Radius after initialization without parameters:"+regularPolygon.getRadius());
        System.out.println("The local x-coordinates after initialization without parameters:"+ Arrays.toString(regularPolygon.getXLocal()));
        System.out.println("The local y-coordinates after initialization without parameters:"+ Arrays.toString(regularPolygon.getYLocal()));
        System.out.println();

        //Test the setNumOfSides() and setVertices() method
        regularPolygon.setNumOfSides(9);
        System.out.println("The number of sides after calling setNumOfSides():"+regularPolygon.getNumOfSides());
        System.out.println();

        //Test the setRadius() and setVertices() method
        regularPolygon.setRadius(Math.PI/3);
        System.out.println("The radius after calling setRadius():"+regularPolygon.getRadius());
        System.out.println();

        //Test the contains() method
        boolean contained = regularPolygon_two.contains(15,6);
        System.out.println("The point is contained inside the polygon?:"+contained);
    }
}
