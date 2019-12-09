/**
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.*;
import java.lang.Math.*; 
public class FracCalc {

    public static void main(String[] args)
    {
    	// TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner userInput = new Scanner(System.in);
    	boolean stop = false ; // boolean used to determine when the program is ended.
    	while (stop == false){ // repeats the calculator when quit isn't entered.
	    	String input = userInput.nextLine(); // user input
	    	input = input.toUpperCase();
	    	// tests to see if quit is entered by checking the individual charachters of the answer
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
    	charPlacement = operand1.length() + 3 ; 
    	operand3 = secondNum (input , charPlacement); // used to store value for 1st test
    	int[] num1 = mixedFrac(operand1);
    	int[] num3 = mixedFrac(operand3);// stores value for 2nd test
    	String answer = calculateAnswer(num1, num3, operand2 );
        return answer ; // 1st test : operand 3 ; 2nd test : operand3Check2 ; 
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
    // separates out the variables and calculates them out into their selective parts. 
    public static int[] mixedFrac (String input) { 
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
    	int[] answer = {whole , numerator , denominator }; // array of num values
		return answer ;  
    }
   public static String calculateAnswer(int[] num1 , int[] num2 , char operand) {
	   int numerator1 ; // stores numerator of the first fraction
	   int numerator2 ; // stores numerator of the second fraction
	   int denominator1 = num1[2]; // stores the denominator of the first fraction
	   int denominator2 = num2[2]; // stores the denominator of the second fraction
	   int numerator ; // holds the numerator of the function
	   int denominator ; // holds the denominator of the function
	   int whole ; // holds the whole value of the mixed fraction
	   if (num1[0] < 0 ) { // converts whole number to frac for negative numbers
		   numerator1 = (-1 ) * ( (Math.abs(num1[0]) * num1[2] ) + num1[1] ); 
	   } else { // handles the conversion of positive fractions to numerators and negative numbers. 
		   numerator1 = (num1[0] * num1[2]) + num1[1];
	   }
	   if (num2[0] < 0 ) { // converts whole number to frac for negative numbers
		   numerator2 = (-1 ) * ( (Math.abs(num2[0]) * num2[2] ) + num2[1] ); 
	   } else { // handles the conversion of positive fractions to numerators and negative numbers. 
		   numerator2 = (num2[0] * num2[2]) + num2[1];
	   }
	   if (operand == '+') { // handles the addition of fractions
		   numerator = (numerator1 * denominator2) + (numerator2 * denominator1 ); 
		   denominator = denominator1 * denominator2 ; 
	   } else if (operand == '-') { // handles the subtraction of fractions
		   numerator = (numerator1 * denominator2) - (numerator2 * denominator1);
		   denominator = denominator1 * denominator2 ; 
	   } else if (operand == '*') { // handles the multiplication of fractions
		   numerator = numerator1 * numerator2 ; 
		   denominator = denominator1 * denominator2 ; 
	   } else if (operand == '/'){ // handles the division of fractions
		   numerator = numerator1 * denominator2 ; 
		   denominator = numerator2 * denominator1; 
		   if (numerator < 0 && denominator < 0 ) { // divides with numerator and denom are negative
			   numerator = Math.abs(numerator);
		   } else if (denominator < 0 ) { // handles if only the denominator is negative
			   numerator = -1 * numerator; 
			   denominator = Math.abs(denominator);
		   }
	   } else { // error handling
		   System.out.println("Your operator value was innappropriate. Please try again.");
		   return "";
	   }
	   boolean isNegative = false; // used to tell if the end result will be positive or negative . 
	   if (numerator < 0 ) { // if the numerator is negative, reminds us before we use abs values
		   isNegative = true ; 
	   }
	   numerator = Math.abs(numerator);
	   denominator = Math.abs(denominator);
	   int[] endValues = { 0 , 0 , 0 } ; // stores final calculated values. 0-whole 1-numer 2-denom 
	   // stores the remainder of the numerator divided by the denominator 
	   int remainder = numerator % denominator ; 
	   endValues[0] = (numerator - remainder) / denominator ; 
	   for (int i = remainder ; i > 0 ; i-- ) { // reduces the fraction if the values match
		   if (remainder % i == 0 && denominator % i == 0 ) { // checks if values match to reduce
			   remainder = remainder / i ; 
			   denominator = denominator / i ; 
		   }
	   }
	   endValues[1] = remainder ; 
	   endValues[2] = denominator ; 
	   // changes the values of the product if the number is supposed to be negative
	   if (isNegative == true ) { 
		   if (endValues[0] == 0) { // changes the numerator value if the whole number is 0
			   int value = -1 * endValues[1] ; // stores negative value of the numerator
			   endValues[1] = value;
		   } else { // changes the whole number value if there is one
			   int value = (-1) * endValues[0] ; // stores negative value of the whole number
			   endValues[0] = value ; 
			   int value2 = Math.abs(endValues[1]) ; 
			   endValues[1] = value2 ; 
		   }
	   }
	   
	   String answer = "" ; // stores the final string for the answer
	   if (endValues[0] == 0 && endValues[1] == 0 ) {
		   answer = "0" ; 
   	   }  else if (endValues[0] == 0 ) { // handles if the answer is only a fraction
		   answer = endValues[1] + "/" + endValues[2] ; 
	   } else if(endValues[1] == 0 ) {// handles if the answer is only a whole number
		   answer = "" + endValues[0] ; 
	   } else { // handles all mixed numbers
		   answer = endValues[0] + "_" + endValues[1] + "/" + endValues[2] ; 
	   }
	   return answer ; 
   }
}
