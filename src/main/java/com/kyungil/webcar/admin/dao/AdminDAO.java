package com.kyungil.webcar.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungil.webcar.admin.domain.User;


@Repository
public class AdminDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> mapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("password"), rs.getString("user_name"),
					rs.getString("phone"), rs.getString("address"), rs.getInt("gender"), rs.getInt("is_admin"),
					rs.getTimestamp("created_at"));
		}
	};
	public User get(String userId) {
		return jdbcTemplate.queryForObject("select * from users where user_id=?", mapper, userId);
	}
	public User get(int userId) {
		return jdbcTemplate.queryForObject("select * from users where id=?", mapper, userId);
	}
	
	public List<User> getAll() {
		return jdbcTemplate.query("select * from users order by id", mapper);
	}
	public void delete(int id) {
		jdbcTemplate.update("delete from users where id = ?", id);
	}
	public void grantAdminPermission(int id) {
		String sql = "UPDATE users SET is_admin = 1 WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
	public List<User> getUsersByPage(int offset, int pageSize) {
		String sql = "select * from users order by id desc limit ?,?";
		return jdbcTemplate.query(sql, mapper, offset, pageSize);
	}

	public int getCount() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
	}
}
