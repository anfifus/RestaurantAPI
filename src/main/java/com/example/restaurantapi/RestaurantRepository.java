package com.example.restaurantapi;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class RestaurantRepository implements CrudRepository<Restaurant,Long> {
    @Override
    public <S extends Restaurant> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Restaurant> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Restaurant> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Restaurant> findAll() {
        return null;
    }

    @Override
    public Iterable<Restaurant> findAllById(Iterable<Long> longs) {
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
    public void delete(Restaurant entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Restaurant> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
