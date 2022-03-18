package com.example.restaurantapi;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantRestController {
    private RestaurantService restaurantService;
    @Autowired
    public RestaurantRestController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }
    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurantToAdd) throws Exception {
        restaurantToAdd = restaurantService.createRestaurant(restaurantToAdd);
        return restaurantToAdd;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> showAll() throws Exception{
        return restaurantService.getRestaurantList();
    }

    @DeleteMapping("/restaurants")
    public void deleteAll() {
        restaurantService.deleteAll();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable String restaurantId) throws Exception {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public void deleteRestaurantById(@PathVariable String restaurantId) throws Exception{
        restaurantService.deleteRestaurantById(restaurantId);
    }
    @PutMapping("/restaurants/{restaurantId}")
    public void updateRestaurantById(@PathVariable String restaurantId,@RequestBody Restaurant restaurantUpdated) throws Exception{
        restaurantService.updateRestaurantById(restaurantId,restaurantUpdated);
    }
    @PostMapping("/restaurants/{restaurantId}/tables")
    public void addTableInRestaurant(@RequestBody String jsonClients, @PathVariable String restaurantId) throws Exception{

        JSONObject json = new JSONObject(jsonClients);
        int clients = json.getInt("currentSeats");

        restaurantService.addTableInRestaurant(clients,  restaurantId);
    }
    @GetMapping("restaurants/{restaurantId}/tables")
    public  List<Table> getAllTables(@PathVariable String restaurantId) throws Exception{
       return  restaurantService.getAllTables(restaurantId);
    }
    @DeleteMapping("restaurants/{restaurantId}/tables")
    public  void deleteAllTables(@PathVariable String restaurantId) throws Exception{
        restaurantService.deleteAllTables(restaurantId);
    }
    @GetMapping("restaurants/{restaurantId}/tables/{tableId}")
    public  Table getTable(@PathVariable String restaurantId,@PathVariable String tableId) throws Exception{
        return restaurantService.getTable(restaurantId,tableId);
    }
    @DeleteMapping("restaurants/{restaurantId}/tables/{tableId}")
    public void removeTable(@PathVariable String restaurantId,@PathVariable String tableId) throws Exception{
        restaurantService.removeTable(restaurantId,tableId);

    }
}
