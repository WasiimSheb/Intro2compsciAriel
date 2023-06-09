package geo;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Polygon_2DTest {
        private ArrayList <Point_2D> points = new ArrayList<>();
        private static final double EPS = 0.001;
    @Test
    void getAllPoints() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(4,4);
        Point_2D c3 = new Point_2D(0,4);
        Point_2D c4 = new Point_2D(4,0);
        points.add(c1);
        points.add(c2);
        points.add(c3);
        points.add(c4);
        Polygon_2D s = new Polygon_2D(points);
        Point_2D [] expected = points.toArray(new Point_2D[points.size()]);
        Point_2D [] all = s.getAllPoints();
        assertArrayEquals(all, expected);
    }

    @Test
    void add() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(4,4);
        Point_2D c3 = new Point_2D(0,4);
        Point_2D c4 = new Point_2D(4,0);
        points.add(c1);
        points.add(c2);
        points.add(c3);
        points.add(c4);
        Polygon_2D s = new Polygon_2D(points);
        ArrayList <Point_2D> check = new ArrayList<>();
        check = (ArrayList<Point_2D>) points.clone();
        Point_2D c5 = new Point_2D(10,10);
        s.add1(c5);
        check.add(c5);
        Point_2D [] actual = check.toArray(new Point_2D[check.size()]);
        Point_2D [] expected = s.getAllPoints();
        assertArrayEquals(actual, expected);
    }

    @Test
    void contains() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,4);
        Point_2D c3 = new Point_2D(4,4);
        Point_2D c4 = new Point_2D(4,0);
        ArrayList<Point_2D> _points = new ArrayList<>();
        _points.add(c1);
        _points.add(c2);
        _points.add(c3);
        _points.add(c4);
        Polygon_2D h = new Polygon_2D(_points);
        Point_2D ch = new Point_2D(2,2);
        assertTrue(h.contains(ch));
        Point_2D ch1 = new Point_2D(5,2);
        assertFalse(h.contains(ch1));
        assertTrue(h.contains(new Point_2D(0,0)));
    }

    @Test
    void area() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,4);
        Point_2D c3 = new Point_2D(4,4);
        Point_2D c4 = new Point_2D(4,0);
        ArrayList<Point_2D> _points = new ArrayList<>();
        _points.add(c1);
        _points.add(c2);
        _points.add(c3);
        _points.add(c4);
        Polygon_2D h = new Polygon_2D(_points);
        assertEquals(h.area(), 16);
    }

    @Test
    void perimeter() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,4);
        Point_2D c3 = new Point_2D(4,4);
        Point_2D c4 = new Point_2D(4,0);
        ArrayList<Point_2D> _points = new ArrayList<>();
        _points.add(c1);
        _points.add(c2);
        _points.add(c3);
        _points.add(c4);
        Polygon_2D h = new Polygon_2D(_points);
        assertEquals(h.area(), 16);
    }

    @Test
    void translate() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,4);
        Point_2D c3 = new Point_2D(4,4);
        Point_2D c4 = new Point_2D(4,0);
        ArrayList<Point_2D> _points = new ArrayList<>();
        _points.add(c1);
        _points.add(c2);
        _points.add(c3);
        _points.add(c4);
        Polygon_2D h = new Polygon_2D(_points);
        h.translate(new Point_2D(1,1));
        Point_2D c11 = new Point_2D(1,1);
        Point_2D c22 = new Point_2D(1,5);
        Point_2D c33 = new Point_2D(5,5);
        Point_2D c44 = new Point_2D(5,1);
        Point_2D [] actual = {c11, c22, c33, c44};
        Point_2D [] expected = h.getAllPoints();
        assertArrayEquals(actual, expected);
    }

    @Test
    void copy() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,4);
        Point_2D c3 = new Point_2D(4,4);
        Point_2D c4 = new Point_2D(4,0);
        ArrayList<Point_2D> _points = new ArrayList<>();
        _points.add(c1);
        _points.add(c2);
        _points.add(c3);
        _points.add(c4);
        Polygon_2D s = new Polygon_2D(_points);
        s.copy();
       Point_2D [] expected = {c1, c2, c3, c4};
       Point_2D [] actual = s.getAllPoints();
       assertArrayEquals(expected, actual);
    }

    @Test
    void scale() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(0,4);
        Point_2D c3 = new Point_2D(4,4);
        Point_2D c4 = new Point_2D(4,0);
        ArrayList<Point_2D> _points = new ArrayList<>();
        _points.add(c1);
        _points.add(c2);
        _points.add(c3);
        _points.add(c4);
        Polygon_2D h = new Polygon_2D(_points);
        Point_2D cen = new Point_2D(2,2);
        h.scale(cen, 1);
      //  System.out.println(h.toString());
        Point_2D [] expected = {c1, c2, c3, c4};
        Point_2D [] actual = h.getAllPoints();
        //System.out.println(Arrays.toString(expected));
        //System.out.println(Arrays.toString(actual));
        assertArrayEquals(expected, actual);
        h.scale(cen, 0.5);
        System.out.println(h.toString());
        System.out.println(h.area());
        assertEquals(h.area(), 4);
    }

    @Test
    void rotate() {
        Point_2D c1 = new Point_2D(0,0);
        Point_2D c2 = new Point_2D(4,4);
        Point_2D c3 = new Point_2D(0,4);
        Point_2D c4 = new Point_2D(4,0);
        ArrayList<Point_2D> _points = new ArrayList<>();
        _points.add(c1);
        _points.add(c2);
        _points.add(c3);
        _points.add(c4);
        Polygon_2D s = new Polygon_2D(_points);
        s.translate(new Point_2D(5,5));
        System.out.println(s.toString());
        Point_2D cen = new Point_2D(2,2);
        s.rotate(cen, 360);
        System.out.println(s.toString());
        Point_2D c11 = new Point_2D(5,5);
        Point_2D c22 = new Point_2D(9,9);
        Point_2D c33 = new Point_2D(5,9);
        Point_2D c44 = new Point_2D(9,5);
        Point_2D [] po = s.getAllPoints();
        assertTrue(po[0].x() - c11.x() <= EPS && po[0].y() - c11.y() <= EPS);
        assertTrue(po[1].x() - c22.x() <= EPS && po[1].y() - c22.y() <= EPS);
        assertTrue(po[2].x() - c33.x() <= EPS && po[2].y() - c33.y() <= EPS);
        assertTrue(po[3].x() - c44.x() <= EPS && po[3].y() - c44.y() <= EPS);
    }
}