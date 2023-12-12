package com.kyungil.webcar.notice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungil.webcar.notice.domain.Notice;
import com.kyungiljava4.board.board.domain.Board;

@Repository
public class NoticeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

	private RowMapper<Notice> mapper = new RowMapper<Notice>() {
		@Override
		public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Notice(rs.getInt("id"), rs.getString("title"), rs.getString("content"), 
					rs.getTimestamp("created_at"),
					rs.getInt("is_withdrew"),
					rs.getInt("user_id"),
					rs.getString("name")
					);
		}
	};


	public Notice get(int id) {
		return jdbcTemplate.queryForObject(
				"select a.*, b.name from notices a join users b on a.user_id = b.id where a.id = ?",
				mapper, id);
	}

	public List<Notice> getAll() {
		return jdbcTemplate.query(
				"select notices.*, users.name from notices join users on notices.user_id=users.id order by notices.id",
				mapper);
	}
	
    public void addNotice(Notice notice) {
        jdbcTemplate.update("INSERT INTO notices (title, content,is_withdrew, user_id) VALUES (?, ?,?,?)",
                notice.getTitle(), notice.getContent(),0, notice.getUserId());
    }
    public void deleteNotice(int noticeId) {
        jdbcTemplate.update("DELETE FROM notices WHERE id=?", noticeId);
    }
	public List<Notice> getNoticesByPage(int offset, int pageSize) {
		String sql = "select notices.*, users.name from notices join users on notices.user_id=users.id order by notices.id desc limit ?,?";
		return jdbcTemplate.query(sql, mapper, offset, pageSize);
	}

	public int getCount() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM notices", Integer.class);
	}
}
