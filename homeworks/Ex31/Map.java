
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D {
	private int[][] _map;
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
		this._map = new int[w][h]; // updating the map with the new height and width
		for (int i = 0; i < w; i++) { // running over the matrix
			for (int j = 0; j < h; j++) { // running over the matrix
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

	public Pixel2D[] neighbors(Map2D t, Pixel2D p){
		int numRows = t.getWidth();
		int numCols = t.getHeight();
		ArrayList <Pixel2D> neighbors = new ArrayList<Pixel2D>();
		if (p.getX() > 0) {
			neighbors.add(new Index2D(p.getX() - 1, p.getY()));
		}
		if (p.getX() < t.getWidth()) {
			neighbors.add(new Index2D(p.getX() + 1, p.getY()));
		}
		if (p.getY() < t.getHeight()) {
			neighbors.add(new Index2D(p.getX(), p.getY() + 1));
		}
		if(p.getY() > 0) {
			neighbors.add(new Index2D(p.getX(), p.getY() - 1));
		}
		return neighbors.toArray(new Pixel2D[neighbors.size()]);
	}
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
		ArrayList<Pixel2D> path = new ArrayList<Pixel2D>(); // creating a new arrayList to check the pixels
		// checking the input if it's valid or not
		if (!this.isInside(p1) || !this.isInside(p2) || p1 == null || p2 == null || this.getPixel(p1) == obsColor || this.getPixel(p2) == obsColor) {
			return null;
		}
		boolean [][] visited = new boolean[getWidth()][getHeight()]; // new map of the visited and unvisited Pixels
		Pixel2D [][] check = new Pixel2D[getWidth()][getHeight()]; // new Map of the ShortestPath process
		this.setPixel(p1, 0); // the distance between the first Pixel and itself is 0
		path.add(p1); // adding the Pixel to the Checking ArrayList
		visited[p1.getX()][p1.getY()] = true; // we've visited the first Pixel
		while (!path.isEmpty()) { // while there is still neighbors to check
			Pixel2D current = path.remove(0); // popping the head of the Array
			if (current.equals(p2)) { // if we've reached the target
				break; // stopping the while loop
			}
			Pixel2D[] neighboring = neighbors(this, current); // saving all the neighbors of the Pixel current
			for (int i = 0; i < neighboring.length; i++) {
				int newx = neighboring[i].getX(); // saving the X's value
				int newy = neighboring[i].getY(); // saving the Y's value
				// checking the neighboring Pixel if it is valid or not
				if (Validpixel(newx, newy, obsColor) && !visited[newx][newy]) {
					path.add(neighboring[i]); // adding the legitimate neighbor for a new check of it's neighbors
					visited[newx][newy] = true; // we've visited the neighboring Pixel
					check[newx][newy] = current; // saving the current in the neighboring Pixels
				}
			}
		}
		if (visited[p2.getX()][p2.getY()]) { // if the target is reachable
			ArrayList <Pixel2D> reversedpath = new ArrayList<>(); // creating a reversed ArrayList
			Pixel2D current = p2; // updating the current value to be p2 to start from the end
			while (current != null) { // While we have not reached the p1 target
				reversedpath.add(current); // adding to the reversed ArrayList
				current = check[current.getX()][current.getY()]; // updating the current in order to save in the reversed ArrayList
			}
			Collections.reverse(reversedpath); // reversing the reversed ArrayList in order to get the path in order
			return reversedpath.toArray(new Pixel2D[reversedpath.size()]); // turning the ArrayList into an array and returning it
		}
		else {
			return null; // No path found
		}
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

	@Override
	/////// add your code below ///////
	public void setCyclic(boolean cy) {
		this._cyclicFlag = cy;
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
		ans.setPixel(start, 0); // the distance between the starting pixel and itself is 0
		while (!exp.isEmpty()) { // while there is still nodes to check and not all has been visited
			Pixel2D current = exp.poll(); // removing the head of the queue
			int currentdist = ans.getPixel(current); // saving the value of the pixel in an integer
			Pixel2D [] neighboring = neighbors(this, current); // getting all the neighbors of the current pixel
			for (int i = 0; i < neighboring.length; i++) { // running over the neighbors
				int neighboringx = neighboring[i].getX(); // saving the value of X of the first neighbor in the array
				int neighboringy = neighboring[i].getY(); // saving the value of Y of the first neighbor in the array
				// checking whether the pixel is valid or not and not yet visited
				if (Validpixel(neighboringx, neighboringy, obsColor) && ans.getPixel(neighboring[i]) == -1) {
					int newdist = currentdist + 1;	// updating how far we've got from the starting pixel
					ans.setPixel(neighboring[i], newdist); // updating the new distance in the map
					exp.add(neighboring[i]); // adding a new neighbor for the check;
				}
			}
		}
		return ans; // returning the new created map;
	}
	/**
	 * this algorithm is made to make the Pacman eat pink and green pixels as much as he can
	 * @param current
	 * @param pg
	 * @param obsColor
	 * @return
	 */
	protected Pixel2D eatpink(Pixel2D current, int [] pg , int obsColor) {
		Queue<Pixel2D> pro = new LinkedList<>(); // creating a  new queue for checking
		boolean[][] visited = new boolean[getWidth()][getHeight()]; // creating a new 2D array to handle the visited and unvisited Pixels
		pro.add(current); // adding the current position of checking
		int width = this.getWidth(); // the width of the map
		int height = this.getHeight(); // the height of the map
		while (!pro.isEmpty()) { // while there are still pixels we have not visited
			Pixel2D temp = pro.remove(); // processing the head of the queue
			if (visited[temp.getX()][temp.getY()]){ // checking whether the pixel have been visited or not
				continue; // moving to the next iteration if it has been already visited
			}
			visited[current.getX()][current.getY()] = true; // the current position has been visited
			// creating a left pixel of the current one using the arithmetic modular map to handle the cyclic mode
			int x_l = (temp.getX() - 1 + width) % width;
			int y_l = (temp.getY() + height) % height;
			Pixel2D left = new Index2D(x_l, y_l);
			// creating a right pixel of the current one using the arithmetic modular map to handle the cyclic mode
			int x_r = (temp.getX() + 1 + width) % width;
			int y_r = (temp.getY() + height) % height;
			Pixel2D right = new Index2D(x_r, y_r);
			// creating a lower pixel of the current one using the arithmetic modular map to handle the cyclic mode
			int x_d = (temp.getX() + width) % width;
			int y_d = (temp.getY() - 1 + height) % height;
			Pixel2D down = new Index2D(x_d, y_d);
			// creating an upper pixel of the current one using the arithmetic modular map to handle the cyclic mode
			int x_u = (temp.getX() + width) % width;
			int y_u = (temp.getY() + 1 + height) % height;
			Pixel2D up = new Index2D(x_u, y_u);
			// checking whether the right pixel is valid, not an obstacle, is and eatable pixel.
			if ((this.isCyclic() || isInside(right)) && getPixel(right) != obsColor  && (getPixel(right) == pg[0] || getPixel(right) == pg[1])) {
				return right; // if so go right;
			}
			// checking whether the left pixel is valid, not an obstacle, is and eatable pixel.
			if ((this.isCyclic() || isInside(left)) && getPixel(left) != obsColor  && (getPixel(left) == pg[0] || getPixel(left) == pg[1])) {
				return left; // if so go left
			}
			// checking whether the upper pixel is valid, not an obstacle, is and eatable pixel.
			if ((this.isCyclic() || isInside(up)) && getPixel(up) != obsColor && (getPixel(up) == pg[0] || getPixel(up) == pg[1])) {
				return up; // if so go up
			}
			// checking whether the lower pixel is valid, not an obstacle, is and eatable pixel.
			if ((this.isCyclic() || isInside(down)) && getPixel(down) != obsColor && (getPixel(down) == pg[0] || getPixel(down) == pg[1])) {
				return down; // if so go down
			}
			// checking whether the neighboring pixels are obstacles or not and adding them for a futher check
			if (getPixel(right) != obsColor)
				pro.add(right);
			if (getPixel(left) != obsColor)
				pro.add(left);
			if (getPixel(up) != obsColor) {
				pro.add(up);
			}
			if (getPixel(down) != obsColor) {
				pro.add(down);
			}
		}
		return null;
	}
}

