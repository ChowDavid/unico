package com.david.unico.common.dao;

import java.util.List;

import com.david.unico.common.GcdNumber;
import com.david.unico.common.Numbers;

public interface GcdNumbersDao {

	void insertNumber(GcdNumber number);
	List<Numbers> getAllNumbers();
	Long getMaxId();
	GcdNumber getHeadGcd();
	void delete(GcdNumber number);
	Long getGcdSum();
	List<Integer> getAllGcdNumber();

	
	
}
