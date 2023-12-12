package com.kyungil.webcar.admin.domain;

import java.sql.Timestamp;

import com.kyungil.webcar.user.domain.User;
import com.kyungil.webcar.user.domain.User.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
	public enum Role {
		USER, ADMIN
	}

	private int id;

	@NonNull
	private String userId;

	@NonNull
	private String password;

	@NonNull
	private String name;

	@NonNull
	private String phone;

	@NonNull
	private String address;

	private int gender;

	private int admin;

	private Timestamp created_at;

	public String getGenderAsString() {
		return (gender == 1) ? "남성" : "여성";
	}

	public Role getRole() {
		return (admin == 1) ? Role.ADMIN : Role.USER;
	}
}


