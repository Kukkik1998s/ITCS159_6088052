package com.gemini8.app.controller;

import com.gemini8.app.repositories.ScPlanRepository;
import edu.gemini.app.ocs.model.BaseSciencePlan;
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
   public String createProjectForm(Model model) {

        BaseSciencePlan plan = new BaseSciencePlan();
        plan.setStartDate(Calendar.getInstance().getTime());
        plan.setEndDate(Calendar.getInstance().getTime());
        model.addAttribute("plan", plan);
        return "createPlan";
    }

    @PostMapping("/savePlan")
    public String saveProjectSubmission(@ModelAttribute("plan") BaseSciencePlan plan) {

        // TODO: save project in DB here

        return "result";
    }
}
