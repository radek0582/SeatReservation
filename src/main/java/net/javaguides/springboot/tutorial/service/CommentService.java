package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.entity.Comment;

import java.util.Optional;

public interface CommentService {
    void save (Comment comment);
    void delete (Comment comment);

    Optional<Comment> findById(Long id);
    //Comment findById (Long id);
}
