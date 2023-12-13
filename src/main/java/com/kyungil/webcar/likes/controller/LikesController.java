package com.kyungil.webcar.likes.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyungil.webcar.likes.domain.Likes;
import com.kyungil.webcar.likes.service.LikesService;

@RestController
@RequestMapping("/api/likes")
public class LikesController {
	@Autowired
    private LikesService likesService;

    @PostMapping("/toggle")
    public Map<String, Boolean> toggleLike(@RequestParam Long carId) {

        boolean liked = !getLikedStatusFromDatabase(carId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("liked", liked);

        return response;
    }

    private boolean getLikedStatusFromDatabase(Long carId) {
        // 데이터베이스에서 좋아요 상태를 가져오는 로직을 구현
        // (예: likesService.isLiked(userId, carId))
        return false;
    }
}
