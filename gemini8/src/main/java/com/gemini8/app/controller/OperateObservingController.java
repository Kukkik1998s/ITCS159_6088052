package com.gemini8.app.controller;

import com.gemini8.app.OCS;
import com.gemini8.app.model.*;
import com.gemini8.app.repositories.*;
import jparsec.astronomy.CoordinateSystem;
import jparsec.ephem.Ephem;
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.Target;
import jparsec.ephem.planets.EphemElement;
import jparsec.observer.CityElement;
import jparsec.observer.LocationElement;
import jparsec.observer.ObserverElement;
import jparsec.time.AstroDate;
import jparsec.time.TimeElement;
import jparsec.util.JPARSECException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@SpringBootApplication(scanBasePackages={"com.gemini8.app.repositories.BaseObservingRepository"})
public class OperateObservingController {
    @Autowired
    private BaseObservingRepository obRepo;
    @Autowired
    private ScPlanRepository scPlan;
    @Autowired
    private LensRepository lenRepo;
    @Autowired
    private FilterRepository filterRepo;
    @Autowired
    private SpEquipRepository spRepo;

   // BaseSciencePlan selected;
    Boolean testResult = false;
    @GetMapping("/operate")
    public String selectPlan(Model model) {
        Iterable<BaseSciencePlan> plans = scPlan.findAll();
        //model.addAttribute("selected", selected);
        model.addAttribute("plans", plans);
        BaseObservingProgram ob = new BaseObservingProgram();
        ArrayList<Lens> lenList = lenRepo.findAll();
        ArrayList<Filter> filterList = filterRepo.findAll();
        ArrayList<SpecialEquipment> spList = spRepo.findAll();
        model.addAttribute("ob", ob);
        model.addAttribute("filterList", filterList);
        model.addAttribute("lenList", lenList);
        model.addAttribute("spList",spList);
        return "operate";
    }


    @PostMapping("/saveOb")
    public  String saveOb(@ModelAttribute("ob") BaseObservingProgram ob, @ModelAttribute("selected") String selected,
                          @ModelAttribute("exposure") String exposure, @ModelAttribute("virtual") String virtual) throws VirtualTelescope.NoSciencePlanException {
        int planNo = Integer.parseInt(selected);
        BaseSciencePlan plan = scPlan.findById(planNo).get();
        String[] parts = plan.getStartDate().split("-");
        Integer year = Integer.parseInt(parts[0]);
        Integer month = Integer.parseInt(parts[1]);
        String[] temp = parts[2].split(" ");
        Integer day = Integer.parseInt(temp[0]);
        OCS ocs = new OCS();
        LocationElement loc = ocs.getLocation(year, month, day, plan.getStarSystem());
        String[] exposures = exposure.split(",");
        ArrayList<Double> dataExposure = new ArrayList<>();
        for(String s:exposures){
            dataExposure.add(Double.parseDouble(s));
        }
        ob.setExposures(dataExposure);
        ob.setId(plan.getPlanNo());
        ob.setLoc(loc);
        obRepo.save(ob);
        plan.setObservingProgram(ob);
       // scPlan.save(plan);

        Date cur = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cur = format.parse( "2000-01-01" );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        VirtualTelescope vt = VirtualTelescope.getInstance();
        vt.setName(virtual);
        vt.setLocation(plan.getTelescopeLocation().toString());
        vt.setInstalledDate(cur);
        vt.setSciencePlan(plan);
        testResult = vt.executeSciencePlan();
        if(testResult==true){
            plan.setStatus(BaseSciencePlan.STATUS.TESTED);
        }
        scPlan.save(plan);
        return "saveOb";
    }

    @GetMapping("/testResult")
    public String testResult(Model model){
        String result;
        if(testResult==true){
            result =  "This plan is executable!";
        }else{
            result = "This plan is not executable!";
        }
        model.addAttribute("result", result);
        return "testResult";
    }


}
