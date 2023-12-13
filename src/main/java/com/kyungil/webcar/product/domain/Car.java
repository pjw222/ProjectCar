package com.kyungil.webcar.product.domain;

import java.sql.Timestamp;

import com.kyungil.webcar.img.domain.Img;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Car {
	private int id;

	@NonNull
	private String name;

	@NonNull
	private String content;

	@NonNull
	private int price;

	private int likesCount;

	@NonNull
	private int imgId;

	@NonNull
	private int brandId;

	@NonNull
	private int carTypeId;
	
	private String url;
}
