package com.kyungil.webcar.reservation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungil.webcar.reservation.domain.Reservation;

@Repository
public class ReservationDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Reservation> mapper = new RowMapper<Reservation>() {
		@Override
		public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Reservation(rs.getInt("id"), rs.getTimestamp("created_at"), rs.getInt("user_id"),
					rs.getInt("img_id"), rs.getInt("car_id"), rs.getString("user_name"), rs.getString("address"),
					rs.getString("name"), rs.getString("content"), rs.getString("url"));
		}
	};

	public void addReservation(Reservation reservation) {
		jdbcTemplate.update("INSERT INTO reservation (user_id, img_id, car_id) VALUES (?, ?, ?)",
				reservation.getUserId(), reservation.getImgId(), reservation.getCarId());
	}

	public List<Reservation> getUserReservations(int userId) {
		String sql = "SELECT r.id, r.created_at, r.user_id, r.img_id, r.car_id, u.user_name, u.address, c.name AS name, c.content, i.url FROM reservation r JOIN users u ON r.user_id = u.id JOIN car c ON r.car_id = c.id JOIN img i ON r.img_id = i.id WHERE r.user_id = ?";
		return jdbcTemplate.query(sql, mapper, userId);
	}
	public List<Reservation> getAdminReservations() {
	    String sql = "SELECT r.id, r.created_at, r.user_id, r.img_id, r.car_id, u.user_name, u.address, c.name AS name, c.content, i.url FROM reservation r JOIN users u ON r.user_id = u.id JOIN car c ON r.car_id = c.id JOIN img i ON r.img_id = i.id";
	    return jdbcTemplate.query(sql, mapper);
	}
	public void delivery(int id) {
		jdbcTemplate.update("delete from reservation where id = ?", id);
	}
	
	public List<Reservation> getPageReservations(int offset, int pageSize) {
	    String sql = "SELECT r.id, r.created_at, r.user_id, r.img_id, r.car_id, u.user_name, u.address, c.name AS name, c.content, i.url FROM reservation r JOIN users u ON r.user_id = u.id JOIN car c ON r.car_id = c.id JOIN img i ON r.img_id = i.id ORDER BY r.id DESC LIMIT ?,?";

	    return jdbcTemplate.query(sql, mapper, offset, pageSize);
	}
	
	public int getCount() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM reservation", Integer.class);
	}
}
