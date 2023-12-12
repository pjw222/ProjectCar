package com.kyungil.webcar.user.domain;

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
public class User {
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
}
