package com.example.restaurantapi;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class TableRepository implements CrudRepository<Table,Long> {

    @Override
    public <S extends Table> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Table> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Table> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Table> findAll() {
        return null;
    }

    @Override
    public Iterable<Table> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Table entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Table> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
