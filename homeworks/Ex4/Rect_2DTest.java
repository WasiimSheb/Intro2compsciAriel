package geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect_2DTest {

    @Test
    void contains() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(4,4);
        Point_2D c3 = new Point_2D(0,4);
        Point_2D c4 = new Point_2D(4,0);
        Rect_2D s = new Rect_2D(c1, c2, c3, c4);
        Point_2D ch = new Point_2D(10,10);
        Point_2D ch2 = new Point_2D(1,1);
        boolean ans = s.contains(ch);
        boolean ans2 = s.contains(ch2);
        assertFalse(ans);
        assertTrue(ans2);
    }

    @Test
    void area() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(4,4);
        Point_2D c3 = new Point_2D(0,4);
        Point_2D c4 = new Point_2D(4,0);
        Rect_2D s = new Rect_2D(c1, c2, c3, c4);
        double ans = 16;
        assertEquals(s.area(), 16);
    }

    @Test
    void perimeter() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(4,4);
        Point_2D c3 = new Point_2D(0,4);
        Point_2D c4 = new Point_2D(4,0);
        Rect_2D s = new Rect_2D(c1, c2, c3, c4);
        double ans = 16;
        assertEquals(s.perimeter(), 16);
    }

    @Test
    void translate() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(4,4);
        Point_2D c3 = new Point_2D(0,4);
        Point_2D c4 = new Point_2D(4,0);
        Rect_2D s = new Rect_2D(c1, c2, c3, c4);
        Point_2D move = new Point_2D(1,1);
        s.translate(move);
        Point_2D c11 = new Point_2D(1,1);
        Point_2D c22 = new Point_2D(5,5);
        Point_2D c33 = new Point_2D(1,5);
        Point_2D c44 = new Point_2D(5,1);
        assertEquals(c1, c11);
        assertEquals(c2, c22);
        assertEquals(c3, c33);
        assertEquals(c4, c44);
    }

    @Test
    void copy() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(4,4);
        Point_2D c3 = new Point_2D(0,4);
        Point_2D c4 = new Point_2D(4,0);
        Rect_2D s = new Rect_2D(c1, c2, c3, c4);
        s.copy();
        Point_2D c11 = new Point_2D(0,0);
        Point_2D c22 = new Point_2D(4,4);
        Point_2D c33 = new Point_2D(0,4);
        Point_2D c44 = new Point_2D(4,0);
        assertEquals(c1, c11);
        assertEquals(c2, c22);
        assertEquals(c3, c33);
        assertEquals(c4, c44);
    }

    @Test
    void scale() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(4,4);
        Point_2D c3 = new Point_2D(0,4);
        Point_2D c4 = new Point_2D(4,0);
        Rect_2D s = new Rect_2D(c1, c2, c3, c4);
        Point_2D cen = new Point_2D(2,2);
        s.scale(cen, 1);
        Point_2D c11 = new Point_2D(0,0);
        Point_2D c22 = new Point_2D(4,4);
        Point_2D c33 = new Point_2D(0,4);
        Point_2D c44 = new Point_2D(4,0);
        assertEquals(c1, c11);
        assertEquals(c2, c22);
        assertEquals(c3, c33);
        assertEquals(c4, c44);
        s.scale(cen, 0.5);
        assertEquals(s.area(), 4);
    }

    @Test
    void rotate() {
        Point_2D c1 = new Point_2D(5,5);
        Point_2D c2 = new Point_2D(9,9);
        Point_2D c3 = new Point_2D(5,9);
        Point_2D c4 = new Point_2D(9,5);
        Rect_2D s = new Rect_2D(c1, c2, c3, c4);
        Point_2D cen = new Point_2D(7,7);
        System.out.println(s.toString());
        s.rotate(cen, 90);
        Point_2D c11 = new Point_2D(9,5);
        Point_2D c22 = new Point_2D(5,9);
        Point_2D c33 = new Point_2D(5,5);
        Point_2D c44 = new Point_2D(9,9);
        System.out.println(s.toString());
        assertEquals(c1, c11);
        assertEquals(c2, c22);
        assertEquals(c3, c33);
        assertEquals(c4, c44);
    }
}