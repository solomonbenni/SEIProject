package com.sei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Solomon Benni
 *
 */ 
public class DeDup {

	public int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};

	public static void main(String[] args) {
		DeDup deDup = new DeDup();
		
		long t0 = System.nanoTime();
		int[] removedDuplicatesOne =  deDup.removeDuplicatesWithArrays(deDup.randomIntegers);
		System.out.println("Nano diff with removeDuplicatesWithArrays :: "+ (System.nanoTime()-t0) );
		
		t0 = System.nanoTime();
		int[] removedDuplicatesTwo =  deDup.removeDuplicatesWithHashArrayList(deDup.randomIntegers);
		System.out.println("Nano diff with removeDuplicatesWithHashSet :: "+ (System.nanoTime()-t0) );
		
		t0 = System.nanoTime();
		int[] removedDuplicatesThree =  deDup.removeDuplicatesWithLinkedHashSet(deDup.randomIntegers);
		System.out.println("Nano diff with removeDuplicatesWithLinkedHashSet :: "+ (System.nanoTime()-t0) );

	}
	
	/**
	 * Actually, java arrays are even faster than using Set/ArrayList of Collections
	 * This is one of the approach, here taken a simple built in
	 * sorting(here we can choose and work with different sorting techniques manually) and comparing side by side elements 
	 * and whenever we find duplicate element just replace with last element of the array, it will not returned order elements.
	 * Its a simplest way of solve the problem, but with nested loops like in this method, 
	 * it will degrade the performance and memory consumption, its disadvantage of this approach.
	 * 
	 * @param randomIntegers
	 * @return int []
	 */
	public int[] removeDuplicatesWithArrays(int[] randomIntegers){
		System.out.println("\nIn removeDuplicatesWithArrays method");
		int[] removeDuplicate = new int[randomIntegers.length];
        
	    //First we need to copy the array values into another array and retaining the original array as it is.
        removeDuplicate = Arrays.copyOf(randomIntegers, randomIntegers.length);
        //Arrays.sort(), sorting of primitive type arrays using Quicksort algorithm
        Arrays.sort(removeDuplicate);     
        
        int newSize = removeDuplicate.length;
        for (int i = 0; i < newSize; i++) {
        	for (int j = i+1; j < newSize; j++) {
        		if(removeDuplicate[i] == removeDuplicate[j]) {
        			removeDuplicate[j] = removeDuplicate[newSize-1];
        			newSize--;
        			j--;
        		}
        	}
        }
        int[] duplicatesRemoved = Arrays.copyOf(removeDuplicate, newSize);
	    System.out.println("\tBy using just arrays, the new array :: " + Arrays.toString(duplicatesRemoved));
	    
	    //The returned result may not be in original retained order.
	    return duplicatesRemoved;
	}
	
	
	/**
	 * By using ArrayList of Java Collections, here in this approach, it will check/compare for each and every 
	 * element before insert into ArrayList while looping thrr the array, the result list after loop is a unique list, 
	 * but its a performance degrade using with contains() of ArrayList, because of object comparison checking of inner elements.
	 * 
	 * @param randomIntegers
	 * @return int []
	 */
	public int[] removeDuplicatesWithHashArrayList(int[] randomIntegers){
		System.out.println("\nIn removeDuplicatesWithHashArrayList method");
		List<Integer> list = new ArrayList<Integer>();
	    for (int i = 0; i < randomIntegers.length; i++) {
	    	if(!list.contains(randomIntegers[i])){
	    		list.add(randomIntegers[i]);
	    	}
	    }
	    System.out.println("\tBy using list :: "+list);

	    return covertToPremitiveArray(list.toArray(new Integer[0]));	    
	}
	
	/**
	 * By using LinkedHashSet from Java Collections and assuming order is very important after removing duplicates, 
	 * it's a better approach with minimal no.of lines of code, its already stable api of Java Collection, 
	 * LinkedHashSet maintains a doubly-linked list running through all of its elements.
	 * The code is very easy to understand and maintainable, 
	 * its a better approach than simply using arrays itself with nested loops, even though LinkedHashSet requires more memory than HashSet.
	 * If order is not at all priority then use with HashSet, it will give a little more performance than LinkedHashSet.
	 * 
	 * @param randomIntegers
	 * @return int []
	 */
	public int[] removeDuplicatesWithLinkedHashSet(int[] randomIntegers){
		System.out.println("\nIn removeDuplicatesWithLinkedHashSet method");
		Set<Integer> linkedHashSet = new LinkedHashSet<Integer>();
	    for (int i = 0; i < randomIntegers.length; i++) {
	    	linkedHashSet.add(randomIntegers[i]);
	    }
	    System.out.println("\tBy using linkedHashSet :: "+linkedHashSet);
	    return covertToPremitiveArray(linkedHashSet.toArray(new Integer[0]));	    
	}
	
    private int[] covertToPremitiveArray (Integer[] toArray) {
        int [] results = new int[toArray.length];
        for (int i = 0; i < toArray.length; i++) {
            results[i] = toArray[i];            
        }
        return results;
    }
}
