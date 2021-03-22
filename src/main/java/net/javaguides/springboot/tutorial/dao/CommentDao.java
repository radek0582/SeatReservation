package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends CrudRepository<Comment, Long>{
//    Comment findById ();
}
