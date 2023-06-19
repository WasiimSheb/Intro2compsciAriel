package geo;

import ex4.Ex4_Const;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect_2D implements GeoShape {
	private Point_2D p1;
	private Point_2D p2;
	private Point_2D p3;
	private Point_2D p4;
	public Rect_2D(Point_2D p1, Point_2D p2) {
		this.p1 = new Point_2D(p1);
		this.p2 = new Point_2D(p2);
		this.p3 = new Point_2D(p1.x(), p2.y());
		this.p4 = new Point_2D(p2.x(), p1.y());
	}
	public Rect_2D(Point_2D p1, Point_2D p2, Point_2D p3, Point_2D p4){
		this.p1 = new Point_2D(p1);
		this.p2 = new Point_2D(p2);
		this.p3 = new Point_2D(p3);
		this.p4 = new Point_2D(p4);
	}
	public Rect_2D(Rect_2D t1) {
        this.p1= new Point_2D(t1.getP1());
		this.p2= new Point_2D(t1.getP2());
		this.p3= new Point_2D(t1.getP3());
		this.p4= new Point_2D(t1.getP4());
	}
	public double getWidth(){
		return this.p2.distance(p3);
	}
	public double getHeight(){
		return this.p1.distance(p2);
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

	public Point_2D getP4() {
		return p4;
	}

	public void setP4(Point_2D p4) {
		this.p4 = p4;
	}

	@Override
	public String toString() {
		return "Rect_2D{" +
				"p1=" + p1 +
				", p2=" + p2 +
				", p3=" + p3 +
				", p4=" + p4 +
				'}';
	}

	@Override
	public boolean contains(Point_2D ot) {
		Triangle_2D c1 = new Triangle_2D(ot, p1, p2);
		Triangle_2D c2 = new Triangle_2D(ot, p2, p3);
		Triangle_2D c3 = new Triangle_2D(ot, p3, p4);
		Triangle_2D c4 = new Triangle_2D(ot, p1, p4);
		double area = c1.area() + c2.area() + c3.area() + c4.area();
		if (Math.abs(this.area()-area)<= Ex4_Const.EPS){
			return true;
		}
			else
				return false;
	}


	@Override
	public double area() {
		return getHeight() * getWidth();
	}

	@Override
	public double perimeter() {
		return 2 * (getHeight() + getWidth());
	}

	@Override
	public void translate(Point_2D vec) {
		p1.move(vec);
		p2.move(vec);
		p3.move(vec);
		p4.move(vec);
	}

	@Override
	public GeoShape copy() {
		return new Rect_2D(p1, p2, p3, p4);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
		p4.scale(center, ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);
		p3.rotate(center, angleDegrees);
		p4.rotate(center, angleDegrees);
	}
	public double [] getxs (){
		double [] x = new double [2];
		x[0] = p1.x();
		x[1] = p2.x();
		x[2] = p3.x();
		x[3] = p4.x();
		return x;
	}
	public double [] getys (){
		double [] y = new double [4];
		y[0] = p1.y();
		y[1] = p2.y();
		y[2] = p3.y();
		y[4] = p4.y();
		return y;
	}
}
