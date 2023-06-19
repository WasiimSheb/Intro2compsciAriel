package gui;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import ex4.ShapeCollection;
import geo.Circle_2D;
import geo.GeoShape;
import geo.Point_2D;
import geo.Segment_2D;
import org.junit.jupiter.api.Test;

class GUIShapeTest {
    //segment
    Point_2D p1= new Point_2D(1,2);
    Point_2D p2= new Point_2D(6,2);
    GeoShape seg= new Segment_2D(p1,p2);
    //circle
    Point_2D p3 = new Point_2D(1,2);
    GeoShape c= new Circle_2D(p3,5);
    GUI_Shape gs1 = new GUIShape(seg,true,Color.BLUE,0);
    GUI_Shape gs3 =new GUIShape(c,false,Color.BLACK,1);
    ShapeCollection collection = new ShapeCollection();

    @Test
    public void testShapes() {
        //test circle
        String [] spl1 = gs3.toString().split(",");
        assertEquals(spl1[2], " _fill=" + "false");
        // Test segment
        String [] arr = gs1.toString().split(",");
        assertEquals(arr[4], " _fill=true");
        //toString
        collection.toString();
        String [] split2 = gs3.toString().split(",");
        assertEquals(split2[6], " _tag=1");
        //test color
        assertEquals(gs3.getColor().getRGB(),Color.BLACK.getRGB());
    }
}