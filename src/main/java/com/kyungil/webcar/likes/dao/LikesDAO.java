package com.kyungil.webcar.likes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class LikesDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

    // 사용자가 특정 차량을 좋아요했는지 여부를 확인
    public boolean isLiked(int userId, int carId) {
        String sql = "SELECT COUNT(*) FROM likes WHERE user_id = ? AND car_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId, carId) > 0;
    }

    // 좋아요 상태 토글
    public void toggleLike(int userId, int carId) {
        if (isLiked(userId, carId)) {
            // 이미 좋아요한 경우, 좋아요 취소
            String deleteSql = "DELETE FROM likes WHERE user_id = ? AND car_id = ?";
            jdbcTemplate.update(deleteSql, userId, carId);
        } else {
            // 좋아요하지 않은 경우, 좋아요 추가
            String insertSql = "INSERT INTO likes (user_id, car_id) VALUES (?, ?)";
            jdbcTemplate.update(insertSql, userId, carId);
        }
    }
}
