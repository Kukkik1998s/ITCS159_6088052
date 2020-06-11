/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package com.gemini8.app.model;

import com.gemini8.app.model.*;
import jparsec.observer.LocationElement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class BaseObservingProgram {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //@OneToOne(mappedBy = "observingProgram", cascade = CascadeType.ALL)
    private int id;

    private LocationElement loc;

    @OneToOne
    @JoinColumn
    private Lens lens;
    @ManyToMany
    @JoinColumn
    private List<Filter> filters = new ArrayList<>();

    private ArrayList<Double> exposures;
    private boolean isLightDetectorOn;
    @ManyToMany
    @JoinColumn
    private List<SpecialEquipment> specialEquipments = new ArrayList<>();
    @OneToOne
    @JoinColumn
    private AstronomicalData astroData;

    public BaseObservingProgram() {

    }

    public BaseObservingProgram(int id, LocationElement loc, Lens lens, ArrayList<Filter> filters,
                                ArrayList<Double> exposures, boolean isLightDetectorOn,
                                ArrayList<SpecialEquipment> specialEquipments,
                                AstronomicalData astroData) {
        this.id = id;
        this.loc = loc;
        this.lens = lens;
        this.filters = filters;
        this.exposures = exposures;
        this.isLightDetectorOn = isLightDetectorOn;
        this.specialEquipments = specialEquipments;
        this.astroData = astroData;
    }

   public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocationElement getLoc() {
        return loc;
    }

    public void setLoc(LocationElement loc) {
        this.loc = loc;
    }

    public Lens getLens() {
        return lens;
    }

    public void setLens(Lens lens) {
        this.lens = lens;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public ArrayList<Double> getExposures() {
        return exposures;
    }

    public void setExposures(ArrayList<Double> exposures) {
        this.exposures = exposures;
    }

    public boolean isLightDetectorOn() {
        return isLightDetectorOn;
    }

    public void setLightDetectorOn(boolean lightDetectorOn) {
        isLightDetectorOn = lightDetectorOn;
    }

    public List<SpecialEquipment> getSpecialEquipments() {
        return specialEquipments;
    }

    public void setSpecialEquipments(List<SpecialEquipment> specialEquipments) {
        this.specialEquipments = specialEquipments;
    }

    public AstronomicalData getAstroData() {
        return astroData;
    }

    public void setAstroData(AstronomicalData astroData) {
        this.astroData = astroData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseObservingProgram that = (BaseObservingProgram) o;
        return id == that.id &&
                isLightDetectorOn == that.isLightDetectorOn &&
                loc.equals(that.loc) &&
                Objects.equals(lens, that.lens) &&
                Objects.equals(filters, that.filters) &&
                Objects.equals(exposures, that.exposures) &&
                Objects.equals(specialEquipments, that.specialEquipments) &&
                Objects.equals(astroData, that.astroData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loc, lens, filters, exposures, isLightDetectorOn, specialEquipments, astroData);
    }

    @Override
    public String toString() {
        return "BaseObservingProgram{" +
                "id=" + id +
                ", loc=" + loc +
                ", lens=" + lens +
                ", filters=" + filters +
                ", exposures=" + exposures +
                ", isLightDetectorOn=" + isLightDetectorOn +
                ", specialEquipments=" + specialEquipments +
                ", astroData=" + astroData +
                '}';
    }
}
