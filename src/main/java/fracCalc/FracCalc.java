/**
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.*; 
public class FracCalc {

    public static void main(String[] args)
    {
    	// TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner userInput = new Scanner(System.in);
    	boolean stop = false ; // boolean used to determine when the program is ended.
    	while (stop == false){ // repeats the calculator when quit isn't entered.
	    	String input = userInput.nextLine(); // user input
	    	input = input.toUpperCase();
	    	if ( input.charAt(0) == 'Q' && input.charAt(1) == 'U' && input.charAt(2) == 'I' &&
	    			input.charAt(3) == 'T') { //tests to see if quit is entered
	    		stop = true ; 
		    	return;
	    	} else { // executes the fraction calculator if quit isn't entered
	    		String answer = produceAnswer(input); // passes the user input into produceAnswer
		    	System.out.println(answer);
	    	}
    	}
    	userInput.close();
    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    
    public static String produceAnswer(String input)
    {
    	String operand1 ; //stores the first number inputed into the calculator.
    	char operand2 ; // stores the operator used 
    	String operand3 ; // stores the second inputed number. 
    	int charPlacement ; // used to keep track of index location in string
        // TODO: Implement this function to produce the solution to the input
    	operand1 = firstNum(input);
    	operand2 = operator(input);
    	charPlacement = input.indexOf(operand2 , 1);
    	charPlacement +=  2 ; 
    	operand3 = secondNum (input , charPlacement); // used to store value for 1st test
    	String operand3Check2 = seperateNumber(operand3);// stores value for 2nd test
    	
        return operand3 ; // 1st test : operand 3 ; 2nd test : operand3Check2 ; 
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
    // this method returns the first fraction inputed as a string
    public static String firstNum(String input) {
    	String operand1 = ""; // stores the values in the first operand
    	int charPlacement = 0 ; // tells us which index we are checking
    	char charInString = input.charAt(charPlacement); // gives the char value at the set index
    	while (charInString != ' ') {
    		operand1 += charInString ;  
    		charPlacement ++ ; 
    		charInString = input.charAt(charPlacement);
    	}
    	return operand1 ; 
    }
    // this method will return the operator used in the function. 
    public static char operator(String input) {
    	char operator ; // this variable will contain the operator
    	int charPlacement = 0 ; // counts the index of the string that we are looking at
    	char charInString = input.charAt(charPlacement); // finds char at StringIndex
    	while (charInString != ' ') {
    		charPlacement++ ; 
    		charInString = input.charAt(charPlacement);
    	}
    	charPlacement++ ; 
    	operator = input.charAt(charPlacement);
    	return operator; 
    }
    // returns a string of the second character inputed
    public static String secondNum(String input , int index) {
    	int lengthString = input.length(); // will give the length of the string
    	String operand = "" ; // will be used to store the second operand
    	//adds the characters of the second operand to the operand string. 
    	for ( int i = index ; i < lengthString  ; i++) {
    		operand += input.charAt(i);
    	}
    	return operand ; 
    }
    // seperates out the variables and calculates them out into their selective parts. 
    public static String seperateNumber (String input) { 
    	int whole ; // the int value of the whole number.
    	int numerator ; // int value of the numerator ; 
    	int denominator ; // int value of the denominator
    	int index = 0 ; // used to track the index of the numbers used
    	// finds the value of the variables if the variables are equal to 0 
    	if (input.indexOf('_') == -1 && input.indexOf('/') == - 1 ) { 
    		whole = Integer.parseInt(input) ; 
    		numerator = 0 ; 
    		denominator = 1 ;
    	// finds the numerator and the denominator if only a fraction 
    	} else if (input.indexOf('_') == -1 ) {
    		whole = 0 ; 
    		if (input.indexOf('/') == 1 ) { // handles if numerator only has one place
    			String numSubstr = ""; // stores substring of the numerator
    			numSubstr += input.charAt(0); 
    			numerator = Integer.parseInt(numSubstr); 
    			index = 1 ; 
    		} else { // handles if the fraction is more than one place
    			index = input.indexOf('/') ; // index of division operator
    			String numSubstr = "" ; // string of numerator values. 
    			numSubstr = input.substring( 0 , index ) ; 
    			numerator = Integer.parseInt(numSubstr);
    		}
    		index ++ ; 
    		// finds string of denominator values. 
    		if (index == input.length() -1 ) { // finds if the denominator is one place
    			String denomSubstr = "" ; 
    			denomSubstr += input.charAt(index) ; 
    			denominator = Integer.parseInt(denomSubstr);    			
    		} else { // finds the denominator if the denominator is more than 1 place
	    		String denomSubstr = input.substring(index , input.length() ) ; 
	    		denominator = Integer.parseInt(denomSubstr);
    		}
    	} else { //handles if the fraction is a mixed number. 
    		if (input.indexOf('_') == 1 ) { // handles if whole number has 1 place
    			String wholeSubstr = ""; // string of whole number 
    			wholeSubstr += input.charAt(0) ; 
    			whole = Integer.parseInt(wholeSubstr) ;
    			index = 2 ;     			
    		} else { // handles if whole number is more than 1 place.
    			index = input.indexOf('_') ; 
    			String wholeSubstr = input.substring( 0 , index ) ;  // string of whole number
    			whole = Integer.parseInt(wholeSubstr) ; 
    			index++	;
    		} if (input.indexOf('/') == index + 1 ) { // handles if numerator has 1 place
    			String numSubstr = "" ; // string of numerator value 
    			numSubstr += input.charAt(index) ; 
    			numerator = Integer.parseInt(numSubstr);
    			index = input.indexOf('/') + 1 ; 
    		} else { // handles if numerator has 2 places. 
    			 // string of numerator value
    			String numSubstr = input.substring(index , input.indexOf('/'));
    			numerator = Integer.parseInt(numSubstr) ; 
    			index = input.indexOf('/') + 1 ; 	
    		}
    		if (input.length() - 1 == index) {// handles if denominator has 1 place 
    			String denomSubstr = ""; // string of denominator value
    			denomSubstr += input.charAt(index);
    			denominator = Integer.parseInt(denomSubstr);
    		} else { // handles if the denominator has more than 1 place
    			String denomSubstr = input.substring(index , input.length()); 
    			denominator = Integer.parseInt(denomSubstr);
    		}
    	}
    	String checkpoint2 = "whole:" + whole + " numerator:" + numerator + " denominator:" + 
    			denominator ;
    	// finds the double value of all of the terms combined into one number. 
    	double value = ( (numerator * 1.0 ) / ( denominator * 1.0 ) ) + whole ;
    	return checkpoint2; 
    }
    
}
