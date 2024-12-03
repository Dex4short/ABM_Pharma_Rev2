package oop;

import java.math.BigDecimal;

public class Percentage {
	private String percent_value;
	/**
	 * 
	 * @param percent_value with % sign, "%0"~"%100".
	 */
	public Percentage(String percent_value){
		if(percent_value.charAt(percent_value.length() - 1) != '%') {
			System.err.println("percent value must have a percent symbol");
		}
		else {
			this.percent_value = percent_value;
		}
	}
	@Override
	public String toString() {
		return percent_value;
	}
	public BigDecimal toBigDecimal() {
		String str = percent_value.replace("%", "");
		return new BigDecimal(str).multiply(new BigDecimal("0.01"));
	}
}
