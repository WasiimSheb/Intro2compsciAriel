package ex4;

import gui.GUIShape;
import gui.GUI_Shape;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection{
	private ArrayList<GUI_Shape> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) {
		this._shapes.remove(_shapes.get(i));
		return null;
	}

	@Override
	public void addAt(GUI_Shape s, int i) {
		if( s != null && s.getShape() != null)
			_shapes.add(i, s);
	}
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public GUI_Shape_Collection copy() {
		ArrayList<GUI_Shape> ans = new ArrayList<>();
		for (int i = 0; i < _shapes.size(); i++) {
			ans.add(_shapes.get(i));
		}
		return null;
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		//Collections.sort(_shapes, comp);
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		_shapes.removeAll(_shapes);
	}

	@Override
	public void save(String file) {
		try (FileWriter f = new FileWriter(file)) {
			for (GUI_Shape shape : _shapes) {
				f.write(shape.toString() + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String file) {
		_shapes.clear();
		try (BufferedReader b = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = b.readLine()) != null) {
				_shapes.add(new GUIShape(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i = 0;i < size();i = i + 1) {
			ans += this.get(i);
		}
		return ans;
	}
	

}
