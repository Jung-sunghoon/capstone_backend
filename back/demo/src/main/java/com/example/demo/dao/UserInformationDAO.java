package com.example.demo.dao;

import com.example.demo.dto.SignUpDTO;

public interface UserInformationDAO {
    SignUpDTO UserData(String userId);

    void UpdateUserData(SignUpDTO user);
}
