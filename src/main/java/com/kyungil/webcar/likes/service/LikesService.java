package com.kyungil.webcar.likes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyungil.webcar.likes.dao.LikesDAO;
import com.kyungil.webcar.product.service.CarService;

@Service
public class LikesService {
	@Autowired
	private LikesDAO likesDAO;
	@Autowired
	private CarService carService;
	
    public void toggleLike(int userId, int carId) {
        likesDAO.toggleLike(userId, carId);
        updateLikesCount(carId); // 좋아요 토글 시 likes_count 업데이트
    }

    private void updateLikesCount(int carId) {
        int likesCount = likesDAO.getLikesCount(carId);
        carService.updateLikesCount(carId, likesCount);
    }


    public int getLikesCount(int carId) {
        return likesDAO.getLikesCount(carId);
    }
    public boolean hasUserLiked(int userId, int carId) {
        return likesDAO.hasUserLiked(userId, carId);
    }
}
