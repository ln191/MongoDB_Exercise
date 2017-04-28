/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christoffer
 */
public class Result implements Comparable<Result>{

    /**
     * @param args the command line arguments
     */
    public int value;
    public String name;
    
//    public int compareTo(Result CompareResult)
//    {
//        int compareQuantity = ((Result) CompareResult).getQuantity();
//
//		//ascending order
//		return this.value - compareQuantity;
//    }
    
    public int getValue() {
		return value;
	}

    public int compareTo(Result CompareResult) {
        int compareQuantity = ((Result) CompareResult).getValue();

		//ascending order
		return compareQuantity - this.value;
    }

    public Result(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
}
