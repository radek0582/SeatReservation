package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.manytomany.Magazine;
import net.javaguides.springboot.tutorial.entity.onetomany.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagazineRepository extends CrudRepository<Magazine, Long> {
    List<Magazine> findByName(String name);
}
