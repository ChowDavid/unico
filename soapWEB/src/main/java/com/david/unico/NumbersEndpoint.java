package com.david.unico;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.david.unico.common.GcdNumber;
import com.david.unico.common.dao.GcdNumbersDaoImpl;
import com.david.unico.gs_producing_web_service.GcdListResponse;
import com.david.unico.gs_producing_web_service.GcdResponse;
import com.david.unico.gs_producing_web_service.GcdSumResponse;

@Endpoint
public class NumbersEndpoint {

	private static final Logger LOG = Logger.getLogger(NumbersEndpoint.class);
	private static final String NAMESPACE_URI = "http://david.com/unico/gs-producing-web-service";

	@Autowired
	private GcdNumbersDaoImpl dao;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "gcdListRequest")
	@ResponsePayload
	public GcdListResponse getGcdList() {
		System.out.println("getGcd called");
		GcdListResponse response = new GcdListResponse();
		try{
		List<Integer> gcds=dao.getAllGcdNumber();
		gcds.forEach(gcd -> response.getGcd().add(gcd));
		} catch (Exception e){
			response.getGcd().add(-1);
			LOG.error("Error found on getGcdList "+e.getMessage());
			e.printStackTrace();
		}
		return response;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "gcdSumRequest")
	@ResponsePayload
	public GcdSumResponse getGcdSum() {

		GcdSumResponse response = new GcdSumResponse();
		try {
			Long sum = dao.getGcdSum();
			response.setGcd(sum);
		} catch (Exception e ) {
			response.setGcd(0);
			LOG.error("Error found on getGcdSum "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	/*
	 * response -1 means the gcd number not exist or the stack is empty.
	 * 
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "gcdRequest")
	@ResponsePayload
	public GcdResponse getGcd() {
		GcdResponse response = new GcdResponse();
		try {
			GcdNumber number = dao.getHeadGcd();
			if (number != null) {
				LOG.info("Gcd Number found:" + number);
				response.setGcd(number.getGcd());
				dao.delete(number);
				LOG.info("Gcd number remove:" + number);
			} else {
				response.setGcd(-1);
			}
		} catch (Exception e) {
			response.setGcd(-1);
			LOG.error("Error found on getGcd "+e.getMessage());
			e.printStackTrace();
			
		}
		return response;

	}

}
