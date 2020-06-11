/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package com.gemini8.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Filter {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;


    private String make;
    private String manufacturer;
    private String model;
    private int year;
    private double size;
    private double weight;

    public Filter(){

    }

    public Filter(String make, String manufacturer, String model, int year, double size, double weight) {
        this.make = make;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.size = size;
        this.weight = weight;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "make=" + make +
                ", model=" + model +
                ", manufacturer=" + manufacturer +
                ", year=" + year +
                ", size=" + size +
                ", weight=" + weight +
                '}';
    }
}
