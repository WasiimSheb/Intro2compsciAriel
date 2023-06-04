package geo;

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
	public double getRadius() {return this._radius;}
	public Point_2D getCenter(){ return _center;}
	 @Override
	    public String toString() {
		return "Circle radius: " + this._radius + "Circle PointCenter: " + this._center;
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
		GeoShape r = new Circle_2D(this._center, this._radius);
		return r;
	}
	@Override
	public void scale(Point_2D center, double ratio) {
		this.scale(center, ratio);
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.rotate(center, angleDegrees);
	}
}
