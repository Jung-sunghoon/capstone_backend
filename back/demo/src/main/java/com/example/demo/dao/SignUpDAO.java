package com.example.demo.dao;

import com.example.demo.dto.SignUpDTO;

import java.util.List;

public interface SignUpDAO {
    void SignUpData(SignUpDTO users);
    int checkUserId(String userID);

    int checkNickName(String nickName);
}