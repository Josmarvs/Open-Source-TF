package com.appheathycook2.Service.impl;

import com.appheathycook2.Entity.Comment;
import com.appheathycook2.Repository.ICommentRepository;
import com.appheathycook2.Service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) throws Exception {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) throws Exception {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAll() throws Exception {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getById(Long id) throws Exception {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> find(Date publication_Date) throws Exception {
        return commentRepository.find(publication_Date);
    }
}
