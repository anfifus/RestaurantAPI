package com.example.restaurantapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

}
