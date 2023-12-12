package com.kyungil.webcar.user.service;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyungil.webcar.user.dao.UserDAO;
import com.kyungil.webcar.user.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public void grantAdminPermission(String userId) {

		userDAO.grantAdminPermission(userId);
	}

	public void registerUser(User user) {
		user.setPassword(cryptoPassword(user.getPassword()));
		userDAO.regist(user);
	}

	public User login(User user) {
		User tempUser = userDAO.get(user.getUserId());

		if (tempUser != null && tempUser.getPassword().equals(cryptoPassword(user.getPassword()))) {
			return tempUser;
		}
		return null;

	}

	public User get(int userId) {
		return userDAO.get(userId);
	}
	public User get(String userId) {
		return userDAO.get(userId);
	}

	private String cryptoPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] sha256Hash = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : sha256Hash) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	  public void delete(int id) {
	        userDAO.delete(id);
	    }
}