// SortTools.java 
/*
 * EE422C Project 1 submission by
 * Replace <...> with your actual data.
 * <Student Name>
 * <Student EID>
 * <5-digit Unique No.>
 * Fall 2016
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
		for(int i = 0; i<n-1; i++){
			if(x[i]<=x[i+1])
				return false;
		
		}	
		return true;
	}
	/**
	  * This method is use to find the number v in arrray x
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @param v is the number needed to be find
	  * @return the index where v is located
	  */
	public int find(int[] x, int n, int v){
		int lower = 0;
		int higher = n;
		
		while(lower!=higher){
			if(lower+1 == higher){
				if(x[lower] == v)
					return lower;
				else if(x[higher] == v)
					return higher;
				else
					return -1;
			}
				
			int mid = (lower+higher)/2;
			if(x[mid] == v)
				return mid;
			else if(x[mid] > v)
				higher = mid;
			else if(x[mid] < v)
				lower = mid;

		}
		return -1;
	}


	/**
	  * This method insert v into array x if array x do not contain v
	  * retrun a copy
	  * @param x is the array
	  * @param n is the length of array x
	  * @param v is the value that ened to be insert	
	  * @return a newly created array copy of x with v
	  */



	public int[] insertGeneral(int[] x, int n, int v){
		if(find(x,n,v) == -1){
			int[] copy = new int[n+1];
			int copy_index = 0;
			int i;
			for(i=0; i<= n;i++){
				if(v<x[i] || i == n){
					copy[copy_index] = v;
					copy_index++;
					break;	
				}
				else{
					copy[copy_index] = x[i];
					copy_index++;
				}
			}
			
			while(i<n){
				copy[copy_index] = x[i];
				i++; copy_index++;
			}
			return copy;

		}
		else{
			int[] copy = new int[n];
			for(int i=0; i< n;i++){
					copy[i] = x[i];
			}
			return copy;

		}

	}	
	

	/**
	  * This method insert v into array x if array x do not contain v
	  * @param x is the array
	  * @param n is the length of array x
	  * @param v is the value that ened to be insert	
	  * @return a modified array of x with v
	  */



	public int insertInPlace(int[] x, int n, int v){
		if(find(x,n,v) == -1){
			int copy = 0;
			int i;
			for(i=0; i<= n;i++){
				if(v<x[i] || i == n){
					copy = x[i];
					x[i] = v;
					break;	
				}
			}
			
			while(i<n){
				int copy2 = x[i];
				x[i] = copy;
				copy = copy2;	
				i++; 
			}
			return n+1;

		}
		else
			return n;

	}














}
