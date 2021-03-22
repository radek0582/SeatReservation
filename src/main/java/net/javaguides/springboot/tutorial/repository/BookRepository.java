package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.onetomany.Book;
import net.javaguides.springboot.tutorial.entity.onetoone.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByName(String name);
}
