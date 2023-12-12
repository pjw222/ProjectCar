package com.kyungil.webcar.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungil.webcar.user.domain.User;


@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> mapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("password"), rs.getString("name"),
					rs.getString("phone"), rs.getString("address"), rs.getInt("gender"), rs.getInt("is_admin"),
					rs.getTimestamp("created_at"));
		}
	};

	public void regist(User user) {
		jdbcTemplate.update("insert into users (user_id, password, name, phone, address, gender) values(?,?,?,?,?,?)",
				user.getUserId(), user.getPassword(), user.getName(), user.getPhone(), user.getAddress(),
				user.getGender());
	}

	public User get(String userId) {
		return jdbcTemplate.queryForObject("select * from users where user_id=?", mapper, userId);
	}
	public User get(int userId) {
		return jdbcTemplate.queryForObject("select * from users where user_id=?", mapper, userId);
	}

	public void grantAdminPermission(String userId) {
		String sql = "UPDATE users SET is_admin = 1 WHERE user_id = ?";
		jdbcTemplate.update(sql, userId);
	}
	public void delete(int id) {
		jdbcTemplate.update("delete from users where id = ?", id);
	}

}
