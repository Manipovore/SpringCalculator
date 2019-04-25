package com.manipovore.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Allows basic calculation - [+-/*]
 * @author manipovore.com
 * @version 1.0
 */
public class Operation {
	
	private String inputRefactorNegativeNumbers;
	private ArrayList<Double> numbers = new ArrayList<Double>();
	private ArrayList<String> operators = new ArrayList<String>();
	
	private String regexNb = "N|(\\d+(\\.\\d+)?)";
	private String[] splitOperators = {};
    
	private String regexOperator = "(\\+|\\*|\\×|\\%|\\/|\\÷|\\-)";
	private String[] splitNumbers = {};
    
    public Double resultOperation = 0.0;
	
    /**
     * Constructor: 
     * 1. refactorNegativeNumbers() - modify the string by replacing the negative numbers with Nx
     * 2. splitString() - stock split (regEx) in tables
     * 3. addNumberInList() - stock numbers in List
     * @param input - String in input
     */
	public Operation(String input) {
		this.inputRefactorNegativeNumbers = refactorNegativeNumbers(input);
		this.splitOperators = this.splitString(this.inputRefactorNegativeNumbers, this.regexNb);
		this.splitNumbers = this.splitString(this.inputRefactorNegativeNumbers, this.regexOperator);
		this.addNumberInList();
		this.addOperatorInList();
	}
	
	/**
	 * Search and Replace operator "-" by Character "N" for negative number
	 * Avoid confusion in the regexp
	 * @param input
	 * @return
	 */
	private String refactorNegativeNumbers(String input) {
		try {
	        input = input.replaceAll("(\\+|\\*|\\×|\\%|\\/|\\÷|\\-)(\\-)", "$1N");
	        input = input.replaceAll("^(\\-)(\\d+)", "N$2");
	        input = input.replaceAll("(\\s)", "");
		}catch(NumberFormatException e) {
			System.out.println("Error: operation.java string refactoring l.53");
		}
        return input;
	}
	
	/**
	 * Split a string via a regex
	 * @param string
	 * @param regExp
	 * @return
	 */
	private String[] splitString(String string, String regExp) {
		return string.split(regExp);
	}
	
	/**
	 * Add the elements of the array to a list
	 * Converting the string to double
	 */
	private void addNumberInList() {
        for(String string : splitNumbers) {
        	if(!string.isEmpty()) {
        		try {
        			if(string.contains("N")) {
            			//string = string.replace(string, "-" + string.charAt(1));
            			string = string.replaceAll("N(\\d+)", "-$1");
            		}
            		System.out.println("numbers: " + string);
            		this.numbers.add(Double.parseDouble(string));
        		}catch(NumberFormatException e) {
        			System.out.println("Error: operation.java add number in List (Is number?) l.83");
        		}
        	}
        }
	}
	
	/**
	 * Add the elements of the array to a list
	 * Converting operator
	 */
	private void addOperatorInList() {
        for(String string : splitOperators) {
        	if(!string.isEmpty()) {
				try {
					if(string.contains("×")) {
						string = string.replaceAll("×", "*");
					}else if(string.contains("÷")){
						string = string.replaceAll("÷", "/");
					}
					System.out.println("operator: " + string);
					this.operators.add(string);
				}catch(NumberFormatException e) {
					System.out.println("Error: operation.java add operator in List");
				}
        	}
        }
	}
	
	/**
	 * Find the first priority occurrence (*|/) and returns the position in the List
	 * @param operator List
	 * @return position in the List
	 */
	private int checkPriority(ArrayList<String> operator ) {
		int priority = -1;
		int i = 0;
		for(String string : operator) {
        	if(string.equals("*") || string.equals("/") || string.equals("%")) {
            	priority = i;
            	break;
        	}
        	i++;
        }
		return priority;
	}
	
	/**
	 * Priority calculation (*|/)
	 * update lists after calculation
	 */
	private void calculPriorityMultipDivid() {
        while(checkPriority(this.operators) != -1) {
        	int positionPriority = checkPriority(this.operators);
            double result = 0;
            if(this.operators.get(positionPriority).contentEquals("*")) {
    			result = this.numbers.get(positionPriority) * this.numbers.get(positionPriority + 1);
    		}else if(this.operators.get(positionPriority).contentEquals("/")) {
    			result = this.numbers.get(positionPriority) / this.numbers.get(positionPriority + 1);
    		}else if(this.operators.get(positionPriority).contentEquals("%")) {
    			result = this.numbers.get(positionPriority) % this.numbers.get(positionPriority + 1);
    		}
            this.numbers.remove(positionPriority + 1);
            this.numbers.set(positionPriority, result);
        	this.operators.remove(positionPriority);
        }
	}
	
	/**
	 * Calculation addition and subtraction
	 * update lists after calculation
	 */
	private void calculAdditionSubtraction() {
        int nbSize = this.numbers.size();
        for(int j = 0;j < nbSize ;j++) {
        	if(!this.operators.isEmpty()) {
           		if(this.operators.get(0).equals("+")) {
           			resultOperation = this.numbers.get(0) + this.numbers.get(1);
           		}else if(this.operators.get(0).equals("-")) {
           			resultOperation = this.numbers.get(0) - this.numbers.get(1);
           		}
           		this.numbers.set(0, resultOperation);
           		this.numbers.remove(1);
           		this.operators.remove(0);
        	}
       	}
	}
	
	/**
	 * Calling calculation methods
	 * @return the final result
	 */
	public BigDecimal getResult() {
		BigDecimal bd = new BigDecimal(0);
		try {
			this.calculPriorityMultipDivid();
			this.calculAdditionSubtraction();
			Double arroundRes = (double)Math.round(this.numbers.get(0) * 10000) / 10000;
			bd = new BigDecimal(Double.toString(arroundRes)).stripTrailingZeros();
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error: operation.java (no elt in list ?) l.172");
		}
        return bd;
	}
}
