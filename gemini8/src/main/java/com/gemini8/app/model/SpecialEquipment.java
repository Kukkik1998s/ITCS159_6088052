/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package com.gemini8.app.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SpecialEquipment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String equipmentName;
    private String ownerName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date installedDate;

    public SpecialEquipment(){

    }
    public SpecialEquipment(String equipmentName, String ownerName, Date installedDate) {
        this.equipmentName = equipmentName;
        this.ownerName = ownerName;
        this.installedDate = installedDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    @Override
    public String toString() {
        return "SpecialEquipment{" +
                "equipmentName=" + equipmentName +
                ", ownerName=" + ownerName +
                ", installedDate=" + installedDate +
                '}';
    }
}
