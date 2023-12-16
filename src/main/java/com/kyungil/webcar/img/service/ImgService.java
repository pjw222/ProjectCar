package com.kyungil.webcar.img.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kyungil.webcar.img.dao.ImgDAO;
import com.kyungil.webcar.img.domain.Img;

@Service
public class ImgService {

	@Autowired
	private ImgDAO imgDAO;
	@Value("${upload.path}")
	private String uploadPath;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    public String getUploadPath() {
        return uploadPath;
    }

	public int addImgAndGetId(MultipartFile file) {
		// 이미지가 중복되는지 확인
		Img existingImg = imgDAO.findByName(file.getOriginalFilename());
		if (existingImg != null) {
			// 이미지가 이미 존재하면 해당 이미지의 ID를 반환
			return existingImg.getId();
		}

		Img img = new Img(file.getOriginalFilename(), "/imgs/upload/" + file.getOriginalFilename());

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		int updatedRows = jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement("INSERT INTO img (name, url) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, img.getName());
			ps.setString(2, img.getUrl());
			return ps;
		}, keyHolder);

		if (updatedRows > 0) {
			// 이미지가 성공적으로 추가되었을 때에만 ID 반환
			Number imgId = keyHolder.getKey();
			if (imgId != null) {
				img.setId(imgId.intValue());
				String fileName = file.getOriginalFilename();
				Path imagePath = Paths.get(uploadPath, fileName);
				try {
					Files.copy(file.getInputStream(), imagePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return imgId.intValue();
			} else {
				throw new RuntimeException("Failed to get image ID.");
			}
		} else {
			// 이미지가 추가되지 않았을 경우
			throw new RuntimeException("Failed to add image.");
		}
	}



}