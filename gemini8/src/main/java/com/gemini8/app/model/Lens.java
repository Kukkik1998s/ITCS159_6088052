/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package com.gemini8.app.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lens {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;


    private String make;
    private String model;
    private String manufacturer;
    private int year;

    public Lens(){

    }

    public Lens(String make, String model, String manufacturer, int year) {
        this.make = make;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Lens{" +
                "make=" + make +
                ", model=" + model +
                ", manufacturer=" + manufacturer +
                ", year=" + year +
                '}';
    }
}
