package exe.ex3;

import java.util.Arrays;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D {
	private int[][] _map
	;
	private boolean _cyclicFlag = true;

	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {init(w,h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size) {this(size,size, 0);}

	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
		_map = new int[h][w]; // updating the map with the new height and width
		for (int i = 0; i < h; i++) { // running over the matrix
			for (int j = 0; j < w; j++) { // running over the matrix
				_map[i][j] = v; // updating the vlaues of the matrix with v
			}
		}
	}
	@Override
	public void init(int[][] arr) {
		if (arr == null){ // if the entered matrix is equal to null
			throw new RuntimeException(); // throwing a runtime exception
		}
		else{ // if the entered matrix is not equal to null
			_map = new int [arr.length][arr[0].length]; // creating a new matrix of _map
			for (int i = 0; i < arr.length; i++){ // running over the matrix
				for (int j = 0; j < arr[0].length; j++){ // running over the matrix
					_map[i][j] = arr[i][j]; // updating the arr values in _map
				}
			}
		}
	}
	@Override
	public int [][] getMap() {
		int x = _map.length; // saving the length of the map in an integer
		int y = _map[0].length; // saving the length of the map in an integer
		int [][] ans = new int [x][y]; // creating a new map with the length and width of the _map matrix
		for (int i = 0; i < ans.length; i++) { // running over the matrix
			for (int j = 0; j < ans[0].length; j++) { // running over the matrix
				ans [i][j] = _map[i][j]; // updating all the values of _map in the ans matrix
			}
		}
		return ans; // returning the ans matrix which is a copy of _map
	}
	@Override
	/////// add your code below ///////
	public int getWidth() {return _map.length;}
	@Override
	/////// add your code below ///////
	public int getHeight() {
		if (_map.length == 0) 
			return 0;
		else 
			return _map[0].length;
	}
	@Override
	/////// add your code below ///////
	public int getPixel(int x, int y) { 
		int ans = getMap()[x][y];
		return (int) ans;
	}
	@Override
	/////// add your code below ///////
	public int getPixel(Pixel2D p) {
		return (int) this.getPixel(p.getX(),p.getY());
	}
	@Override
	/////// add your code below ///////
	public void setPixel(int x, int y, int v) {
		_map[x][y] = v;
	}
	@Override
	/////// add your code below ///////
	public void setPixel(Pixel2D p, int v) {
		_map[p.getX()][p.getY()] = v;
	}
	private int floodfill1(int x ,int y , int oldcol, int newcol) {
		int count = 1;
		if (x >= this.getWidth() || x < 0 ||y >= this.getHeight() || y < 0 ) {
			return 0;
		}
		if(getPixel(x, y) != oldcol) {
			return 0;
		}
		setPixel(x, y, newcol);
		count += floodfill1(x + 1, y, oldcol, newcol);
		count += floodfill1(x - 1, y, oldcol, newcol);
		count += floodfill1(x, y + 1, oldcol, newcol);
		count += floodfill1(x, y - 1, oldcol, newcol);
		return count;
	}
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v) {
		int ans = 0;
		int x = xy.getX();
		int y = xy.getY();
		int originv = getPixel(x,y);
		if (getPixel(x,y) == new_v) {
			return ans;
		}
		ans = floodfill1(x, y, originv, new_v);
		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
		Pixel2D[] ans = null;  // the result.
		/////// add your code below ///////

		///////////////////////////////////
		return ans;
	}
	@Override
	/////// add your code below ///////
	public boolean isInside(Pixel2D p) {
		int z = _map.length; // the width of the matrix
		int c = _map[0].length - 1; // the height of the matrix
		Pixel2D targetX = new Index2D(_map.length, p.getY()); // creating a new pixel with x, y coordinates 
		Pixel2D startingX = new Index2D(0, p.getY()); // creating a new pixel with x, y coordinates
		Pixel2D targetY = new Index2D(p.getX(), _map[0].length); // creating a new pixel with x, y coordinates
		Pixel2D startingY = new Index2D(p.getX(), 0); // creating a new pixel with x, y coordinates
		int x = p.getX(); // getting the value of X coordinate of the pixel that is entered
		int y = p.getY(); // getting the value of Y coordinate of the pixel that is entered
		boolean ans = false; // initializing a boolean value to be false 
		// checking whether the distance between x coordinate and the first pilar of the map and the last that is equal to the width of the map
		if (Math.abs(p.distance2D(targetX)) + Math.abs(((p.distance2D(startingX)))) == _map.length){ 
			// checking whether the distance between x coordinate and the first line of the map and the last that is equal to the width of the map
			if (Math.abs(p.distance2D(startingY)) + Math.abs(((p.distance2D(targetY)))) == _map[0].length){
				ans = true; // updating the booolean value to true if the two equivalances have passed 
			}
		}
		return ans; // returning the final boolean value
	}
	@Override
	/////// add your code below ///////
	public boolean isCyclic() {
		return _cyclicFlag;
	}
	public static void main(String[] args) {
		int [][] ans1 = {{1,0,3},
						 {2,5,1},
						 {7,0,9}};
		Map y = new Map(ans1);
		System.out.println("the map is cycilically " + y.isCyclic());
	}
	@Override
	/////// add your code below ///////
	public void setCyclic(boolean cy) {
		this._cyclicFlag = cy;
	}
	public Pixel2D[] neighbors(Map2D t, Pixel2D p){
		int numRows = t.getWidth();
		int numCols = t.getHeight();
		ArrayList <Pixel2D> neighbors = new ArrayList<Pixel2D>();
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
		for (int[] direction : directions) {
			int newX = p.getX() + direction[0];
			int newY = p.getY() + direction[1];
			if (newX >= 0 && newX < numRows && newY >= 0 && newY < numCols) {
				Pixel2D neighborPixel = new Index2D(newX, newY); // Create a new Pixel2D object
				neighbors.add(neighborPixel);
			}
		}
		return neighbors.toArray(new Pixel2D[neighbors.size()]);
	}
	public boolean Validpixel(int x, int y, int obsColor) {
		return  (x >= 0 &&x < _map.length && y >= 0 &&y < _map[0].length && _map[x][y] != obsColor);
	}
	@Override
	/////// add your code below ///////
	public Map2D allDistance(Pixel2D start, int obsColor) {
		Map2D ans = new Map(_map.length, _map[0].length, -1); // creating a new map and initializing all the values in it to -1, in other words "did not visit yet!".
		Queue <Pixel2D> exp = new LinkedList(); // creating a queue of Pixel2D
		exp.add(start); // starting the queue with the starting pixel
		ans.setPixel(start, 0); // the distance between the the starting pixel and itself is 0 
		while (!exp.isEmpty()) { 
			Pixel2D current = exp.poll();
			int currentdist = ans.getPixel(current);
			Pixel2D [] neighboring = neighbors(this, current);
			for (int i = 0; i < neighboring.length; i++) {
				int neighboringx = neighboring[i].getX();
				int neighboringy = neighboring[i].getY();
				if (Validpixel(neighboringx, neighboringy, obsColor) && ans.getPixel(neighboring[i]) == -1) {
					int newdist = currentdist + 1;
					ans.setPixel(neighboring[i], newdist);
					exp.add(neighboring[i]);
				}
			}
		}
		return ans;
	}
}

