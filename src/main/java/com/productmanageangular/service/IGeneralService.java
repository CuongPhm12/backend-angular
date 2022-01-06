package com.productmanageangular.service;

import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll ();
    Optional<T> findById(Long id);
    void delete(Long id);
    void save(T t);

}
