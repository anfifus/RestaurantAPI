package com.example.restaurantapi;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {
    private  RestaurantService serviceRestaurant;
    @Autowired
    public RestaurantController(RestaurantService serviceRestaurant){
        this.serviceRestaurant = serviceRestaurant;
    }
    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurantToAdd) {
        serviceRestaurant.getRestaurantList().add(restaurantToAdd);
        return restaurantToAdd;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> showAll() {
        return serviceRestaurant.getRestaurantList();
    }

    @DeleteMapping("/restaurants")
    public void deleteAll() {
        serviceRestaurant.getRestaurantList().clear();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable String restaurantId) throws Exception {
        for (Restaurant currentRestaurant : serviceRestaurant.getRestaurantList()) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId)) {
                return currentRestaurant;
            }
        }
        throw new Exception("We didn't find a restaurant to show");
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public void deleteRestaurantById(@PathVariable String restaurantId) throws Exception{
        for (Restaurant currentRestaurant : new ArrayList<>(serviceRestaurant.getRestaurantList())) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId)) {
                serviceRestaurant.getRestaurantList().remove(currentRestaurant);
                return;
            }
        }
        throw new Exception("We didn't find a restaurant to delete");
    }
    @PutMapping("/restaurants/{restaurantId}")
    public void updateRestaurantById(@PathVariable String restaurantId,@RequestBody Restaurant restaurantUpdated) throws Exception{
        for (Restaurant currentRestaurant : serviceRestaurant.getRestaurantList()) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId)) {
                currentRestaurant.setName(restaurantUpdated.getName());
                currentRestaurant.setType(restaurantUpdated.getType());
                return;
            }

        }
        throw new Exception("We didn't find a restaurant to update");
    }
    @PostMapping("/restaurants/{restaurantId}/tables")
    public void addTableInRestaurant(@RequestBody String jsonClients, @PathVariable String restaurantId) throws Exception{

        JSONObject json = new JSONObject(jsonClients);

        int clients = json.getInt("clients");


        Restaurant restaurant = serviceRestaurant.findRestaurant(restaurantId);

        if(restaurant != null){
            restaurant.addClients(clients);
        }
    }
    @GetMapping("restaurants/{restaurantId}/tables")
    public  List<Table> getAllTables(@PathVariable String restaurantId) throws Exception{
        Restaurant restaurant = serviceRestaurant.findRestaurant(restaurantId);
        if(restaurant != null){
            return serviceRestaurant.getAllTables(restaurant);
        }
        return null;
    }
    @DeleteMapping("restaurants/{restaurantId}/tables")
    public  void deleteAllTables(@PathVariable String restaurantId) throws Exception{
        Restaurant restaurant = serviceRestaurant.findRestaurant(restaurantId);
        if(restaurant != null){
            serviceRestaurant.removeAllTables(restaurant);
        }
        return ;
    }
    @GetMapping("restaurants/{restaurantId}/tables/{tableId}")
    public  Table getTable(@PathVariable String restaurantId,@PathVariable String tableId) throws Exception{
        Restaurant restaurant = serviceRestaurant.findRestaurant(restaurantId);
        if(restaurant != null){
            return serviceRestaurant.getTable(restaurant,tableId);
        }
        return null;
    }
    @DeleteMapping("restaurants/{restaurantId}/tables/{tableId}")
    public void removeTable(@PathVariable String restaurantId,@PathVariable String tableId) throws Exception{
        Restaurant restaurant = serviceRestaurant.findRestaurant(restaurantId);
        if(restaurant != null){
            serviceRestaurant.removeTable(restaurant,tableId);
        }
    }
}
