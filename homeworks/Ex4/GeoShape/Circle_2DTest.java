package geo;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Circle_2DTest {
    public static Point_2D origin = new Point_2D(0,0);
    public static Circle_2D original = new Circle_2D(origin, 5);
    @org.junit.jupiter.api.Test
    void testToString() {
        //"Center: " + this._center+ "  " + "Radius: " + this._radius
        String s = "[" + "Center: " + original.getCenter()+ "  " + "Radius: " + original.getRadius() + "]";
        String e = original.toString();
        assertEquals(e,s);
    }
    @org.junit.jupiter.api.Test
    void contains() {
        Point_2D s1 = new Point_2D(5,5);
        Point_2D s2 = new Point_2D(1,1);
        Point_2D s3 = new Point_2D(0,10);
        assertTrue(!original.contains(s1));
        assertFalse(original.contains(s3));
        assertTrue(original.contains(origin));
        assertFalse(original.contains(s3));
    }

    @org.junit.jupiter.api.Test
    void area() {
        double r = Math.PI * 25;
        assertEquals(original.area(), r);
    }

    @org.junit.jupiter.api.Test
    void perimeter() {
        double r = 2 * Math.PI * 5;
        assertEquals(original.perimeter(), r);
    }

    @org.junit.jupiter.api.Test
    void translate() {
        original.translate(new Point_2D(0,5));
        Circle_2D s1 = new Circle_2D(new Point_2D(0,5), 5);
        assertEquals(s1.getRadius(), original.getRadius());
        assertEquals(s1.getCenter(), original.getCenter());
    }

    @org.junit.jupiter.api.Test
    void copy() {
        Circle_2D s1 = new Circle_2D(origin, 5);
        Circle_2D s2 = new Circle_2D(s1.getCenter(), s1.getRadius());
        GeoShape g = new Circle_2D((Circle_2D) s1.copy());
        g.translate(new Point_2D(10,0));
        assertEquals(s1.getCenter().distance(((Circle_2D) g).getCenter()),10);
    }

    @org.junit.jupiter.api.Test
    void scale() {
    Circle_2D s1 = new Circle_2D(origin, 100);
    GeoShape g = new Circle_2D((Circle_2D) s1.copy());
    g.scale(origin, 1);
    s1.scale(origin, 1);
    double r2 = ((Circle_2D) g).getRadius();
    double r = 100;
    assertEquals(r, r2);
    }

    @org.junit.jupiter.api.Test
    void rotate() {
    }
}
