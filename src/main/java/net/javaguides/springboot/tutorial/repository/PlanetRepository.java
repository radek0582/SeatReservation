package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {
    List<Planet> findByName (String name);
}
