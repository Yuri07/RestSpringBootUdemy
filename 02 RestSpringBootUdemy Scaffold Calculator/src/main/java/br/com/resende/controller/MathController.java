package br.com.resende.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.resende.exception.UnsupportedMathOperationException;
import br.com.resende.webrequestcalculator.WebRequestCalculator;

@RestController
public class MathController {
	
	@Autowired
	private WebRequestCalculator webCalculator;
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {//(path paramns)
		
		Double sum;
		try {
			sum  = webCalculator.sum(numberOne, numberTwo);
		}catch(UnsupportedMathOperationException e) {
			throw e;
		}
		
		return sum;
	}
	
	@RequestMapping(value="/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		
		Double subtraction;
		try {
			subtraction = WebRequestCalculator.subtraction(numberOne, numberTwo);
		}catch(UnsupportedMathOperationException e) {
			throw e;
		}
		
		return subtraction;
	}
	
	@RequestMapping(value="/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		
		Double multiplication;
		try {
			multiplication = WebRequestCalculator.multiplication(numberOne, numberTwo);
		}catch(UnsupportedMathOperationException e) {
			throw e;
		}
		
		return multiplication;
	}
	
	@RequestMapping(value="/division/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		
		Double division;
		try {
			division = WebRequestCalculator.division(numberOne, numberTwo);
		}catch(UnsupportedMathOperationException e) {
			throw e;
		}
		
		return division;
	}
	
	@RequestMapping(value="/average/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		
		Double average;
		try {
			average = WebRequestCalculator.average(numberOne, numberTwo);
		}catch(UnsupportedMathOperationException e) {
			throw e;
		}
		
		return average;
	}
	
	@RequestMapping(value="/square/{number}", method=RequestMethod.GET)
	public Double square(@PathVariable("number") String number) throws Exception {
		
		Double square;
		try {
			square = WebRequestCalculator.square(number);
		}catch(UnsupportedMathOperationException e) {
			throw e;
		}
		
		return square;
	}

	
	
}
