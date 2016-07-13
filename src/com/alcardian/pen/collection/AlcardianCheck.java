package com.alcardian.pen.collection;

public class AlcardianCheck {
	
	
	/**
	 * isNumber checks a string and returns true if it is a number
	 * @param number The String you want to check if it contains just numbers.
	 * @param wantDouble True if the number could be a Double. False if you want an Int.
	 * @return Return true if the String are a number, false if anything else are detected
	 */
	public static boolean isNumber(String number, boolean wantDouble){ 
		char temp;
		boolean dotUsed = false;	//if . have already been used in the number
		for(int b=0;b < number.length(); b++){
			temp = number.charAt(b);
			if(!(temp == '0' || temp == '1' || temp == '2' || temp == '3' || temp == '4' || temp == '5' || temp == '6' || temp == '7' || temp == '8' || temp == '9' || temp == '-')){
				if(!(wantDouble && (temp == '.'))){
					return false;  //false if it is not a number
					}
				}
			if(temp == '.'){
				if(!dotUsed){
					dotUsed = true;
				} else{
					return false;	//return false if there are more than one '.'
				}
			}
			}
		return true;
		}
}
