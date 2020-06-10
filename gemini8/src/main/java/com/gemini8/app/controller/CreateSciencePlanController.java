package com.gemini8.app.controller;

import com.gemini8.app.model.*;
import com.gemini8.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@Controller
@SpringBootApplication(scanBasePackages={"com.gemini8.app.repositories.ScPlanRepository"})

public class CreateSciencePlanController {
    @Autowired
    private ScPlanRepository scRepo;


    @GetMapping("/createPlan")
   public String createProjectForm(Model model, Model observing) {

        BaseSciencePlan plan = new BaseSciencePlan();
        plan.setStartDate(Calendar.getInstance().getTime());
        plan.setEndDate(Calendar.getInstance().getTime());
        BaseObservingProgram ob = new BaseObservingProgram();
        //AstronomicalData astro = new AstronomicalData();
       // ob.setAstroData(astro);
        plan.setObservingProgram(ob);
        model.addAttribute("plan", plan);
        return "createPlan";
    }

    @PostMapping("/savePlan")
    public String saveProjectSubmission(@ModelAttribute("plan") BaseSciencePlan plan) {

        // TODO: save project in DB here
        scRepo.save(plan);
      return "result";
    }
}
