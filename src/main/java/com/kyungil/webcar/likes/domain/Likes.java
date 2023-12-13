package com.kyungil.webcar.likes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Likes {
	private int id;
	@NonNull
	private int userId;
	@NonNull
	private int carId;
}
