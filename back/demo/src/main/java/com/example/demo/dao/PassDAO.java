package com.example.demo.dao;

import com.example.demo.dto.ApplicationProjectDTO;
import com.example.demo.dto.PassDTO;

import java.util.List;

public interface PassDAO {
    void insertPass(PassDTO passDTO);
    List<PassDTO> listByProjectId(int projectId);
    int countPassByProjectId(int projectId);

    int countProjectGenerateByProjectId(int projectId);
    void updateProjectGenerateStatus(int projectId);

    List<ApplicationProjectDTO> getApplicationListByProject(int projectId);
    void completeApplyProject(int projectId);






}
