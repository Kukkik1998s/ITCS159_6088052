/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package com.gemini8.app.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DataProcRequirement {

    public enum TYPE {
        RAW, PNG, JPEG, TIFF
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;


    @Enumerated(EnumType.STRING)
    private TYPE fileType;
    private double fileQuality;
    private double bw;
    private double colors;
    private double contrast;
    private double brightness;
    private double saturation;


    public DataProcRequirement() {

    }

    public DataProcRequirement(TYPE fileType, double fileQuality,
                               double bw, double colors, double contrast,
                               double brightness, double saturation) {
        this.fileType = fileType;
        this.fileQuality = fileQuality;
        this.bw = bw;
        this.colors = colors;
        this.contrast = contrast;
        this.brightness = brightness;
        this.saturation = saturation;
    }

    public TYPE getFileType() {
        return fileType;
    }

    public void setFileType(TYPE fileType) {
        this.fileType = fileType;
    }

    public double getFileQuality() {
        return fileQuality;
    }

    public void setFileQuality(double fileQuality) {
        this.fileQuality = fileQuality;
    }

    public double getBw() {
        return bw;
    }

    public void setBw(double bw) {
        this.bw = bw;
    }

    public double getColors() {
        return colors;
    }

    public void setColors(double colors) {
        this.colors = colors;
    }

    public double getContrast() {
        return contrast;
    }

    public void setContrast(double contrast) {
        this.contrast = contrast;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataProcRequirement that = (DataProcRequirement) o;
        return Double.compare(that.fileQuality, fileQuality) == 0 &&
                Double.compare(that.bw, bw) == 0 &&
                Double.compare(that.colors, colors) == 0 &&
                Double.compare(that.contrast, contrast) == 0 &&
                Double.compare(that.brightness, brightness) == 0 &&
                Double.compare(that.saturation, saturation) == 0 &&
                fileType == that.fileType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileType, fileQuality, bw, colors, contrast, brightness, saturation);
    }

    @Override
    public String toString() {
        return "DataProcRequirement{" +
                "fileType=" + fileType +
                ", fileQuality=" + fileQuality +
                ", bw=" + bw +
                ", colors=" + colors +
                ", contrast=" + contrast +
                ", brightness=" + brightness +
                ", saturation=" + saturation +
                '}';
    }
}
