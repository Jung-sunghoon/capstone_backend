package com.example.demo.dao;

import com.example.demo.dto.ItInformationDTO;

public interface ItInfoMoreInformationDAO {
    ItInformationDTO ItInformation(int itInfoId);
    void IncreaseViewCountItInfo(int itInfoId);
}
