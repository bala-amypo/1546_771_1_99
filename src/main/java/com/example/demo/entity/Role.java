package com.example.demo.entity;

public class Role {
    private long id;
    private String name;
    public Role(){

    }
    public Role(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
