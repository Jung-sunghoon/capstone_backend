package com.example.demo.dao;

import com.example.demo.dto.ItInfoGenerateDTO;
import com.example.demo.dto.ItInfoEditRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItInfoEditDAO {

    int checkItInfoExists(@Param("itInfoId") int itInfoId);

    void updateItInfo(ItInfoGenerateDTO itInfo);

    void deleteItInfo(@Param("itInfoId") int itInfoId);

    void deleteTechMapping(@Param("itInfoId") int itInfoId);
}

