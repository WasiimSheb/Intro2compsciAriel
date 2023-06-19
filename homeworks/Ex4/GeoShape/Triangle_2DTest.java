package geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Triangle_2DTest {

    @Test
    void getAllPoints() {
        Triangle_2D test = new Triangle_2D(new Point_2D(0,0), new Point_2D(2,2), new Point_2D(2,0));
        Point_2D [] expected = test.getAllPoints();
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(2,2);
        Point_2D c3 = new Point_2D(2,0);
        Point_2D [] actual = {c1, c2, c3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void contains() {
        Point_2D cen = new Point_2D(1, 1);
        Point_2D r = new Point_2D(1, 3);
        Point_2D u = new Point_2D(4, 1);
        Triangle_2D t = new Triangle_2D(cen, r, u);
        boolean ans = t.contains(new Point_2D(1, 1));
        boolean ans2 = t.contains(u);
        if (!ans) {
            fail();
        }
        if (!ans2){
            fail();
        }
        boolean ans3 = t.contains(r);
        assertTrue(ans3);
    }
    @Test
    void area() {
        Point_2D cen = new Point_2D(1, 1);
        Point_2D r = new Point_2D(1, 3);
        Point_2D u = new Point_2D(4, 1);
        Triangle_2D t = new Triangle_2D(cen, r, u);
        double ar = Math.round(t.area());
        assertEquals(3, ar);
    }

    @Test
    void perimeter() {
        Point_2D cen = new Point_2D(0, 0);
        Point_2D r = new Point_2D(5, 0);
        Point_2D u = new Point_2D(0, 5);
        Triangle_2D t = new Triangle_2D(cen, r, u);
        double per = Math.round(t.perimeter());
        assertEquals(per, 17);
    }

    @Test
    void translate() {
        Point_2D cen = new Point_2D(0, 0);
        Point_2D r = new Point_2D(5, 0);
        Point_2D u = new Point_2D(0, 5);
        Triangle_2D t = new Triangle_2D(cen, r, u);
        t.translate(new Point_2D(2,0));
        System.out.println(t.toString());
        Point_2D check1 = new Point_2D(2,0);
        Point_2D check2 = new Point_2D(7,0);
        Point_2D check3 = new Point_2D(2,5);
        Point_2D [] points = t.getAllPoints();
        assertEquals(points[0], check1);
        assertEquals(points[1], check2);
        assertEquals(points[2], check3);
    }

    @Test
    void copy() {
        Point_2D cen = new Point_2D(0, 0);
        Point_2D r = new Point_2D(5, 0);
        Point_2D u = new Point_2D(0, 5);
        Triangle_2D t = new Triangle_2D(cen, r, u);
        Point_2D [] i = t.getAllPoints();
        t.copy();
        Point_2D [] o = t.getAllPoints();
        assertEquals(o[0], i[0]);
        assertEquals(o[1], i[1]);
        assertEquals(o[2], i[2]);
    }

    @Test
    void scale() {
        Point_2D p1 = new Point_2D(2, 2);
        Point_2D p2 = new Point_2D(3, 4);
        Point_2D p3 = new Point_2D(5, 3);
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);
        Point_2D center = new Point_2D(4, 3);
        double ratio = 0.75;
        p1.scale(center, ratio);
        p2.scale(center, ratio);
        p3.scale(center, ratio);
        assertEquals(new Point_2D(2.5, 2.25), p1);
        assertEquals(new Point_2D(3.25, 3.75), p2);
        assertEquals(new Point_2D(4.75, 3), p3);
        Point_2D p11 = new Point_2D(0, 0);
        Point_2D p12 = new Point_2D(0, 5);
        Point_2D p13 = new Point_2D(5, 0);
        Triangle_2D triangle2 = new Triangle_2D(p12, p12, p13);
        Point_2D center2 = new Point_2D(2, 3);
        double ratio2 = 1;
        p1.scale(center2, ratio);
        p2.scale(center2, ratio);
        p3.scale(center2, ratio);
        assertEquals(new Point_2D(0, 0), p11);
        assertEquals(new Point_2D(0, 5), p12);
        assertEquals(new Point_2D(5, 0), p13);
    }

    @Test
    void rotate() {
        Point_2D p1 = new Point_2D(5, 5);
        Point_2D p2 = new Point_2D(5, 10);
        Point_2D p3 = new Point_2D(10, 5);
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);
        double angleDegrees = 90.0;
        Point_2D center = new Point_2D(2.5, 2.5);
        triangle.rotate(p1,90);
        System.out.println(triangle.toString());
        assertEquals(new Point_2D(5, 5), p1);
        assertEquals(new Point_2D(0, 5.000000000000001), p2);
        assertEquals(new Point_2D(5, 10), p3);
    }
}