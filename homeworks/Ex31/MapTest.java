package exe.ex3;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MapTest {

	@Test
	void testInit() {
		Map matrix = new Map(2,2,3);
		int [][] expected = {{3,3},{3,3}};
		int [][] equals = matrix.getMap();
		assertArrayEquals(equals, expected);
	}

	@Test
	void testInitArray() {
		int [][] expected = {{2,2},{2,2}};
		Map matrix = new Map(expected);
		int [][] result = matrix.getMap();
		int [][] arr = null;
		assertThrows(RuntimeException.class, () -> new Map(arr));
		assertArrayEquals(expected, result);
	}

	@Test
	void testGetMap() {
		Map mat = new Map(2,2,0);
		int [][] result = {{0,0},{0,0}};
		int [][] expected = mat.getMap();
		assertArrayEquals(expected, result);
	}

	@Test
	void testGetWidth() {
		int [][] arr = {{2,3},{4,5}};
		Map x = new Map(arr);
		int z = x.getWidth();
		assertEquals(z, 2);
	}

	@Test
	void testGetHeight() {
		int [][] arr = {{2,3},{4,5}};
		Map x = new Map(arr);
		int z = x.getHeight();
		assertEquals(z, 2);
	}

	@Test
	void testGetPixelIntInt() {
		int [][] ans = {{1,2,3},{4,5,6},{7,8,9}};
		Map a = new Map(ans);
		int value = a.getPixel(1, 1);
		assertEquals(value, 5);
		int value2 = a.getPixel(2, 2);
		assertEquals(value2, 9);
		assertThrows(RuntimeException.class, () -> a.getPixel(4, 4));
	}

	@Test
	void testGetPixelPixel2D() {
		int [][] ans = {{1,2,3},{4,5,6},{7,8,9}};
		Map a = new Map(ans);
		Pixel2D r = new Index2D(0,0);
		int value = a.getPixel(r);
		assertEquals(value, 1);
		Pixel2D d = new Index2D(2,2);
		int value2 = a.getPixel(d);
		assertEquals(value2, 9);
	}

	@Test
	void testSetPixelIntIntInt() {
		int [][] ans1 = {{1,2,3},{4,5,6},{7,8,9}};
		Map expert = new Map(ans1);
		int v = 5;
		Pixel2D t = new Index2D(1,1);
		expert.setPixel(t, v);
		int [][] ans2 = expert.getMap();
		assertArrayEquals(ans2, ans1);
		int v2 = 9;
		Pixel2D e = new Index2D(2,2);
		Map expert2 = new Map(ans1);
		expert2.setPixel(e, v2);
		int [][] ans3 = expert2.getMap();
		assertArrayEquals(ans3, ans1);
	}

	@Test
	void testSetPixelPixel2DInt() {
		int [][] ans1 = {{1,2,3},{4,5,6},{7,8,9}};
		Map expert = new Map(ans1);
		int v1 = 9;
		expert.setPixel(2, 2, v1);
		int v2 = 2;
		expert.setPixel(0, 1,v2);
		int [][] ans2 = expert.getMap();
		assertArrayEquals(ans1, ans2);
	}

	@Test
	void testFill() {
		fail("Not yet implemented");
	}

	@Test
	void testShortestPath() {
		fail("Not yet implemented");
	}

	@Test
	void testIsInside() {
		Map w = new Map(3,3,5);
		Pixel2D t1 = new Index2D(3,3);
		Pixel2D t2 = new Index2D(0,3);
		Pixel2D t3 = new Index2D(3,0);
		Pixel2D t4 = new Index2D(0,0);
		Pixel2D t5 = new Index2D(6,6);
		Pixel2D t6 = new Index2D(5,5);
		Pixel2D t7 = new Index2D(0,5);
		boolean ans1 = w.isInside(t1);
		boolean ans2 = w.isInside(t2);
		boolean ans3 = w.isInside(t3);
		boolean ans4 = w.isInside(t4);
		boolean ans5 = w.isInside(t5);
		boolean ans6 = w.isInside(t6);
		boolean ans7 = w.isInside(t7);
		if (!ans1) {fail();}
		if (!ans2) {fail();}
		if (!ans3) {fail();}
		if (!ans4) {fail();}
		if (ans5) {fail();}
		if (ans6) {fail();}
		assertTrue(!ans7);
	}

	@Test
	void testIsCyclic() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCyclic() {
		int [][] ans1 = {{1,2,3},{4,5,6},{7,8,9}};
		Map expert = new Map(ans1);
		boolean cy = true;
		boolean cy1 = false;
		expert.setCyclic(cy);
		assertFalse(expert.isCyclic());
		assertFalse(expert.isCyclic());
	}

	@Test
	void testAllDistance() {
		fail("Not yet implemented");
	}

}
