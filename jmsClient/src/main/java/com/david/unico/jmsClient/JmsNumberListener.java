package com.david.unico.jmsClient;

import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.david.unico.common.GcdNumber;
import com.david.unico.common.Numbers;
import com.david.unico.common.calculator.GCDCalculator;
import com.david.unico.common.dao.GcdNumbersDaoImpl;



@Component
public class JmsNumberListener {
	
	private static final Logger LOG = Logger.getLogger(JmsNumberListener.class.getName());
	
	@Autowired
	private GcdNumbersDaoImpl dao;
	
	@Autowired
	private GCDCalculator calculator;

	@JmsListener(destination = SpringConfig.ORDER_QUEUE, containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(Numbers number) {
		LOG.info("Received <" + number + ">");
		int gcd=calculator.gcb(number.getFirst(), number.getSecond());
		GcdNumber gcdNumber=new GcdNumber(number,gcd);
		dao.insertNumber(gcdNumber);
		LOG.info("Record write into DB "+gcdNumber);
	}

}
