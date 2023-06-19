package ex4;

import geo.*;
import gui.Ex4_GUI;
import gui.GUIShape;
import gui.GUI_Shape;
import gui.StdDraw_Ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private GUI_Shape_Collection _shapes = new ShapeCollection();
	private GUI_Shape _gs;
	private Color _color = Color.blue;
	private boolean _fill = false;
	private String _mode = "";
	private Point_2D _p1;
	private Point_2D _p2;
	private ArrayList<Point_2D> point2DS = new ArrayList<>();
	private static Ex4 _winEx4 = null;
	private static int tag = 0;
	private Ex4() {
		init(null);
	}

	public void init(GUI_Shape_Collection s) {
		if (s == null) {
			_shapes = new ShapeCollection();
		} else {
			_shapes = s;
		}// //should be s.copy();}
		_gs = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		_p1 = null;
	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0, d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if (_winEx4 == null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape sh = _shapes.get(i);

			drawShape(sh);
		}
		if (_gs != null) {
			drawShape(_gs);
		}
		StdDraw_Ex4.show();
	}

	private static void drawShape(GUI_Shape g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if (g.isSelected()) {
			StdDraw_Ex4.setPenColor(Color.gray);
		}
		GeoShape gs = g.getShape();
		boolean isFill = g.isFilled();
		if (gs instanceof Circle_2D) {
			Circle_2D c = (Circle_2D) gs;
			Point_2D cen = c.getCenter();
			double rad = c.getRadius();
			if (isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			} else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		if (gs instanceof Rect_2D) {
			Rect_2D s = (Rect_2D) gs;
			double[] xarr = {s.getP1().x(), s.getP2().x(), s.getP3().x(), s.getP4().x()};
			double[] yarr = {s.getP1().y(), s.getP2().y(), s.getP3().y(), s.getP4().y()};
			if (isFill) {
				StdDraw_Ex4.filledPolygon(xarr, yarr);
			} else {
				StdDraw_Ex4.polygon(xarr, yarr);
			}
		}
		if (gs instanceof Triangle_2D) {
			Triangle_2D s = (Triangle_2D) gs;
			Point_2D[] points = s.getAllPoints();
			double[] yarr = new double[points.length];
			double[] xarr = new double[points.length];
			for (int i = 0; i < points.length; i++) {
				yarr[i] = points[i].y();
				xarr[i] = points[i].x();
			}
			if (isFill) {
				StdDraw_Ex4.filledPolygon(xarr, yarr);
			} else {
				StdDraw_Ex4.polygon(xarr, yarr);
			}
		}
		if (gs instanceof Polygon_2D) {
			Polygon_2D s = (Polygon_2D) gs;
			if (isFill) {
				StdDraw_Ex4.filledPolygon(s.x(), s.y());
			} else {
				StdDraw_Ex4.polygon(s.x(), s.y());
			}
		}
		if (gs instanceof Segment_2D) {
			Segment_2D s = (Segment_2D) gs;
			Point_2D p1 = s.get_p1();
			Point_2D p2 = s.get_p2();
			StdDraw_Ex4.line(p1.x(), p1.y(), p2.x(), p2.y());
		}

	}

	private void setColor(Color c) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			if (s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	private void setFill() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			if (s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if (p.equals("Blue")) {
			_color = Color.BLUE;
			setColor(_color);
		}
		if (p.equals("Red")) {
			_color = Color.RED;
			setColor(_color);
		}
		if (p.equals("Green")) {
			_color = Color.GREEN;
			setColor(_color);
		}
		if (p.equals("White")) {
			_color = Color.WHITE;
			setColor(_color);
		}
		if (p.equals("Black")) {
			_color = Color.BLACK;
			setColor(_color);
		}
		if (p.equals("Yellow")) {
			_color = Color.YELLOW;
			setColor(_color);
		}
		if (p.equals("Fill")) {
			_fill = true;
			setFill();
		}
		if (p.equals("Empty")) {
			_fill = false;
			setFill();
		}
		if (p.equals("Clear")) {
			_shapes.removeAll();
		}
		// sorting the shapes by the mode that is selected, by area, by perimeter, by tag, by toString and anti
		if(p.equals("ByArea")) {
			_shapes.sort( new ShapeComp(Ex4_Const.Sort_By_Area));
		}
		if(p.equals("ByAntiArea")) {
			_shapes.sort( new ShapeComp(Ex4_Const.Sort_By_Anti_Area));
		}
		if(p.equals("ByPerimeter")) {
			_shapes.sort( new ShapeComp(Ex4_Const.Sort_By_Perimeter));
		}
		if(p.equals("ByAntiPerimeter")) {
			_shapes.sort( new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter));
		}
		if(p.equals("ByTag")) {
			_shapes.sort( new ShapeComp(Ex4_Const.Sort_By_Tag));
		}
		if(p.equals("ByAntiTag")) {
			_shapes.sort( new ShapeComp(Ex4_Const.Sort_By_Anti_Tag));
		}
		if(p.equals("ByToString")) {
			_shapes.sort( new ShapeComp(Ex4_Const.Sort_By_toString));
		}
		if(p.equals("ByAntiToString")) {
			_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_toString));
		}
		// removing a certain shape
		if (_mode.equals("Remove")) {
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shape s = _shapes.get(i);
				if (s.isSelected()) {
					_shapes.removeElementAt(i);
				}
			}
		}
		// none of the shapes is selected
		if (_mode.equals("None")) {
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shape s = _shapes.get(i);
				if (s.isSelected()) {
					s.setSelected(false);
				}
			}
		}
		// selecting all the shapes in one click
		if (_mode.equals("All")) {
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shape s = _shapes.get(i);
				if (!s.isSelected()) {
					s.setSelected(true);
				}
			}
		}
		// making the selected unselected and the unselected selected
		if (_mode.equals("Anti")) {
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shape s = _shapes.get(i);
				if (!s.isSelected()) {
					s.setSelected(true);
				} else {
					s.setSelected(false);
				}
			}
		}
		// saving the file of the shapes that we've drawn.
		if(p.equals("Save")) {
			FileDialog chooser = new FileDialog(new JFrame(),"Use a .png or .jpg extension", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if(filename != null) {
				_shapes.save(chooser.getDirectory() + File.separator + chooser.getFile());
			}
		}
		// loading the shapes that we've drawn before
		if (p.equals("Load")) {
			FileDialog chooser = new FileDialog(new JFrame(),"Use a .png or .jpg extension", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if(filename != null) {
				_shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
			}
		}

		drawShapes();
	}

	public static int getTag() {
		return tag;
	}

	public void mouseClicked(Point_2D p) {
		System.out.println("Mode: " + _mode + "  " + p);
		if (_mode.equals("Circle")) {
			if (_gs == null) {
				_p1 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				tag++;
			}
		}
	// if the mode is drawing a rectangle
		if (_mode.equals("Rect")) {
			if (_gs == null) { // if the first point is still not set
				_p1 = new Point_2D(p);
			} else {
				_gs.setColor(_color); // setting the color
				_gs.setFilled(_fill); // setting whether it is filled or not
				_shapes.add(_gs); // adding the GUI_Shape to the arrayList of the shapes
				_gs = null;
				_p1 = null;
				tag++;
			}
		}
	// if the mode is drawing a Triangle
		if (_mode.equals("Triangle")) {
			if (_gs == null) { // if the first point is still not set
				_p1 = new Point_2D(p);
			} else if (_p2 == null) { // if the second point is still not set
				_p2 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null; // resetting to null
				_p1 = null; // resetting to null
				_p2 = null; // resetting to null
				tag++; // incrementing the tag
			}
		}
 	// if the mode is drawing a Polygon
		if (_mode.equals("Polygon")) {
			if (_gs == null) { // if the first point is still not set
				point2DS.add(p);
				_p1 = new Point_2D(p);
			} else { // if it is add it the ArrayList of Point2DS
				point2DS.add(p); // adding the point to the list of the polygon's Points
				tag++; // incrementing the tag by 1;
			}
		}
	// if the mode is equal to drawing a Segment
		if (_mode.equals("Segment")) {
			if (_gs == null) {	// if the first point is still not set
				_p1 = new Point_2D(p);
			} else {	// if it is draw it accordingly
				_gs.setColor(_color); // setting the color
				_gs.setFilled(_fill); // setting whether it is filled or not
				_shapes.add(_gs); // adding it to the ArrayList of shapes that we created
				_gs = null; // resetting the GUISHAPE
				_p1 = null; // resetting the point
				tag++; // incrementing the tag
			}
		}
	// this method is used for moving the shape
		if (_mode.equals("Move")) {
			if (_p1 == null) {
				_p1 = new Point_2D(p);
			} else {
				_p1 = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y()); // updating p1
				move(); // moving the shape
				_p1 = null;
			}
		}
	// this method is used to scale the shapes by the ratio of 90%
		if (_mode.equals("Scale_90%")) {
			if (_p1 == null) {
				_p1 = new Point_2D(p);
			} else {
				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shape s = _shapes.get(i);
					GeoShape c = s.getShape();
					if (c != null && s.isSelected()) {
						c.scale(_p1, 0.9);
					}
				}
			}
		}
	// this method is used for scaling the specific shape by 110% ratio
		if (_mode.equals("Scale_110%")) {
			if (_p1 == null) {
				_p1 = new Point_2D(p);
			} else {
				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shape s = _shapes.get(i);
					GeoShape c = s.getShape();
					if (c != null && s.isSelected()) {
						c.scale(_p1, 1.1);
					}
				}
			}
		}
		// this method is used to copy the shapes
		if (_mode.equals("Copy")){
			if (_p1== null) {
				_p1= new Point_2D(p);
			}
			else{
				Point_2D translete = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shape new_shape = _shapes.get(i);
					GeoShape geo_sh = new_shape.getShape();
					if (new_shape.isSelected() && geo_sh != null) {
						GUI_Shape copy = new_shape.copy();
						copy.getShape().translate(translete);
						copy.setTag(tag++);
						_shapes.add(copy);
					}
					_p1 = null;
				}
			}
		}
		// this method is used to rotate the shapes by calculating the angledegrees from the starting point and the second selected point
		if (_mode.equals("Rotate")) {
			if (_p1 == null) {
				_p1 = new Point_2D(p);
			}
			else {
				_p2 = new Point_2D(p);
				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shape s = _shapes.get(i);
					GeoShape g = s.getShape();
					if (s.isSelected() && g != null) {
						g.rotate(_p1, Math.toDegrees(_p1.anglefrompoints(_p2)));
					}
				}
				_p1 = null;
				_p2 = null;
			}
		}
		// selecting the shape
		if (_mode.equals("Point")) {
			select(p);
		}

		drawShapes();
	}
		// selecting the shapes
	private void select(Point_2D p) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (g != null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	// moving the shape
	private void move() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if (s.isSelected() && g != null) {
				g.translate(_p1);
			}
		}
	}

	public void mouseRightClicked(Point_2D p) {
		System.out.println("right click!");
		if (_gs!=null) {
			// checking whether the mode is a Polygon
			if(_mode.equals("Polygon")) {
				Polygon_2D polygon2 = new Polygon_2D(point2DS); // creating a Polygon with the point2DS ArrayList
				_gs = new GUIShape(polygon2, _fill, _color, tag); // making a new GUIShape of a Polygon
				_gs.setColor(_color); // setting the color
				_gs.setFilled(_fill); // setting the boolean filled
				_shapes.add(_gs); // adding the shape to the ArrayList of shapes
				tag++; // incrementing the tag by 1
				_gs = null; // resetting the GUIShape
				_p1 = null; // resetting the point
				point2DS.clear(); // resetting the points of the polygon
				drawShapes(); // drawing the shapes
			}
			else { // this mode only works in drawing the polygon so if the polygon is not selected the points
				_gs = null;
				_p1 = null;
				_p2 = null;
				drawShapes();
			}
		}

	}
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShape gs = null;
		//	System.out.println("M: "+x1+","+y1);
			Point_2D p = new Point_2D(x1,y1);
			// if the mode circle is selected draw accordingly...
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle_2D(_p1,r);
			}
			// if the mode Rect is selected draw accordingly...
			if (_mode.equals("Rect")){
				Point_2D p3 = new Point_2D(p.x(), _p1.y()); // creating the third point of the polygon using _p1 and p
				Point_2D p4 = new Point_2D(_p1.x(), p.y());	// creating the third point of the polygon using _p1 and p
				gs = new Rect_2D(_p1, p3, p, p4);
			}
			// if the mode circle is selected draw accordingly...
			if (_mode.equals("Polygon")){
				ArrayList <Point_2D> points = point2DS; // create a new ArrayList of Points of each polygon in order to store them
				points.add(p); // adding the current point
				gs = new Polygon_2D(points); // updating the Polygon in GUIShape with a new set of Points
				points.remove(p);
			}
			// if the mode Segment is selected draw accordingly...
			if (_mode.equals("Segment")){
				gs = new Segment_2D(_p1, p); // updating the Segment in GUIShape with the first click and the current one
			}
			// if the mode Triangle is selected draw accordingly...
			if (_mode.equals("Triangle")){
				if (_p2 != null){ // if the second point is chosen
					gs = new Triangle_2D(_p1, _p2, p); // update the Triangle in the GUIShape with the first, Second and the current point.
				}
				else { // if the third Point is not selected
					gs = new Segment_2D(_p1, p); // updating a Segment in GUIShape with the first click and the current one
				}
			}
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	public GUI_Shape_Collection getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
