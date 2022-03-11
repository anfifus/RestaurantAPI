package com.example.restaurantapi;

public class Table {

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
}
