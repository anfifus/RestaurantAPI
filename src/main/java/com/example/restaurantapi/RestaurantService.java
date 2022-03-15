package com.example.restaurantapi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RestaurantService {
    private List<Restaurant> restaurantList = new ArrayList<>();




    public Table getTable(Restaurant currentRestaurant, String currentIdTable) throws Exception{
        Table table = currentRestaurant.getTableByUID(currentIdTable);
        return table;
    }
    public void removeTable(Restaurant currentRestaurant, String tableId) throws Exception{
        Table table = currentRestaurant.getTableByUID(tableId);
        currentRestaurant.removeTable(table);
    }
}
