package com.kyungil.webcar.likes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyungil.webcar.likes.dao.LikesDAO;

@Service
public class LikesService {
	@Autowired
	private LikesDAO likesDAO;

    public boolean isLiked(int userId, int carId) {
        return likesDAO.isLiked(userId, carId);
    }

    public void toggleLike(int userId, int carId) {
        likesDAO.toggleLike(userId, carId);
    }
}
