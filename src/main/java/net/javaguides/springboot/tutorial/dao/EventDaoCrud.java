package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Event;
import net.javaguides.springboot.tutorial.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDaoCrud extends CrudRepository<Event, Long>{

    @Query("SELECT e.id FROM Event e")
    List<Long> findAllIds();

    @Query("SELECT e FROM Event e ORDER BY e.name")
    List<Event> findAllByNameAsc();

    @Query("SELECT e FROM Event e ORDER BY e.timestamp DESC")
    List<Event> findAllOrderByTimestampDesc();


//    @Modifying
//    @Query(value = "alter table PRODUCTS add newonecolumn int not null default 0", nativeQuery = true)
//    void addColumn();
}
