import java.awt.*;

import java.net.InterfaceAddress;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import exe.ex3.game.Game;
import exe.ex3.game.GhostCL;
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

/**
 * This is the major algorithmic class for Ex3 - the PacMan game:
 * <p>
 * This code is a very simple example (random-walk algorithm).
 * Your task is to implement (here) your PacMan algorithm.
 */
public class Ex3Algo implements PacManAlgo {
	private int _count;

	public Ex3Algo() {
		_count = 0;
	}

	@Override
	/**
	 *  Add a short description for the algorithm as a String.
	 */
	public String getInfo() {
		return "This is an Algorithm which is made to make the Pacman each as fast as possible regarding the ghosts that are chasing him.";
	}

	@Override
	/**
	 * This ia the main method - that you should design, implement and test.
	 */
	public int move(PacmanGame game) {
		if (_count == 0 || _count == 300) {
			int code = 0;
			int[][] board = game.getGame(0);
			printBoard(board);
			int blue = Game.getIntColor(Color.BLUE, code);
			int pink = Game.getIntColor(Color.PINK, code);
			int black = Game.getIntColor(Color.BLACK, code);
			int green = Game.getIntColor(Color.GREEN, code);
			System.out.println("Blue=" + blue + ", Pink=" + pink + ", Black=" + black + ", Green=" + green);
			String pos = game.getPos(code);
			System.out.println("Pacman coordinate: " + pos);
			GhostCL[] ghosts = game.getGhosts(code);
			printGhosts(ghosts);
			int up = Game.UP, left = Game.LEFT, down = Game.DOWN, right = Game.RIGHT;
		}
		_count++;
		GhostCL[] gh = game.getGhosts(0);
		String t =(game.getPos(0));
		String [] r = t.split(",");
		int xx = Integer.parseInt(r[0]);
		int yy = Integer.parseInt(r[1]);
		Pixel2D p  = new Index2D(xx,yy);
		Pixel2D[] gp = new Pixel2D[gh.length];
		for (int i = 0; i < gh.length; i++) {
			String s = gh[i].getPos(0);
			String [] co = s.split(",");
			int x = Integer.parseInt(co[0]);
			int y = Integer.parseInt(co[1]);
			gp[i] = new Index2D(x,y);
		}
		int [] pinks = {5,3};
		Map k = new Map(game.getGame(0));
		Pixel2D close = k.eatpink(p, pinks, 1);
		Pixel2D[] sp = k.shortestPath(p, close, 1);
		return directions(k, p, sp[1]);
	}
	private static void printBoard(int[][] b) {
		for (int y = 0; y < b[0].length; y++) {
			for (int x = 0; x < b.length; x++) {
				int v = b[x][y];
				System.out.print(v + "\t");
			}
			System.out.println();
		}
	}
	private static void printGhosts(GhostCL[] gs) {
		for (int i = 0; i < gs.length; i++) {
			GhostCL g = gs[i];
			System.out.println(i + ") status: " + g.getStatus() + ",  type: " + g.getType() + ",  pos: " + g.getPos(0) + ",  time: " + g.remainTimeAsEatable(0));
		}
	}
	private static int randomDir() {
		int[] dirs = {Game.UP, Game.LEFT, Game.DOWN, Game.RIGHT};
		int ind = (int) (Math.random() * dirs.length);
		return dirs[ind];
	}

	/**
	 * this function is a private one for my use, which gives the Pacman the directions considering the cyclic mode in order to decide how to move.
	 * @param m a certain map
	 * @param p1 the starting point
	 * @param p2 the target point to reach
	 * @return
	 */
	private int directions (Map2D m , Pixel2D p1, Pixel2D p2){
		if (m.isCyclic()){
			if (p1.getX() == m.getWidth() - 1 && p2.getX() == 0) {
				return	Game.LEFT;
			}
			if (p1.getX() == 0 && p2.getX() == m.getWidth() - 1) {
				return Game.RIGHT;
			}
			if (p1.getY() == m.getHeight() - 1 && p2.getY() == 0) {
				return Game.UP;
			}
			if (p1.getY() == 0 && p2.getY() == m.getHeight() - 1) {
				return Game.DOWN;
			}
		}
		if ((p1.getX() == p2.getX()) && p1.getY() < p2.getY()) {
			return Game.UP;
		}
		if ((p1.getX() == p2.getX()) && p1.getY() > p2.getY()) {
			return Game.DOWN;
		}
		if ((p1.getY() == p2.getY()) && p1.getX() > p2.getX()) {
			return Game.LEFT;
		}
		if ((p1.getY() == p2.getY()) && p1.getX() < p2.getX()) {
			return Game.RIGHT;
		}
		return Game.PAUSE;
	}
}