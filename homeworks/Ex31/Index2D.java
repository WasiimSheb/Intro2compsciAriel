package exe.ex3;

public class Index2D implements Pixel2D{
	private int _x, _y;
	public Index2D() {this(0,0);}
	public Index2D(int x, int y) {_x=x;_y=y;}
	public Index2D(Pixel2D t) {this(t.getX(), t.getY());}
	@Override
	public int getX() {
		return _x;
	}
	@Override
	public int getY() {
		return _y;
	}
	public double distance2D(Pixel2D t) {
		double ans;
		if (t == null )
		{
			throw new RuntimeException();
		}
		double x = Math.pow(this._x - t.getX(), 2);
		double y = Math.pow(this._y - t.getY(), 2);
		ans = Math.sqrt(x + y);
		return ans;
	}
	@Override
	public String toString() {
		return getX()+","+getY();
	}
	@Override
	public boolean equals(Object t) {
		boolean ans = false;
		if (t instanceof Pixel2D && t != null)
		{
			Pixel2D p = (Pixel2D) t;
			ans = this.distance2D(p) == 0;
		}
		return ans;
	}
}
