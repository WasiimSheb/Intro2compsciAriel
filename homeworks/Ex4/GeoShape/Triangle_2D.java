package geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	private Point_2D p1;
	private Point_2D p2;
	private Point_2D p3;
	private static final double EPS = 0.001;
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this.p1 = new Point_2D(p1);
		this.p2 = new Point_2D(p2);
		this.p3 = new Point_2D(p3);
	}

	public Triangle_2D(Triangle_2D t1) {
		this.p1 = new Point_2D(t1.getP1());
		this.p2 = new Point_2D(t1.getP2());
		this.p3 = new Point_2D(t1.getP3());
	}

	public Point_2D getP1() {
		return p1;
	}

	public void setP1(Point_2D p1) {
		this.p1 = p1;
	}

	public Point_2D getP2() {
		return p2;
	}

	public void setP2(Point_2D p2) {
		this.p2 = p2;
	}

	public Point_2D getP3() {
		return p3;
	}

	public void setP3(Point_2D p3) {
		this.p3 = p3;
	}

	public Point_2D[] getAllPoints() {
		Point_2D [] points = new Point_2D[3];
		points [0] = p1;
		points [1] = p2;
		points [2] = p3;
		return points;
	}

	@Override
	public boolean contains(Point_2D ot) {
		Triangle_2D s1 = new Triangle_2D(p1, p2, ot);
		Triangle_2D s2 = new Triangle_2D(p1, p3, ot);
		Triangle_2D s3 = new Triangle_2D(p2, p3, ot);
		Triangle_2D s = new Triangle_2D(p1, p2, p3);
		return (Math.abs(s.area() - (s1.area() + s2.area() + s3.area()))) <= EPS;
	}

	@Override
	public double area() {
		double a = p1.distance(p2);
		double b = p2.distance(p3);
		double c = p1.distance(p3);
		double s = (a + b + c) / 2;
		return (Math.sqrt(s * (s-a) * (s-b) * (s-c)));
	}

	@Override
	public double perimeter() {
		return p1.distance(p2) + p2.distance(p3) + p1.distance(p3);
	}

	@Override
	public void translate(Point_2D vec) {
		p1.move(vec);
		p2.move(vec);
		p3.move(vec);
	}

	@Override
	public GeoShape copy() {
		return new Triangle_2D(p1, p2, p3);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		p1.scale(p1, ratio);
		p2.scale(p2, ratio);
		p3.scale(p3, ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);
		p3.rotate(center, angleDegrees);
	}

	public static void main(String[] args) {
		Point_2D cen = new Point_2D (0, 0);
		Point_2D r = new Point_2D(2.5,2.5);
		Point_2D u = new Point_2D(0,5);
		Triangle_2D t = new Triangle_2D(cen, r, u);
		//boolean ans = t.contains(new Point_2D(1,1));
		//System.out.println(ans);
		System.out.println(t.area());

	}
	@Override
	public String toString() {
	return "Triangle_2D," +this.p1+ ",  "+ this.p2+ ",  " +this.p3;
	}
}
