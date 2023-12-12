package com.kyungil.webcar.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyungil.webcar.admin.dao.AdminDAO;
import com.kyungil.webcar.admin.domain.User;

@Service
public class AdminService {
	@Autowired
	private AdminDAO adminDAO;

	public List<User> getAllUsers() {
		return adminDAO.getAll();
	}

	public void delete(int userId) {

		adminDAO.delete(userId);

	}

	public User get(int userId) {
		return adminDAO.get(userId);
	}

	public User get(String userId) {
		return adminDAO.get(userId);
	}

	public void addAdmin(int[] userIds) {
		for (int userId : userIds) {
			adminDAO.grantAdminPermission(userId);
		}
	}
	public int getPageCount() {
		return adminDAO.getCount();
	}
    public List<User> getUsersByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return adminDAO.getUsersByPage(offset, pageSize);
    }
}
