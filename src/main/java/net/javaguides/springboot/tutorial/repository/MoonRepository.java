package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.Moon;
import net.javaguides.springboot.tutorial.entity.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoonRepository extends CrudRepository<Moon, Long> {
    List<Moon> findByName(String name);
}
