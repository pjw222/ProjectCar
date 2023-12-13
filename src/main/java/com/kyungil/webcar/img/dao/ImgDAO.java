package com.kyungil.webcar.img.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungil.webcar.img.domain.Img;

@Repository
public class ImgDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private RowMapper<Img> mapper = new RowMapper<Img>() {
		@Override
		public Img mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Img(rs.getString("name"),rs.getString("url"));
		}
	};
	public int save(Img img) {
		String sql = "INSERT INTO img (name, url) VALUES (?, ?)";
		return jdbcTemplate.update(sql, img.getName(), img.getUrl());
	}
	public Img findByName(String name) {
	    String sql = "SELECT * FROM img WHERE name = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, mapper, name);
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}
}
