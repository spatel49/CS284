package hw1;

import recitation1.Person;

/**
 * Siddhanth Patel -- CS 284A
 * I pledge my honor that I have abided by the Stevens Honor System.
 * 
 * @author spate144
 * 
 * I am using the big-endian format
 */
public class BinaryNumber {
	//Data Field
	private int data[];
	private boolean overflow;
	
	//Constructors
	/**
	 * Constructor for creating a binary number of length length and consisting only of zeros
	 * @param length The length of binary number consisting of zeros.
	 */
	public BinaryNumber(int length) {
		data = new int[length];
	}
	
	/**
	 * Constructor for creating a binary number given a string
	 * @param str String form of a binary number
	 */
	public BinaryNumber(String str) {
		data = new int[str.length()];
		for (int i =0; i < str.length(); i++) {
			data[i] = java.lang.Character.getNumericValue(str.charAt(i));
		}
		
	}
	
	//Methods
	/**
	 * Method that returns the length of the binary number
	 */
	public int getLength() {
		return data.length;
	}
	
	/**
	 * Method that returns the digit of the binary number at an index
	 */
	public int getDigit(int index) {
		if (index<0 || index>data.length-1) {
			System.out.println("index is out of bounds");
		}
		return data[index];
	}
	
	/**
	 * Method that transforms a binary number to decimal form
	 */
	public int toDecimal() {
		int decimal = 0;
		for (int i=0; i<data.length; i++) {
			if (data[i]==1) {
			decimal += Math.pow(2, data.length-i-1);}
			decimal += 0;}
		return decimal;
	}
	
	/**
	 * Method that shifts all digits in the binary number n places to the right
	 */
	public void shiftR(int amount) {
		int reallocate[] = data;
		data = new int[amount + reallocate.length];
		for (int i=0; i< amount; i++) {
			data[i] =0;
		}
		for (int i=data.length; i>=amount; i--) {
			data[i] += reallocate[i - amount];
		}
	}
	
	/**
	 * Method that adds two binary numbers of the same length
	 */
	public void add(BinaryNumber aBinaryNumber){
		if (aBinaryNumber.getLength() != data.length) {
			System.out.println("The lengths of the two binary numbers are different");
		}
		int binaryOutput[] = new int[data.length];
		int carriedDigit = 0;
		for (int i=aBinaryNumber.getLength(); i==0; i--) {
			if (aBinaryNumber.getDigit(i) + data[i] + carriedDigit == 0) {
				binaryOutput[i] = 0;}
			else if (aBinaryNumber.getDigit(i) + data[i] + carriedDigit == 1) {
				binaryOutput[i] = 1;
				carriedDigit = 0;}
			else if (aBinaryNumber.getDigit(i) + data[i] + carriedDigit == 2) {
				binaryOutput[i] = 0;
				carriedDigit = 1;
			}
			else {
				binaryOutput[i] = 1;
				carriedDigit = 1;
			}
		}
		if (carriedDigit ==1) {
			overflow = true;
			data = new int[binaryOutput.length + 1];
			data[0] = 1;
			for (int index=1; index<binaryOutput.length; index++) {
				data[index] = binaryOutput[index];}
		}
		data = binaryOutput;
	}
	
	/**
	 * Method that clears the overflow flag
	 */
	public void clearOverflow() {
		overflow = false;
	}
	
	/**
	 * Method that transforms a binary number to a String
	 */
	public String toString() {
		if (overflow) {
			return "Overflow";
		}
		String binaryString = "";
		for (int i=0; i<data.length; i++) {
			binaryString += data[i];
		}
		return binaryString;
	}
}
