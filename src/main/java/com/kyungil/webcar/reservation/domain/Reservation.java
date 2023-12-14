package com.kyungil.webcar.reservation.domain;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Reservation {
	private int id;
	private Timestamp createdAt;
	@NonNull
	private int userId;
	@NonNull
	private int imgId;
	@NonNull
	private int carId;
	private String userName;
	private String userAddress;
	private String carName;
	private String carContent;
	private String url;
	
}
