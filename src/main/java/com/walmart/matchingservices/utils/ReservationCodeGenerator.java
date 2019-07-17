package com.walmart.matchingservices.utils;

import java.util.Random;

public class ReservationCodeGenerator {
	
	public static String generateCode() {		
		Random rand = new Random();
		return String.valueOf(rand.nextInt(100000));
	}
}
