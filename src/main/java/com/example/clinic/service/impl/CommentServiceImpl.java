package com.example.clinic.service.impl;

import com.example.clinic.entity.Comment;
import com.example.clinic.entity.Patient;
import com.example.clinic.repository.CommentRepository;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
