package com.example.restaurantapi;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantRestController {
    private RestaurantController restaurantController;
    @Autowired
    public RestaurantRestController(RestaurantController restaurantController){
        this.restaurantController = restaurantController;
    }
    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurantToAdd) throws Exception {
        restaurantToAdd = restaurantController.createRestaurant(restaurantToAdd);
        return restaurantToAdd;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> showAll() throws Exception{
        return restaurantController.getRestaurantList();
    }

    @DeleteMapping("/restaurants")
    public void deleteAll() {
        restaurantController.deleteAll();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable String restaurantId) throws Exception {
        return restaurantController.getRestaurantById(restaurantId);
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public void deleteRestaurantById(@PathVariable String restaurantId) throws Exception{
        restaurantController.deleteRestaurantById(restaurantId);
    }
    @PutMapping("/restaurants/{restaurantId}")
    public void updateRestaurantById(@PathVariable String restaurantId,@RequestBody Restaurant restaurantUpdated) throws Exception{
        restaurantController.updateRestaurantById(restaurantId,restaurantUpdated);
    }
    @PostMapping("/restaurants/{restaurantId}/tables")
    public void addTableInRestaurant(@RequestBody String jsonClients, @PathVariable String restaurantId) throws Exception{

        restaurantController.addTableInRestaurant(jsonClients,  restaurantId);
    }
    @GetMapping("restaurants/{restaurantId}/tables")
    public  List<Table> getAllTables(@PathVariable String restaurantId) throws Exception{
       return  restaurantController.getAllTables(restaurantId);
    }
    @DeleteMapping("restaurants/{restaurantId}/tables")
    public  void deleteAllTables(@PathVariable String restaurantId) throws Exception{
        restaurantController.deleteAllTables(restaurantId);
    }
    @GetMapping("restaurants/{restaurantId}/tables/{tableId}")
    public  Table getTable(@PathVariable String restaurantId,@PathVariable String tableId) throws Exception{
        return restaurantController.getTable(restaurantId,tableId);
    }
    @DeleteMapping("restaurants/{restaurantId}/tables/{tableId}")
    public void removeTable(@PathVariable String restaurantId,@PathVariable String tableId) throws Exception{
        restaurantController.removeTable(restaurantId,tableId);

    }
}
