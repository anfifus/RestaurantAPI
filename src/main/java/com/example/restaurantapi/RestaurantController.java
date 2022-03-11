package com.example.restaurantapi;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {
    private List<Restaurant> restaurantList = new ArrayList<>();

    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurantToAdd) {
        restaurantList.add(restaurantToAdd);
        return restaurantToAdd;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> showAll() {
        return restaurantList;
    }

    @DeleteMapping("/restaurants")
    public void deleteAll() {
        restaurantList.clear();
    }

    @GetMapping("/restaurants/{name}")
    public Restaurant getRestaurantById(@PathVariable String name) throws Exception {
        for (Restaurant currentRestaurant : restaurantList) {
            if (currentRestaurant.getName().equals(name)) {
                return currentRestaurant;
            }
        }
        throw new Exception("We didn't find a restaurant to show");
    }

    @DeleteMapping("/restaurants/{name}")
    public void deleteRestaurantById(@PathVariable String name) throws Exception{
        for (Restaurant currentRestaurant : new ArrayList<>(restaurantList)) {
            if (currentRestaurant.getName().equals(name)) {
                restaurantList.remove(currentRestaurant);
                return;
            }
        }
        throw new Exception("We didn't find a restaurant to delete");
    }
    @PutMapping("/restaurants/{name}")
    public void updateRestaurantById(@PathVariable String name,@RequestBody Restaurant restaurantUpdated) throws Exception{
        for (Restaurant currentRestaurant : restaurantList) {
            if (currentRestaurant.getName().equals(name)) {
                currentRestaurant.setType(restaurantUpdated.getType());
                return;
            }

        }
        throw new Exception("We didn't find a restaurant to update");
    }
}
