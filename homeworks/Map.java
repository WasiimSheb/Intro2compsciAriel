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
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v) {
		int ans = 0;
		/////// add your code below ///////

		///////////////////////////////////
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
//	public static void main(String[] args) {
//		Map map= new Map(5,5,0);
//		Index2D p1= new  Index2D(7,3);
//		System.out.println("Is p2 inside the map? " + map.isInside(p1));
//		Index2D p2= new  Index2D(6,4);
//		System.out.println("Is p2 inside the map? " + map.isInside(p2));
//	}

	@Override
	/////// add your code below ///////
	public boolean isCyclic() {
		boolean ans1 = false;
		boolean ans2 = false;
		boolean ans3 = false;
		boolean ans4 = false;
		Pixel2D right = null;
		Pixel2D left = null;
		Pixel2D above = null;
		Pixel2D below = null;
		for ( int i = 0; i < _map.length; i++){
			for (int j = 0; j < _map[0].length; j++) {
				if(getPixel(i, j) == 0 && j == 0){
					left = new Index2D (i, getWidth() - 1);
				}
				if (getPixel(i, j) == 0 && j == getWidth() - 1){
					right = new Index2D(i, 0);
				}
				if (getPixel(i, j) == 0 && i == getHeight() - 1){
					above = new Index2D(0, j);
				}
				if (getPixel(i, j) == 0 && i == 0){
					below = new Index2D(getHeight() - 1, j);
				}
			}
		}
		for ( int i = 0; i < _map.length; i++){
			for (int j = 0; j < _map[0].length; j++) {
				Pixel2D random = new Index2D(i, j);
				if (getPixel(i, j) == 0 && j == 0 && random.equals(right)) {
					ans1 = true;
				}
				if (getPixel(i, j) == 0 && j == getWidth() - 1 && random.equals(left)) {
					ans2 = true;
				}
				if (getPixel(i, j) == 0 && i == getHeight() - 1 && random.equals(below)) {
					ans3 = true;
				}
				if (getPixel(i, j) == 0 && i == 0 && random.equals(above)) {
					ans4 = true;
				}
			}
		}
		return (ans1 && ans2) || (ans3 && ans4);
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
		Pixel2D [] ans = null;
		int count =0;
		Pixel2D e = new Index2D(p.getX(), p.getY());
		return ans;
	}
	@Override
	/////// add your code below ///////
	public Map2D allDistance(Pixel2D start, int obsColor) {
		Map2D ans = null;  // the result.
		/////// add your code below ///////

		///////////////////////////////////
		return ans;
	}
}

