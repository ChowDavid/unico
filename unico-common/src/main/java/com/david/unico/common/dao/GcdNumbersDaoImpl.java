package com.david.unico.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.david.unico.common.GcdNumber;
import com.david.unico.common.Numbers;

@Repository
public class GcdNumbersDaoImpl implements GcdNumbersDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void insertNumber(GcdNumber number) {
		jdbcTemplate.update("insert into numbers(first,second,gcd) values (?,?,?)",number.getFirst(),number.getSecond(),number.getGcd());
		
	}

	@Override
	public List<Numbers> getAllNumbers() {
		return jdbcTemplate.query("select id,first,second from numbers order by id",
				new RowMapper<Numbers>(){
					@Override
					public Numbers mapRow(ResultSet rs, int arg1) throws SQLException {
						Numbers number=new Numbers();
						number.setFirst(rs.getInt("first"));
						number.setSecond(rs.getInt("second"));
						number.setId(rs.getLong("id"));
						return number;
					}
		});
	}

	@Override
	public Long getMaxId() {
		return jdbcTemplate.queryForObject("select max(id) from numbers",Long.class);
	}

	@Override
	public GcdNumber getHeadGcd() {
		List<GcdNumber> numbers= jdbcTemplate.query("select id,gcd,first,second from numbers where id in (select min(id) from numbers) ", 
				new RowMapper<GcdNumber>(){
			@Override
			public GcdNumber mapRow(ResultSet rs, int arg1) throws SQLException {
				GcdNumber number=new GcdNumber();
				number.setFirst(rs.getInt("first"));
				number.setSecond(rs.getInt("second"));
				number.setId(rs.getLong("id"));
				number.setGcd(rs.getInt("gcd"));
				return number;
			}
		
		});
		if (numbers!=null && numbers.size()>0){
			return numbers.get(0);
		} else {
			return null;
		}
	}

	public void delete(GcdNumber number) {
		jdbcTemplate.update("delete from numbers where id=?",number.getId());
		
	}

	public Long getGcdSum() {
		return jdbcTemplate.queryForObject("select sum(gcd) from numbers", Long.class); 
	}

	public List<Integer> getAllGcdNumber() {
		return jdbcTemplate.queryForList("select gcd from numbers",Integer.class);
	}


}
