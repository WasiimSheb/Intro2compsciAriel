package geo;

import com.sun.source.tree.Scope;

import java.awt.*;

/**
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;
	
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}

	public Circle_2D(Circle_2D copy) {
		this._center = new Point_2D(copy.getCenter());
		this._radius = copy.getRadius();
	}

	public double getRadius() {return this._radius;}
	public Point_2D getCenter(){ return _center;}
	 @Override
	    public String toString() {
		return "[" + "Center: " + this._center+ "  " + "Radius: " + this._radius + "]";
	}
	@Override
	public boolean contains(Point_2D ot) {
		return ot.distance(this._center) <= this._radius;
	}
	@Override
	public double area() {
		return Math.PI * Math.pow(_radius, 2);
	}
	@Override
	public double perimeter() {
		return 2 * Math.PI * this._radius;
	}
	@Override
	public void translate(Point_2D vec) {
		this._center.move(vec);
	}
	@Override
	public GeoShape copy() {
		return new Circle_2D(this._center, this._radius);
	}
	@Override
	public void scale(Point_2D center, double ratio) {
		this._center.scale(center, ratio);
		this._radius *= ratio;
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.rotate(center, angleDegrees);
	}

	public static void main(String[] args) {
		Point_2D r = new Point_2D (1,1);
		Circle_2D a = new Circle_2D(r, 3);
		Point_2D t = new Point_2D (3,3);
		//a.translate(t);
		GeoShape s = new Circle_2D(r,3);
		s.copy();
		s.translate(r);
		System.out.println(s.toString());
		System.out.print(a._center);
	}
}
