package com.fun.fucms;

public class EvilException extends Exception {
	
	public EvilException(Exception e) {
		super(e.getMessage());
	}

}
