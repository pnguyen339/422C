package assignment1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class Tester {
	
	/**
	 * Test isSorted
	 * 8 tests
	 * Eliminate tests 3 & 4, they are normal use
	 */
	
	
	@Test(timeout = 2000)
	public void testIsSortedWithOneElement(){
		int[] x = new int[]{ 0 };
		//assertEquals(true, SortTools.isSorted(x, 1)); // n = 1
		SortTools.isSorted(x, 1);
	}
	
	// A very ordinary case
	@Test(timeout = 2000)
	public void testIsSortedNegInOrder(){
		int[] x = new int[]{-1, 0, 1};
		assertEquals(true, SortTools.isSorted(x, 3));
	}

	
	// A very ordinary case
	@Test(timeout = 2000)
	public void testIsSortedNegRevOrder(){
		int[] x = new int[]{1, 0, -1};
		assertEquals(false, SortTools.isSorted(x, 3));
	}

	
	@Test(timeout = 2000)
	public void testIsSortedPartial(){
		int[] x = new int[]{3, 4, 5, 1, 2, 3};
		assertEquals(true, SortTools.isSorted(x, 3));
	}
	
	@Test(timeout = 2000)
	public void testIsSortedFull(){
		int[] x = new int[]{3, 4, 5, 1, 2, 3};
		assertEquals(false, SortTools.isSorted(x, 6));
	}
	
	@Test(timeout = 2000)
	public void testIsSortedRepeatedPartial(){
		int[] x = new int[]{5, 5, 5, 3, 3, 3};
		System.out.println(SortTools.isSorted(x, 3));
		assertEquals(true, SortTools.isSorted(x, 3));
	}
	
	@Test(timeout = 2000)
	public void testIsSortedRepeatedFull(){
		int[] x = new int[]{5, 5, 5, 3, 3, 3};
		assertEquals(false, SortTools.isSorted(x, 6));
	}
	
	/**
	 * Test find
	 * 6 tests, all kept
	 */
	@Test(timeout = 2000)
	public void testFindFoundFull(){
		int[] x = new int[]{-2, -1, 0, 1, 2, 3};
		assertEquals(3, SortTools.find(x, 6, 1));
	}
	
	@Test(timeout = 2000)
	public void testFindNotFoundFull(){
		int[] x = new int[]{-5, -4, -3, -2, -1};
		assertEquals(-1, SortTools.find(x, 5, 0));
	}
	
	@Test(timeout = 2000)
	public void testFindFoundPartial(){
		int[] x = new int[]{1, 3, 5, 7, 9};
		assertEquals(1, SortTools.find(x, 3, 3));
	}
	
	@Test(timeout = 2000)
	public void testFindNotFoundPartial(){
		int[] x = new int[]{2, 4, 6, 8, 10};
		System.out.println(SortTools.find(x, 4, 2));
		assertEquals(0, SortTools.find(x, 4, 2));
	}
	
	@Test(timeout = 2000)
	public void testFindFoundBeginning(){
		int[] x = new int[]{1, 2, 3, 4, 5};
		assertEquals(0, SortTools.find(x, 5, 1));
	}
	
	@Test(timeout = 2000)
	public void testFindFoundEnd(){
		int[] x = new int[]{10, 20, 30, 40, 50};
		assertEquals(4, SortTools.find(x, 5, 50));
	}
	
	/**
	 * test insertGeneral
	 * 8 tests
	 * Eliminated the first two tests, I think the "Full" variety will be more tested for than partial
	 */
	//Normal use
	//@Test(timeout = 2000)
	public void testInsertGeneralFullBeginning(){
		int[] x = new int[]{1, 2, 3, 4, 5};
		int[] expected = new int[]{0, 1, 2, 3, 4, 5};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 5, 0));
	}

	
	//Normals
	@Test(timeout = 2000)
	public void testInsertGeneralFullMiddle(){
		int[] x = new int[]{-1, 0, 2, 3, 4};
		int[] expected = new int[]{-1, 0, 1, 2, 3, 4};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 5, 1));
	}

	
	@Test(timeout = 2000)
	public void testInsertGeneralFullEnd(){
		int[] x = new int[]{-4, -3, -2, -1};
		int[] expected = new int[]{-4, -3, -2, -1, 0};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 4, 0));
	}
	
	//@Test(timeout = 2000)
	public void testInsertGeneralFullNoInsert(){
		int[] x = new int[]{2, 4, 6, 8, 10};
		int[] expected = x.clone();
		assertArrayEquals(expected, SortTools.insertGeneral(x, 5, 6));
	}
	
	//@Test(timeout = 2000)
	public void testInsertGeneralPartialBeginning(){
		int[] x = new int[]{1, 3, 5, 7, 9};
		int[] expected = new int[]{0, 1, 3, 5};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 3, 0));
	}
	
	//@Test(timeout = 2000)
	public void testInsertGeneralPartialMiddle(){
		int[] x = new int[]{-4, -3, -1, 0};
		int[] expected = new int[]{-4, -3, -2, -1};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 3, -2));
	}
	
	
	@Test(timeout = 2000)
	public void testInsertGeneralPartialEnd(){
		int[] x = new int[]{10, 20, 30, 40, 50};
		int[] expected = new int[]{10, 20, 30, 35};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 3, 35));
	}
	
	//@Test(timeout = 2000)
	public void testInsertGeneralPartialNoInsert(){
		int[] x = new int[]{1, 1, 2, 3, 5, 8};
		int[] expected = new int[]{1, 1, 2, 3};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 4, 2));
	}
	
	/**
	 * Test insertInPlace
	 * 8 tests
	 * Eliminate tests 1 & 2, seem to be the normal/easiest use
	 */
	@Test(timeout = 2000)
	public void testInsertInPlaceFullBeginning(){
		int[] x = new int[]{1, 2, 3, 4, 5, 0};
		int[] expected = new int[]{-5, 1, 2, 3, 4, 5};
		int ret = SortTools.insertInPlace(x, 5, -5);
		assertArrayEquals(expected, x);
		assertEquals(6, ret);
	}

	
	@Test(timeout = 2000)
	public void testInesrtInPlaceFullMiddle(){
		int[] x = new int[]{1, 1, 2, 3, 5, 8, 0};
		int[] expected = new int[]{1, 1, 2, 3, 4, 5, 8};
		int ret = SortTools.insertInPlace(x, 6, 4);
		assertArrayEquals(expected, x);
		assertEquals(7, ret);
	}

	
	@Test(timeout = 2000)
	public void testInsertInPlaceFullEnd(){
		int[] x = new int[]{-5, -4, -3, -2, -1, 0};
		int[] expected = new int[]{-5, -4, -3, -2, -1, 5};
		int ret = SortTools.insertInPlace(x, 5, 5);
		assertArrayEquals(expected, x);
		assertEquals(6, ret);
	}
	
	@Test(timeout = 2000)
	public void testInsertInPlaceFullNoInsert(){
		int[] x = new int[]{1, 2, 3, 4, 5, 6};
		int[] expected = x.clone();
		int ret = SortTools.insertInPlace(x, 6, 5);
		assertArrayEquals(expected, x);
		assertEquals(6, ret);
	}
	
	//@Test(timeout = 2000)
	public void testInsertInPlacePartialBeginning(){
		int[] x = new int[]{3, 6, 9, 12, 15};
		int[] expected = new int[]{-3, 3, 6, 9};
		int ret = SortTools.insertInPlace(x, 3, -3);
		assertArrayEquals(expected, Arrays.copyOfRange(x, 0, 4)); // Subarray of x
		assertEquals(4, ret);
	}
	
	@Test(timeout = 2000)
	public void testInsertInPlacePartialMiddle(){
		int[] x = new int[]{2, 4, 6, 8, 10};
		int[] expected = new int[]{2, 3, 4, 6};
		int ret = SortTools.insertInPlace(x, 3, 3);
		assertArrayEquals(expected, Arrays.copyOfRange(x, 0, 4)); // Subarray of x
		assertEquals(4, ret);
	}
	
	//@Test(timeout = 2000)
	public void testInsertInPlacePartialEnd(){
		int[] x = new int[]{-5, -4, -3, -2, 1};
		int[] expected = new int[]{-5, -4, -3, -2, -1};
		int ret = SortTools.insertInPlace(x, 4, -1);
		assertArrayEquals(expected, Arrays.copyOfRange(x, 0, 5));
		assertEquals(5, ret);
	}
	
	@Test(timeout = 2000)
	public void testInsertInPlacePartialNoInsert(){
		int[] x = new int[]{-2, -1, 0, 1, 2};
		int[] expected = new int[]{-2, -1, 0, 1};
		int ret = SortTools.insertInPlace(x, 4, 0);
		assertArrayEquals(expected, Arrays.copyOfRange(x, 0, 4));
		assertEquals(4, ret);
	}
	
	/**
	 * Test insertSort
	 * 8 tests
	 * Eliminate 1st & 3rd b/c "worst case" is more so for time complexity & we will look at their algorithm
	 */
	@Test(timeout = 2000)
	public void testInsertSortFullWorstCase(){
		int[] x = new int[]{5, 4, 3, 2, 1, 0};
		int[] expected = new int[]{0, 1, 2, 3, 4, 5};
		SortTools.insertSort(x, 6);
		assertArrayEquals(expected, x);
	}

	
	@Test(timeout = 2000)
	public void testInsertSortFullRandom(){
		int[] x = {-23, 12, 32, 5, 0, -2, 1203, 4, 62, 170, 34, 12, 12, 4};
		int[] expected = x.clone(); Arrays.sort(expected);
		SortTools.insertSort(x, x.length);
		assertArrayEquals(expected, x);		
	}
	
	@Test(timeout = 2000)
	public void testInsertSortPartialWorstCase(){
		int[] x = new int[]{5, 4, 3, 2, 1, -1, -2};
		int[] expected = new int[]{1, 2, 3, 4, 5, -1, -2};
		SortTools.insertSort(x, 5);
		assertArrayEquals(expected, x);
	}

	
	@Test(timeout = 2000)
	public void testInsertSortPartialRandom(){
		int[] x = new int[]{12, 324, 23, 23, 0, 0, -2, -3, -4};
		int[] expected = Arrays.copyOfRange(x, 0, 5); Arrays.sort(expected);
		SortTools.insertSort(x, 5);
		assertArrayEquals(expected, Arrays.copyOfRange(x, 0, 5));
	}
	
	@Test(timeout = 2000)
	public void testInsertSortNoSortFull(){
		int[] x = new int[]{-2, -1, 0, 1, 2, 3};
		int[] expected = x.clone();
		SortTools.insertSort(x, 6);
		assertArrayEquals(expected, x);
	}
	
	@Test(timeout = 2000)
	public void testInsertSortNoSortPartial(){
		int[] x = new int[]{-2, -1, 0, 1, 2, 3};
		int[] expected = x.clone();
		SortTools.insertSort(x, 6);
		assertArrayEquals(expected, x);
	}
	
	@Test(timeout = 2000)
	public void testInsertSortSingleNoElements(){
		int[] x = new int[]{ 1 };
		int[] expected = x.clone();
		SortTools.insertSort(x, 0);
		assertArrayEquals(expected, x);
	}
	
	@Test(timeout = 2000)
	public void testInsertSortSingle(){
		int[] x = new int[] { 1 };
		int[] expected = x.clone();
		SortTools.insertSort(x, 1);
		assertArrayEquals(expected, x);
	}
	
	// test Big-O => Optional

	@Test(timeout = 2500)
	public void testWorstCaseTime() {
		int [] x;
		int size = 100000;
		x = new int[size];
		
		initializeArrayToWorst(x);
		long startTime = System.currentTimeMillis();
		SortTools.insertSort(x, size/20);
		long elapsed = System.currentTimeMillis() - startTime;
		System.out.println("w1 " + elapsed);

		initializeArrayToWorst(x);
		startTime = System.currentTimeMillis();
		SortTools.insertSort(x, size/10);
		elapsed = System.currentTimeMillis() - startTime;
		System.out.println("w2 " + elapsed);
		
		initializeArrayToWorst(x);
		startTime = System.currentTimeMillis();
		SortTools.insertSort(x, size/5);
		elapsed = System.currentTimeMillis() - startTime;
		System.out.println("w3 " + elapsed);
		long t1 = elapsed;
		
		initializeArrayToWorst(x);
		startTime = System.currentTimeMillis();
		SortTools.insertSort(x, (int)(size/2.5));
		elapsed = System.currentTimeMillis() - startTime;
		System.out.println("w4 " + elapsed);
		long t2 = elapsed;
		
		assertTrue((t2/t1) < 4.5);
		
	}
	
	@Test(timeout = 2000)
	public void testAlmostSortedArray () {
		int [] x;
		int size = 100000;
		x = new int[size];
		long startTime, elapsed;
		
		initializeArrayToBest(x);
		startTime = System.currentTimeMillis();
		SortTools.insertSort(x, (int)(size/5));
		elapsed = System.currentTimeMillis() - startTime;
		System.out.println("b1 " + elapsed);
		long t3 = elapsed;
		
		initializeArrayToBest(x);
		startTime = System.currentTimeMillis();
		SortTools.insertSort(x, (int)(size/2.5));
		elapsed = System.currentTimeMillis() - startTime;
		System.out.println("b2 " + elapsed);
		long t4 = elapsed;
		
		assertTrue((t4/t3) < 2.2);
		
	}
	
	private void initializeArrayToWorst(int [] y) {
		for (int i = y.length -1; i >= 0; i-- ) {
			y[y.length-i-1] = i;
		}
	}
	
	private void initializeArrayToBest(int [] y) {
		for (int i = y.length -1; i >= 0; i-- ) {
			y[i] = i;
			if (i%1000 == 0) 
				y[i] = 0;
		}
	}

}