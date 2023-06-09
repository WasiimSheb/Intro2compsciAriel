package geo;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Polygon_2D implements GeoShape{
	private ArrayList <Point_2D> _points = new ArrayList<>();

	public Polygon_2D (ArrayList <Point_2D> points){
		for (int i = 0; i < points.size(); i++) {
			this._points.add(new Point_2D(points.get(i)));
		}
	}
	public Polygon_2D(Polygon_2D po) {
		for (int i = 0; i < po._points.size(); i++) {
			this._points.add(new Point_2D(po._points.get(i)));
		}
	}
	public Point_2D[] getAllPoints() {
		return _points.toArray(new Point_2D[_points.size()]);
	}

	public void add1(Point_2D p) {
		_points.add(p);
		new Polygon_2D(this._points);
	}

	@Override
	public String toString()
	{
		String s = "Polygon_2D{ ";
		for (int i = 0; i < _points.size(); i++) {
			s +="p" +(i+1)+ "= " + _points.get(i) + " ";
		}
		return s + "}";
	}

	@Override
	public boolean contains(Point_2D ot) {
		int intersectCount = 0;
		Point_2D lastPoint = _points.get(_points.size() - 1);
		for (Point_2D currentPoint : _points) {
			if ((currentPoint.y() > ot.y()) != (lastPoint.y() > ot.y())) {
				if (ot.x() < (lastPoint.x() - currentPoint.x()) * (ot.y() - currentPoint.y()) / (lastPoint.y() - currentPoint.y()) + currentPoint.x()) {
					intersectCount++;
				}
			}
			lastPoint = currentPoint;
		}
		return intersectCount % 2 == 1;
	}

	@Override
	public double area() {
		double area = 0;
		for(int i = 0;i < _points.size();i++) {
			Point_2D p1 = _points.get(i);
			Point_2D p2 = _points.get((i + 1) % _points.size());
			area += p1.x() * p2.y() - p2.x() * p1.y();
		}
		return Math.abs(area / 2);
	}

	@Override
	public double perimeter() {
		double ans = 0;
		for (int i = 0; i < _points.size() - 1; i++) {
			ans += _points.get(i).distance(_points.get(i + 1));
		}
		ans += _points.get(0).distance(_points.get(_points.size() - 1));
		return ans;
	}

	@Override
	public void translate(Point_2D vec) {
		for (int i = 0; i < _points.size(); i++) {
			_points.get(i).move(vec);
		}
	}
	@Override
	public GeoShape copy() {
		return new Polygon_2D(this._points);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		for (int i = 0; i < _points.size(); i++) {
			_points.get(i).scale(center, ratio);
		}
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < _points.size(); i++) {
			_points.get(i).rotate(center, angleDegrees);
		}
	}
	public static void main(String[] args) {
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
		boolean ans = h.contains(ch);
		System.out.println(ans);
	}

}
