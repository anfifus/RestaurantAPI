package com.example.restaurantapi;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int MAX_CAPACITY = 24;
    private String name;
    private int type;
    private static final int PIZZA = 1;
    private static final int KEBAB = 2;
    private static final int CHINO = 3;

    private List<Table> tables = new ArrayList<>();

    public Restaurant(String name, int type) throws Exception {
        checkName(name);
        checkType(type);
        this.name = name;
        this.type = type;
    }

    private void checkType(int type) throws Exception {
        if (type != PIZZA && type != CHINO && type != KEBAB) {
            throw new Exception();
        }
    }

    public int getType() {
        return type;
    }

    private void checkName(String name) throws Exception {
        if (name.equals("")) throw new Exception();
    }

    public String getName() {
        return name;
    }

    public int getCurrentSeatings() {
        int result = 0;
        for (Table table : tables) {
            result += table.getCurrentSeatings();
        }
        return result;
    }

    public int getRemainingSeats() {
        return MAX_CAPACITY - getCurrentSeatings();
    }


    public void addClients(int numOfPeople) throws Exception {
        checkPeopleCanEnter(numOfPeople);
        tables.add(createTable(numOfPeople));
    }

    private Table createTable(int numOfPeople) throws Exception {
        Table table = new Table();
        table.addClients(numOfPeople);
        return table;
    }

    private void checkPeopleCanEnter(int numOfPeople) throws Exception {
        if ((this.getCurrentSeatings() + numOfPeople) > MAX_CAPACITY)
            throw new Exception("Massa gent");
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public String printTablesStatus() {
        String result = "";
        int i = 1;
        for (Table table : tables) {
            result += "Mesa " + i + " :" + table.getCurrentSeatings() + " personas \n";
            i++;
        }
        return result;
    }

    public void removeTableOnIndex(int pos) throws Exception {
        if(this.tables.isEmpty() || pos>this.tables.size()){
            throw new Exception();
        }
        this.tables.remove(pos);
    }

    public void setType(int type) {
        this.type = type;
    }
}
