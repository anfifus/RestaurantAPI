package com.example.restaurantapi;

import java.util.Objects;
import java.util.UUID;

public class Table {

    private String tableId = UUID.randomUUID().toString();
    private int currentSeatings=0;

    public Table(){

    }

    public void addClients(int numOfPeople) throws Exception {
        checkValidNumOfPeople(numOfPeople);
        this.currentSeatings=numOfPeople;
    }

    private void checkValidNumOfPeople(int numOfPeople) throws Exception {
        if(numOfPeople<=0) throw new Exception();
    }

    public int getCurrentSeatings() {
        return currentSeatings;
    }

    public String getTableId() {
        return tableId;
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
}
