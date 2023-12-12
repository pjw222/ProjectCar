package com.kyungil.webcar.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyungil.webcar.notice.dao.NoticeDAO;
import com.kyungil.webcar.notice.domain.Notice;

@Service
public class NoticeService {
	@Autowired
	NoticeDAO noticeDAO;

	public void add(Notice notice) {
		noticeDAO.addNotice(notice);
	}

	public Notice get(int id) {
		Notice notice = noticeDAO.get(id);
		return notice;
	}

	public List<Notice> getAll() {
		List<Notice> notice = noticeDAO.getAll();
		return notice;
	}

	public int getPageCount() {
		return noticeDAO.getCount();
	}
    public List<Notice> getNoticesByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return noticeDAO.getNoticesByPage(offset, pageSize);
    }
}
