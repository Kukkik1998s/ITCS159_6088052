/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package com.gemini8.app.model;

import com.gemini8.app.model.DataProcRequirement;
import jparsec.ephem.Target;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class BaseSciencePlan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int planNo;

    private String creator;
    private String submitter;
    private double fundingInUSD;
    private String objectives;

    @Enumerated(EnumType.STRING)
    private Target.TARGET starSystem;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private TELESCOPELOC telescopeLocation;

    @ManyToMany
    @JoinColumn
    private List<DataProcRequirement> dataProcRequirements = new ArrayList<>();

    @OneToOne
    @JoinColumn
    private BaseObservingProgram observingProgram;

    private STATUS status;

    public enum TELESCOPELOC {
        HAWAII, CHILE
    }

    public enum STATUS {
        COMPLETE, RUNNING, SUBMITTED
    }

    public BaseSciencePlan() {

    }

    public BaseSciencePlan(String creator, String submitter, double fundingInUSD,
                           String objectives, Target.TARGET starSystem, Date startDate,
                           Date endDate, TELESCOPELOC telescopeLocation,
                           ArrayList<DataProcRequirement> dataProcRequirements) {
        this.creator = creator;
        this.submitter = submitter;
        this.fundingInUSD = fundingInUSD;
        this.objectives = objectives;
        this.starSystem = starSystem;
        this.startDate = startDate;
        this.endDate = endDate;
        this.telescopeLocation = telescopeLocation;
        this.dataProcRequirements = dataProcRequirements;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public double getFundingInUSD() {
        return fundingInUSD;
    }

    public void setFundingInUSD(double fundingInUSD) {
        this.fundingInUSD = fundingInUSD;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public Target.TARGET getStarSystem() {
        return starSystem;
    }

    public void setStarSystem(Target.TARGET starSystem) {
        this.starSystem = starSystem;
    }

    public String getStartDate() {
        return startDate.toString();
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //return dateFormat.format(startDate);
    }

    public void setStartDate(String startDate) {
        try {
            this.startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getEndDate()
    {
        return endDate.toString();
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //return dateFormat.format(endDate);
    }

    public void setEndDate(String endDate) {
        try {
            this.endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public TELESCOPELOC getTelescopeLocation() {
        return telescopeLocation;
    }

    public void setTelescopeLocation(TELESCOPELOC telescopeLocation) {
        this.telescopeLocation = telescopeLocation;
    }

    public List<DataProcRequirement> getDataProcRequirements() {
        return dataProcRequirements;
    }

    public void setDataProcRequirements(ArrayList<DataProcRequirement> dataProcRequirements) {
        this.dataProcRequirements = dataProcRequirements;
    }

    public BaseObservingProgram getObservingProgram() {
        return observingProgram;
    }

    public void setObservingProgram(BaseObservingProgram observingProgram) {
        this.observingProgram = observingProgram;
    }

    public int getPlanNo() {
        return planNo;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseSciencePlan that = (BaseSciencePlan) o;
        return planNo == that.planNo &&
                Double.compare(that.fundingInUSD, fundingInUSD) == 0 &&
                creator.equals(that.creator) &&
                submitter.equals(that.submitter) &&
                objectives.equals(that.objectives) &&
                starSystem == that.starSystem &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                telescopeLocation.equals(that.telescopeLocation) &&
                Objects.equals(dataProcRequirements, that.dataProcRequirements) &&
                Objects.equals(observingProgram, that.observingProgram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planNo, creator, submitter, fundingInUSD, objectives, starSystem,
                startDate, endDate, telescopeLocation, dataProcRequirements, observingProgram);
    }

    @Override
    public String toString() {
        return "BaseSciencePlan{" +
                "planNo=" + planNo +
                ", creator='" + creator + '\'' +
                ", submitter='" + submitter + '\'' +
                ", fundingInUSD=" + fundingInUSD +
                ", objectives='" + objectives + '\'' +
                ", starSystem=" + starSystem +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", telescopeLocation='" + telescopeLocation + '\'' +
                '}';
    }
}
