package com.david.unico.restfulWEB;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.unico.common.Numbers;
import com.david.unico.common.dao.GcdNumbersDao;
import com.david.unico.common.dao.GcdNumbersDaoImpl;

@RestController
public class NumberController {

	private static final Logger LOG = Logger.getLogger(NumberController.class);
	@Autowired
	private JmsTemplate myJmsTemplate;

	/*
	@Autowired
	public void setMyJmsTemplate(JmsTemplate myJmsTemplate) {
		this.myJmsTemplate = myJmsTemplate;
	}*/
	
	@Autowired
	private GcdNumbersDaoImpl dao;

	@RequestMapping("/push")
	public String greeting(@RequestParam(value = "first", defaultValue = "empty") String first,
			@RequestParam(value = "second", defaultValue = "empty") String second) {
		try {
			Numbers number = new Numbers(new Integer(first), new Integer(second));
			LOG.info("Send JMS " + number + " to " + myJmsTemplate.getDefaultDestinationName());
			myJmsTemplate.convertAndSend(SpringConfig.ORDER_QUEUE, number);
			return "SUCCESS";
		} catch (Exception e) {
			LOG.info("Error on " + e.getMessage() + " for input[" + first + "][" + second + "]");
			e.printStackTrace();
			return "FAIL input incorrect, both first and second paramter should be integer";
		}
	}
	
	@RequestMapping("/list")
	public List<Numbers> list(){
		return dao.getAllNumbers();
	}
}
