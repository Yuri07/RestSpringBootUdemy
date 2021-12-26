package br.com.resende.webrequestcalculator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.resende.exception.ResourceNotFoundException;
import br.com.resende.utils.CalculatorUtils;

public class WebRequestCalculator {

	public static Double sum(String numberOne, String numberTwo) throws Exception {
		
		if(!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please set a numeric value!");
		}
		
		Double sum = CalculatorUtils.convertToDouble(numberOne)+CalculatorUtils.convertToDouble(numberTwo);
		
		return sum;
	}
	
	public static Double subtraction(String numberOne,  String numberTwo) throws Exception {
		
		if(!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please set a numeric value!");
		}
		
		Double subtraction = CalculatorUtils.convertToDouble(numberOne)-CalculatorUtils.convertToDouble(numberTwo);
		
		return subtraction;
	}
	
	public static Double multiplication(String numberOne, String numberTwo) throws Exception {
		
		if(!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please set a numeric value!");
		}
		
		Double multiplication = CalculatorUtils.convertToDouble(numberOne)*CalculatorUtils.convertToDouble(numberTwo);
		
		return multiplication;
	}
	
	public static Double division(String numberOne, String numberTwo) throws Exception {
		
		if(!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please set a numeric value!");
		}
		
		Double division = CalculatorUtils.convertToDouble(numberOne)/CalculatorUtils.convertToDouble(numberTwo);
		
		return division;
	}
	
	public static Double average(String numberOne, String numberTwo) throws Exception {
		
		if(!CalculatorUtils.isNumeric(numberOne) || !CalculatorUtils.isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please set a numeric value!");
		}
		
		Double average = (CalculatorUtils.convertToDouble(numberOne)+CalculatorUtils.convertToDouble(numberTwo))/2;
		
		return average;
	}
	
	public static Double square(String number) throws Exception {
		
		if(!CalculatorUtils.isNumeric(number)) {
			throw new ResourceNotFoundException("Please set a numeric value!");
		}
		
		Double square = Math.sqrt(CalculatorUtils.convertToDouble(number));
		
		return square;
	}
	
}
