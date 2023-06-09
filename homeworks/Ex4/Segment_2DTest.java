package geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment_2DTest {

    @Test
    void contains() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,5);
        Segment_2D s = new Segment_2D(c1, c2);
        Point_2D ch = new Point_2D( 0,4);
        assertTrue(s.contains(ch));
    }

    @Test
    void area() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,5);
        Segment_2D s = new Segment_2D(c1, c2);
        assertEquals(s.area(), 0);
    }

    @Test
    void perimeter() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,5);
        Segment_2D s = new Segment_2D(c1, c2);
        assertEquals(s.perimeter(), 10);
    }

    @Test
    void translate() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,5);
        Segment_2D s = new Segment_2D(c1, c2);
        s.translate(new Point_2D(1,1));
        Point_2D c11 = new Point_2D(1,1);
        Point_2D c22 =  new Point_2D(1,6);
        Point_2D [] expected = s.getAllPoints();
        assertEquals(c11, expected[0]);
        assertEquals(c22, expected[1]);
    }

    @Test
    void copy() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,5);
        Segment_2D s = new Segment_2D(c1, c2);
        s.copy();
        Point_2D c11 = new Point_2D(0,0);
        Point_2D c22 = new Point_2D(0,5);
        assertEquals(c11, c1);
        assertEquals(c22, c2);
    }

    @Test
    void scale() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,6);
        Segment_2D s = new Segment_2D(c1, c2);
        s.scale(new Point_2D(0,3), 1);
        Point_2D c11 = new Point_2D(0,0);
        Point_2D c22 = new Point_2D(0,6);
        assertEquals(c11, c1);
        assertEquals(c22, c2);
    }

    @Test
    void rotate() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,6);
        Segment_2D s = new Segment_2D(c1, c2);
        Point_2D cen = new Point_2D(0,3);
        s.rotate(cen, 90);
        Point_2D c11 = new Point_2D(3,3);
        Point_2D c22 = new Point_2D(-3,3.0000000000000004);
        System.out.println(s.toString());
        Point_2D [] expected = s.getAllPoints();
        assertEquals(c11, expected[0]);
        assertEquals(c22, expected[1]);
    }
}
