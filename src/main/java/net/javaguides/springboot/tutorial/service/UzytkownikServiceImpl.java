package net.javaguides.springboot.tutorial.service;

import net.javaguides.springboot.tutorial.dao.ProductDao;
import net.javaguides.springboot.tutorial.dao.UzytkownikDao;
import net.javaguides.springboot.tutorial.entity.Product;
import net.javaguides.springboot.tutorial.entity.Uzytkownik;
import net.javaguides.springboot.tutorial.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UzytkownikServiceImpl implements UzytkownikService{

    @Autowired
    private UzytkownikDao uzytkownikDaoImpl;

    @Override
    public List<Uzytkownik> getRegisteredUsers(){
        return uzytkownikDaoImpl.findAll();
    }

    public Uzytkownik findById (Long id) throws UzytkownikNotFoundException{
        Uzytkownik uzytkownik = uzytkownikDaoImpl.findById(id);

//        if (product == null){
//            throw new ProductNotFoundException();
//        }else{
//            product.increaseVisitCount();
//            productDaoImpl.update(product);
//        }

        return getUserAndUpdate(uzytkownik);
    }


    @Override
    public void save (Uzytkownik uzytkownik){
        uzytkownikDaoImpl.save(uzytkownik);
    }

    private Uzytkownik getUserAndUpdate(Uzytkownik uzytkownik) throws UzytkownikNotFoundException{
        if (uzytkownik == null) {

            throw new UzytkownikNotFoundException();
        } else {
//            product.increaseVisitCount();
            uzytkownikDaoImpl.update(uzytkownik);
        }
        return uzytkownik;
    }

    @Override
    public void delete(Uzytkownik uzytkownik){
        uzytkownikDaoImpl.delete(uzytkownik);
    }
}
