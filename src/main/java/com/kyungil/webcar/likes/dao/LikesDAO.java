package com.kyungil.webcar.likes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungil.webcar.likes.domain.Likes;



@Repository
public class LikesDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Likes> mapper = new RowMapper<Likes>() {
        @Override
        public Likes mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Likes(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("car_id"));
        }
    };

    public void toggleLike(int userId, int carId) {
        String query = "INSERT INTO likes (user_id, car_id) VALUES (?, ?) ON DUPLICATE KEY UPDATE user_id = VALUES(user_id), car_id = VALUES(car_id)";
        jdbcTemplate.update(query, userId, carId);
    }

    public int getLikesCount(int carId) {
        String query = "SELECT COUNT(*) FROM likes WHERE car_id = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, carId);
    }
    public boolean hasUserLiked(int userId, int carId) {
        String query = "SELECT COUNT(*) FROM likes WHERE user_id = ? AND car_id = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, userId, carId);
        return count > 0;
    }
}

