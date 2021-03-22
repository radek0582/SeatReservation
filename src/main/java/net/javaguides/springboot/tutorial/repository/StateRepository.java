package net.javaguides.springboot.tutorial.repository;


import net.javaguides.springboot.tutorial.entity.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {
    List<State> findByName(String name);
}
