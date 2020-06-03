package com.gemini8.app.controller;

import com.gemini8.app.repositories.ScPlanRepository;
import com.gemini8.app.model.BaseSciencePlan1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@SpringBootApplication(scanBasePackages={"com.gemini8.app.repositories.ScPlanRepository"})
public class CreateSciencePlanController {
    @Autowired
    private ScPlanRepository scRepo;

    @GetMapping("/createPlan")
   public String createProjectForm(Model model) {

        BaseSciencePlan1 plan = new BaseSciencePlan1();
        plan.setStartDate(Calendar.getInstance().getTime());
        plan.setEndDate(Calendar.getInstance().getTime());
        model.addAttribute("plan", plan);
        //scRepo.save(plan);
        return "createPlan";
    }

    @PostMapping("/savePlan")
    public String saveProjectSubmission(@ModelAttribute("plan") BaseSciencePlan1 plan) {

        // TODO: save project in DB here
        scRepo.save(plan);
        return "result";
    }
}
