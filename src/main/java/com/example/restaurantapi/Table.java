package com.example.restaurantapi;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;
import java.util.UUID;
@Entity
public class Table {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "tableId", nullable = false)
    private Long tableId;
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
}
