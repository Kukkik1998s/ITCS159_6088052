package com.gemini8.app.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    public LocationElement getLocation(int year, int month, int day, Target.TARGET target) {
        /* Code example is adapted from EphemTest1.java by Alonso Albi */
        try {
            // We need three objects: TimeElement, ObserverElement, and EphemerisElement
            AstroDate astro = new AstroDate(year, month, day); // or Constant.J2000, or empty for current date/time, ...
            TimeElement time = new TimeElement(astro, TimeElement.SCALE.UNIVERSAL_TIME_UTC);
            CityElement city = new CityElement("Bangkok");
            ObserverElement obs = new ObserverElement(city);

            // The ephemeris object defines the target body and how to calculate ephemeris. The algorithm
            // is set to Moshier, which is the best way for general calculations
            EphemerisElement eph = new EphemerisElement(target, EphemerisElement.COORDINATES_TYPE.APPARENT,
                    EphemerisElement.EQUINOX_OF_DATE, EphemerisElement.TOPOCENTRIC, EphemerisElement.REDUCTION_METHOD.IAU_2006,
                    EphemerisElement.FRAME.DYNAMICAL_EQUINOX_J2000, EphemerisElement.ALGORITHM.MOSHIER);
            EphemElement ee = Ephem.getEphemeris(time, obs, eph, true); // Compute also rise/set/transit
            LocationElement gal = CoordinateSystem.equatorialToGalactic(ee.getEquatorialLocation(), time, obs, eph);
            return gal;
        } catch (JPARSECException e) {
            e.showException();
        }
        return null;
    }

   /* @GetMapping("/observing")
    public String operateObservingProgram(@RequestParam String selected, Model model) {
        int planNo = Integer.parseInt(selected);
        BaseSciencePlan plan = scPlan.findById(planNo).get();
       // BaseObservingProgram ob = new BaseObservingProgram();
        BaseObservingProgram ob = plan.getObservingProgram();
        ArrayList<Lens> lens = lenRepo.findAll();
        ArrayList<Filter> filterList = filterRepo.findAll();
        ArrayList<SpecialEquipment> spList = spRepo.findAll();
        model.addAttribute("ob", ob);
        model.addAttribute("filterList", filterList);
        model.addAttribute("lens", lens);
        model.addAttribute("spList",spList);
        return "observing";
    }*/

    @PostMapping("/saveOb")
    public  String saveOb(@ModelAttribute("ob") BaseObservingProgram ob, @ModelAttribute("selected") String selected, @ModelAttribute("exposure") String exposure){
        int planNo = Integer.parseInt(selected);
        BaseSciencePlan plan = scPlan.findById(planNo).get();
        String[] parts = plan.getStartDate().split("-");
        Integer year = Integer.parseInt(parts[0]);
        Integer month = Integer.parseInt(parts[1]);
        String[] temp = parts[2].split(" ");
        Integer day = Integer.parseInt(temp[0]);
        LocationElement loc = getLocation(year, month, day, plan.getStarSystem());
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
        scPlan.save(plan);
        return "saveOb";
    }

}
