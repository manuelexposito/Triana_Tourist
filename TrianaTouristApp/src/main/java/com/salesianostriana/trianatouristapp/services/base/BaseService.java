package com.salesianostriana.trianatouristapp.services.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseService<T, ID, R extends JpaRepository<T, ID>>{

    @Autowired
    protected R repository;


    public List<T> findAll(){

        return repository.findAll();
    }

    public T save(T t){
        return repository.save(t);
    }

    public List<T> saveAll(List<T> list){
        return repository.saveAll(list);
    }

}
