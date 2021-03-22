package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.entity.Uzytkownik;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UzytkownikDaoCrud extends CrudRepository<Uzytkownik, Long>{

    @Query("SELECT p.id FROM Uzytkownik p")
    List<Long> findAllIds();

//    @Query("SELECT p FROM Uzytkownik p ORDER BY p.timestamp DESC")
//    List<Product> findAllOrderByTimestampDesc();

//    @Modifying
//    @Query(value = "alter table PRODUCTS add newonecolumn int not null default 0", nativeQuery = true)
//    void addColumn();
}
