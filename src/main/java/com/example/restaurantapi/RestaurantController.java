package com.example.restaurantapi;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RestaurantController {
    private List<Restaurant> restaurantList = new ArrayList<>();

    public Restaurant createRestaurant(Restaurant restaurantId) throws Exception {
        for (Restaurant currentRestaurant : restaurantList) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId)) {
                return currentRestaurant;
            }
        }
        throw new Exception("Restaurant not found");
    }

    public List<Restaurant> getRestaurantList() throws Exception {
        return restaurantList;
    }

    public void deleteAll(String restaurantId) throws Exception {
        for (Restaurant currentRestaurant : restaurantList) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId)) {
                currentRestaurant.getTables().clear();
                return;
            }
        }
        throw new Exception("The restaurant with this id" + restaurantId + " doesn't exist");
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
        for (Restaurant currentRestaurant : new ArrayList<>(restaurantList)) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId)) {
                restaurantList.remove(currentRestaurant);
                return;
            }
        }
        throw new Exception("We didn't find a restaurant to delete");
    }

    public void updateRestaurantById(String restaurantId, Restaurant restaurantUpdated) throws Exception {
        for (Restaurant currentRestaurant : restaurantList) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId)) {
                currentRestaurant.setName(restaurantUpdated.getName());
                currentRestaurant.setType(restaurantUpdated.getType());
                return;
            }

        }
        throw new Exception("We didn't find a restaurant to update");
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
            restaurant.getTables().clear();
        }
        return;
    }

    public Table getTable(String restaurantId, String tableId) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if (restaurant != null) {
            for (Table currentTable : restaurant.getTables()) {
                if (currentTable.getTableId().equals(tableId)) {
                    return currentTable;
                }
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
                restaurant.getTables().remove(tableId);
                return;
            }
            throw new Exception("The id of the table" + tableId + " doesn't exist");
        }


        throw new Exception("The id of the restaurant" + restaurantId + " doesn't exist");
    }
}
