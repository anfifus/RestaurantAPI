package com.example.restaurantapi;

import javax.persistence.*;

import java.util.Objects;

@Entity(name = "tables")
public class Table {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "tableId", nullable = false)
    private Long tableId;
    private static int MAX_CLIENTS = 6;
    private int currentSeats =0;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public Table(){

    }
    public Table(int clients,Restaurant restaurant) throws Exception {
        checkClients(clients);
        checkClientsMoreThan6(clients);
            this.currentSeats = clients;
            this.restaurant = restaurant;
    }
    private void checkClientsMoreThan6(int clients) throws Exception {
        if(clients > 6) {
            throw new Exception("You can't have a table with more than 6 clients");
        }
    }
    private void checkClients(int clients) throws Exception {
        if (clients == 0) throw new Exception("If we ask the number of clients is because we have");

    }

    public int addClients(int numOfPeople) throws Exception {
        checkValidNumOfPeople(numOfPeople);
        this.currentSeats = numOfPeople;
        if (this.currentSeats > MAX_CLIENTS) {
            this.currentSeats = MAX_CLIENTS;
        }
        return numOfPeople - currentSeats;
    }

    private void checkValidNumOfPeople(int numOfPeople) throws Exception {
        if(numOfPeople<=0) throw new Exception();
    }

    public int getCurrentSeats() {
        return currentSeats;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return tableId.equals(table.tableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId);
    }

    public Long getTableId() {
        return tableId;
    }
    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public long getRestaurantUID() {
        return restaurant.getId();
    }
}
