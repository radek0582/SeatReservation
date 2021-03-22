package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.onetomany.Galaxy;
import net.javaguides.springboot.tutorial.entity.onetomany.MoonMO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalaxyRepository extends CrudRepository<Galaxy, Long> {
    List<Galaxy> findByName(String name);
}
