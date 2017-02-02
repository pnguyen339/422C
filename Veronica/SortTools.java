// SortTools.java 
/*
 * EE422C Project 1 submission by
 * Replace <...> with your actual data.
 * <Student Name>
 * <Student EID>
 * <5-digit Unique No.>
 * Spring 2017
 * Slip days used: 
 */

package assignment1;
public class SortTools {
	/**
	  * This method tests to see if the given array is sorted.
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @return true if array is sorted
	  */
	public static boolean isSorted(int[] x, int n) {
		if x.length == 0 || n== 0{
			return false;
		}
		else {
			for (int i = 0; i<n; i++){
				if (x[i] < x[i+1]){
					return true;
				}
			}
			return false;
		}
	}
	
	// more functions required
}
