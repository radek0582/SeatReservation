package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.Planet;
import net.javaguides.springboot.tutorial.entity.onetoone.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
}
