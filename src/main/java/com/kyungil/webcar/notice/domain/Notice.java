package com.kyungil.webcar.notice.domain;

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
public class Notice {

	private int id;
	@NonNull
	private String title;
	@NonNull
	private String content;
	private Timestamp createdAt;
	private int isWithdrew;
	 @NonNull
	private int userId;
	
	private String userName;
}
