package net.miwashi.crud.model;

import lombok.Data;
@Data
public class Book {

    public Book(String name){
        this.name = name;
    }
    private int id;
    private String name;
}
