package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.dao.CommentDao;
import net.javaguides.springboot.tutorial.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void delete(Comment comment){
        commentDao.delete(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentDao.findById(id);
    }
}
