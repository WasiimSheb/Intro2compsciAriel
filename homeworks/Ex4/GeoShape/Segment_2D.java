package geo;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	private Point_2D _p1;
	private Point_2D _p2;
	private double a,b;
	public Segment_2D(Point_2D a1, Point_2D b1) {
		this._p1 = new Point_2D(a1);
		this._p2 = new Point_2D(b1);
		this.a = (_p1.y() - _p2.y()) / (_p1.x() - _p2.x());
		this.b = _p1.y() - a * _p1.x();
	}
	public Segment_2D(Segment_2D t1) {
		Point_2D p1 = new Point_2D(t1.get_p1());
		Point_2D p2 = new Point_2D(t1.get_p2());
		this._p1 = p1;
		this._p2 = p2;
	}
	public Point_2D[] getAllPoints(){
		Point_2D [] ans = new Point_2D[2];
		ans[0] = _p1;
		ans[1] = _p2;
		return ans;
	}

	public Point_2D get_p1() {
		return this._p1;
	}
	public Point_2D get_p2() {
		return this._p2;
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	@Override
	public boolean contains(Point_2D ot) {
		return this._p1.distance(ot) + this._p2.distance(ot) == this._p2.distance(_p1);
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		if(this._p1.equals(this._p2)){
			return 0;
		}
		return _p1.distance(_p2) * 2;
	}

	@Override
	public void translate(Point_2D vec) {
	_p1.move(vec);
	_p2.move(vec);
	}

	@Override
	public GeoShape copy() {
		return new Segment_2D(this._p1, this._p2);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
	}

	@Override
	public String toString() {
		return "Segment_2D{" +
				"_p1=" + _p1 +
				", _p2=" + _p2 +
				'}';
	}

	public static void main(String[] args) {
		Point_2D r1 = new Point_2D(1,1);
		Point_2D r2 = new Point_2D(3,1);
		Point_2D r3 = new Point_2D(0,2);
		Point_2D origin = new Point_2D(0,0);
		Segment_2D m = new Segment_2D(r1, r2);
		m.translate(r3);
		System.out.println(m.get_p1() + " " + m.get_p2());
	}
}