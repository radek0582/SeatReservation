package net.javaguides.springboot.tutorial.repository;

import net.javaguides.springboot.tutorial.entity.Student;
import net.javaguides.springboot.tutorial.entity.Uzytkownik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UzytkownikRepository extends CrudRepository<Uzytkownik, Long> {
    
    List<Uzytkownik> findByName(String name);

    List<Uzytkownik> findByEmail(String email);

    List<Uzytkownik> findByPassword(String password);
}
