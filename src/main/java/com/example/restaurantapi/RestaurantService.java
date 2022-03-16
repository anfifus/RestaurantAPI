package com.example.restaurantapi;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private List<Restaurant> restaurantList = new ArrayList<>();

    public Restaurant createRestaurant(Restaurant restaurantId) throws Exception {
        for (Restaurant currentRestaurant : restaurantList) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId.getRestaurantId())) {
                throw new Exception("Restaurant found" + currentRestaurant);
            }
        }
        restaurantList.add(restaurantId);
        return restaurantId;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void deleteAll() {
        restaurantList.clear();
    }

    public Restaurant getRestaurantById(String restaurantId) throws Exception {
        for (Restaurant currentRestaurant : restaurantList) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId)) {
                return currentRestaurant;
            }
        }
        throw new Exception("We didn't find a restaurant to show");
    }

    public void deleteRestaurantById(String restaurantId) throws Exception {

        Restaurant currentRestaurant = getRestaurantById(restaurantId);
        if (currentRestaurant != null) {
            restaurantList.remove(currentRestaurant);
        }

    }

    public void updateRestaurantById(String restaurantId, Restaurant restaurantUpdated) throws Exception {
        Restaurant currentRestaurant = getRestaurantById(restaurantId);
        if (currentRestaurant != null) {
            currentRestaurant.setName(restaurantUpdated.getName());
            currentRestaurant.setType(restaurantUpdated.getType());
        }
    }

    public void addTableInRestaurant(@RequestBody String jsonClients, @PathVariable String restaurantId) throws Exception {

        JSONObject json = new JSONObject(jsonClients);

        int clients = json.getInt("clients");


        Restaurant restaurant = getRestaurantById(restaurantId);

        if (restaurant != null) {
            restaurant.addClients(clients);
        }
    }

    public List<Table> getAllTables(String restaurantId) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if (restaurant != null) {
            return restaurant.getTables();
        }
        return null;
    }

    public void deleteAllTables(String restaurantId) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if (restaurant != null) {
            restaurant.removeAllTables();
        }
    }

    public Table getTable(String restaurantId, String tableId) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if (restaurant != null) {
            Table currentTable = restaurant.getTableByUID(tableId);
            if (currentTable != null) {
                return currentTable;
            }
            throw new Exception("The id of the table" + tableId + " doesn't exist");
        }
        throw new Exception("The id of the restaurant" + restaurantId + " doesn't exist");
    }

    public void removeTable(String restaurantId, String tableId) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if (restaurant != null) {
            Table currentTable = restaurant.getTableByUID(tableId);
            if (currentTable != null) {
                restaurant.removeTable(currentTable);
                return;
            }
            throw new Exception("The id of the table" + tableId + " doesn't exist");
        }


        throw new Exception("The id of the restaurant" + restaurantId + " doesn't exist");
    }
}
