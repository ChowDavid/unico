package com.david.unico.common.calculator;

import org.springframework.stereotype.Component;

/**
 * 
 * @author david
 * {@link} http://stackoverflow.com/questions/13673600/how-to-write-a-simple-java-program-that-finds-the-greatest-common-divisor-betwee
 */
@Component
public class GCDCalculator {
	
	public static int gcb(int a,int b){
		if(a == 0 || b == 0) return a+b;
		  return gcb(b,a%b);
	}
	

}
