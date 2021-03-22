package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.Moon;
import net.javaguides.springboot.tutorial.entity.onetomany.MoonMO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoonMORepository extends CrudRepository<MoonMO, Long> {
    List<MoonMO> findByName(String name);
}
