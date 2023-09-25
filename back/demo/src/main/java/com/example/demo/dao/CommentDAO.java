package com.example.demo.dao;

import com.example.demo.dto.CommentDTO;
import java.util.List;

public interface CommentDAO {
    void saveComment(CommentDTO comment);
    List<CommentDTO> getCommentsByProjectId(int projectId);
}
