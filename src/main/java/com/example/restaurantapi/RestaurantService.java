package com.example.restaurantapi;

import org.hibernate.annotations.ManyToAny;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private List<Restaurant> restaurantList = new ArrayList<>();
    private RestaurantRepository repositoryRestaurant;
    private TableRepository repositoryTable;
    private EntityManager em;

    @Autowired
    public RestaurantService(RestaurantRepository repositoryRestaurant,TableRepository repositoryTable,EntityManager em){
        this.repositoryRestaurant = repositoryRestaurant;
        this.repositoryTable = repositoryTable;
        this.em = em;
    }
    public Restaurant createRestaurant(Restaurant restaurant) throws Exception {

        this.repositoryRestaurant.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> getRestaurantList() {
        List<Restaurant> restaurantTemporal = new ArrayList<>();
        repositoryRestaurant.findAll().forEach(restaurantTemporal::add);
        return restaurantTemporal;
    }

    public void deleteAll() {
        repositoryRestaurant.deleteAll();
    }

    public Restaurant getRestaurantById(String restaurantId) throws Exception {
        /*for (Restaurant currentRestaurant : restaurantList) {
            if (currentRestaurant.getRestaurantId().equals(restaurantId)) {
                return currentRestaurant;
            }
        }
        throw new Exception("We didn't find a restaurant to show");*/
        long id = Long.parseLong(restaurantId);
        Optional<Restaurant> restaurant = repositoryRestaurant.findById(id);
        if(restaurant.isPresent()){
            return restaurant.get();
        }
        throw new Exception("We didn't find a restaurant to show");
    }

    public void deleteRestaurantById(String restaurantId) throws Exception {
        Restaurant currentRestaurant = getRestaurantById(restaurantId);
        if (currentRestaurant != null) {
            repositoryRestaurant.delete(currentRestaurant);
        }

    }

    public void updateRestaurantById(String restaurantId, Restaurant restaurantUpdated) throws Exception {
        Restaurant currentRestaurant = getRestaurantById(restaurantId);
        if (currentRestaurant != null) {
            currentRestaurant.setName(restaurantUpdated.getName());
            currentRestaurant.setType(restaurantUpdated.getType());
            repositoryRestaurant.save(currentRestaurant);
        }
    }

    public void addTableInRestaurant( int clients, String idRestaurant) throws Exception {




        Restaurant restaurant = getRestaurantById(idRestaurant);
        restaurant.addClients(clients);
        repositoryTable.saveAll(restaurant.getTables());
     /*   if (restaurant != null) {
            restaurant.addClients(clients);
        }*/
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
