package geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point_2DTest {

    @Test
    public void testIx() {
        Point_2D p = new Point_2D(2.5, 3.7);
        int expectedX = 2;
        assertEquals(expectedX, p.ix());
    }

    @Test
    public void testIy() {
        Point_2D p = new Point_2D(2.5, 3.7);
        int expectedY = 3;
        assertEquals(expectedY, p.iy());
    }

    @Test
    public void testAdd() {
        Point_2D p1 = new Point_2D(2, 3);
        Point_2D p2 = new Point_2D(1, 1);
        Point_2D expectedSum = new Point_2D(3, 4);
        assertEquals(expectedSum, p1.add(p2));
    }

    @Test
    public void testToString() {
        Point_2D p = new Point_2D(2.5, 3.7);
        String expectedString = "2.5,3.7";
        assertEquals(expectedString, p.toString());
    }

    @Test
    public void testDistance() {
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(3, 4);
        double expectedDistance = 5.0;
        assertEquals(expectedDistance, p1.distance(p2), 0.0001);
    }

    @Test
    public void testEquals() {
        Point_2D p1 = new Point_2D(2, 3);
        Point_2D p2 = new Point_2D(2, 3);
        Point_2D p3 = new Point_2D(4, 5);
        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
    }

    @Test
    public void testClose2Equals() {
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(1, 1);
        double epsilon = 1.42;
        assertTrue(p1.close2equals(p2, epsilon));
    }

    @Test
    public void testVector() {
        Point_2D p1 = new Point_2D(2, 3);
        Point_2D p2 = new Point_2D(5, 7);
        Point_2D expectedVector = new Point_2D(3, 4);
        assertEquals(expectedVector, p1.vector(p2));
    }

    @Test
    public void testMove() {
        Point_2D p = new Point_2D(2, 3);
        Point_2D vector = new Point_2D(5, 7);
        Point_2D expectedPosition = new Point_2D(7, 10);
        p.move(vector);
        assertEquals(expectedPosition, p);
    }

    @Test
    public void testScale() {
        Point_2D p = new Point_2D(2, 3);
        Point_2D center = new Point_2D(1, 1);
        double ratio = 2;
        Point_2D expectedScaledPoint = new Point_2D(3, 5);
        p.scale(center, ratio);
        assertEquals(expectedScaledPoint, p);
    }
    @Test
    public void testRotate() {
        Point_2D p = new Point_2D(1, 0);
        Point_2D center = new Point_2D(0, 0);
        double angleDegrees = 90;
        double epsilon = 0.0001;
        p.rotate(center, angleDegrees);
        // Expected values after rotation
        double expectedX = 0;
        double expectedY = 1;
        // Assert the rotated coordinates
        assertEquals(expectedX, p.x(), epsilon);
        assertEquals(expectedY, p.y(), epsilon);
    }
}