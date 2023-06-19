package ex4;

import geo.*;
import gui.GUIShape;
import gui.GUI_Shape;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PropertyResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {
    private ArrayList<GUI_Shape> _shapes = new ArrayList<GUI_Shape>();
    private Point_2D s1 = new Point_2D(0,0);
    private Point_2D s2 = new Point_2D(5,5);
    private Point_2D s3 = new Point_2D(10,10);
    private Point_2D s4 = new Point_2D(15,15);
    private Point_2D s5 = new Point_2D(20,20);
    private Point_2D s6 = new Point_2D(25,25);
    private double radius = 5;
    GeoShape c = new Segment_2D(s1, s2);
    GeoShape c1 = new Circle_2D(s1, radius);
    GeoShape c2 = new Rect_2D(s2, s3);
    GeoShape c3 = new Triangle_2D(s1, s2, s3);
    GUI_Shape z1 = new GUIShape(c, false, Color.BLACK, 1);
    GUI_Shape z2 = new GUIShape(c1, true, Color.blue, 0);
    GUI_Shape z3 = new GUIShape(c2, false, Color.CYAN, 2);
    GUI_Shape z4 = new GUIShape(c3, true, Color.GREEN, 3);
    ShapeCollection v = new ShapeCollection();
    @Test
    void get() {
        v.add(z1);
        v.add(z2);
        GUI_Shape ans = v.get(1);
        assertTrue(ans == z2);
    }

    @Test
    void size() {
        v.add(z1);
        v.add(z2);
        ShapeCollection c6 = (ShapeCollection) v.copy();
        assertEquals(v.size(), c6.size());
    }

    @Test
    void removeElementAt() {
        v.add(z1);
        v.add(z2);
        v.removeElementAt(1);
        assertEquals(v.size(), 1);
    }

    @Test
    void addAt() {
        v.add(z1);
        v.add(z2);
        v.addAt(z3, 2);
        assertEquals(v.size(), 3);
    }

    @Test
    void add() {
        v.add(z1);
        v.add(z2);
        v.add(z3);
        assertEquals(v.size(), 3);
    }

    @Test
    void copy() {
        v.add(z1);
        v.add(z2);
        v.add(z3);
        ShapeCollection b = (ShapeCollection) v.copy();
        assertEquals(b.size(), v.size());
    }

    @Test
    void sort() {
        v.add(z1);
        v.add(z2);
        v.add(z3);
//        v.sort(Comparator.comparing((comp)));
    }

    @Test
    void removeAll() {
        v.add(z1);
        v.add(z2);
        v.add(z3);
        v.removeAll();
        assertEquals(v.size(), 0);
        System.out.println(v.toString());
    }

    @Test
    void save() {
        String testFilePath = "test_shapes.ser"; // Test file path
        v.add(z1);
        v.add(z2);
        v.add(z3);

        // Save the ShapeCollection
        v.save(testFilePath);

        // Verify that the file exists
        File file = new File(testFilePath);
        assertTrue(file.exists());

        // Clean up - delete the test file
        file.delete();
    }

    @Test
    void load() {

    }
}