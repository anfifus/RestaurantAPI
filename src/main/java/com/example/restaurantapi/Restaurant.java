package com.example.restaurantapi;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private static final int MAX_CAPACITY = 24;
    private String name;
    private int type;
    private static final int PIZZA = 1;
    private static final int KEBAB = 2;
    private static final int CHINO = 3;



    @OneToMany(mappedBy = "restaurant")
    private List<Table> tables = new ArrayList<>();


    public Restaurant(){

    }
    public Restaurant(String name, int type) throws Exception {
        checkName(name);
        checkType(type);
        this.name = name;
        this.type = type;
    }

    /*public List<Table> getTables() {
        return tables;
    }*/

    public void setTables(List<Table> tables) {
        this.tables = tables;
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

    public int getCurrentSeats() {
        int result = 0;
        for (Table table : tables) {
            result += table.getCurrentSeats();
        }

        return result;
    }

    public int getRemainingSeats() {
        return MAX_CAPACITY - getCurrentSeats();
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
        if ((this.getCurrentSeats() + numOfPeople) > MAX_CAPACITY)
            throw new Exception("Massa gent");
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

   /* public String printTablesStatus() {
        String result = "";
        int i = 1;
        for (Table table : tables) {
            result += "Mesa " + i + " :" + table.getCurrentSeatings() + " personas \n";
            i++;
        }
        return result;
    }*/
    public void removeAllTables(){
        this.tables.clear();
    }
    public Table getTableByUID(String currentIdTable) throws Exception {
        for (Table currentTable:tables) {
            if (currentTable.getTableId().equals(currentIdTable)){
                return currentTable;
            }
        }
        throw  new Exception("Table not found");
    }
    public void removeTable(Table tableToRemove){
        tables.remove(tableToRemove);
    }

    public void setType(int type) throws Exception{

        checkType(type);
        this.type = type;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setName(String name) throws Exception{
        checkName(name);

        this.name = name;
    }
    public Long getId() {
        return id;
    }

}
