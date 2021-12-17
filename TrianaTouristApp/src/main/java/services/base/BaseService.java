package services.base;

import errors.exceptions.ListEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<T, ID, R extends JpaRepository<T, ID>>{

    @Autowired
    protected R repository;


    //TODO : Ver si borrar el baseservice y hacerlo todo directamente en los servicios
    /*
    public List<T> findAll(){

        List<T> obj = repository.findAll();

        if(obj.isEmpty()){
            throw new ListEntityNotFoundException();
        }

    }*/

}
