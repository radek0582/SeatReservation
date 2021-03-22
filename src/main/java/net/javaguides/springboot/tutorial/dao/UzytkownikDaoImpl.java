package net.javaguides.springboot.tutorial.dao;

import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.entity.Uzytkownik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class UzytkownikDaoImpl implements UzytkownikDao {

    @Autowired
    private UzytkownikDaoCrud uzytkownikDaoCrud;

    @Override
    public List<Uzytkownik> findAll() {
        Iterable<Uzytkownik> coll = uzytkownikDaoCrud.findAll();
        List<Uzytkownik> users = new ArrayList<>();

//        List<Product> sortedList = products.stream()
//                .sorted(Comparator.comparingInt(Product::getTimestamp))
//                .collect(Collectors.toList());

        coll.forEach(users::add);    // java 8
        return users;
    }

//    private Sort sortById (){
//        return new Sort(Sort.Direction.ASC, "timestamp");
//    }

    @Override
    public Uzytkownik findById(Long id) {
        return uzytkownikDaoCrud.findById(id).get();
    }

    @Override
    public void update(Uzytkownik uzytkownik) {
        this.save(uzytkownik);
    }
//
//    @Override
//    public Uzytkownik findRandom(){
//        List<Long> allIds = uzytkownikDaoCrud.findAllIds();
//        int listSize = allIds.size();
//        Random random = new Random();
//        int randomIndex = random.nextInt(listSize - 1);
//
//        if (randomIndex < 0) {
//            return null;
//        } else {
//            long randomID = allIds.get(randomIndex);
//            return findById(randomID);
//        }
//    }

    @Override
    public void save(Uzytkownik uzytkownik) {
        uzytkownikDaoCrud.save(uzytkownik);
    }

    @Override
    public void delete(Uzytkownik uzytkownik){
        uzytkownikDaoCrud.delete(uzytkownik);
    }
}
