package com.gemini8.app.controller;

import com.gemini8.app.model.BaseSciencePlan;
import com.gemini8.app.repositories.ScPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
@SpringBootApplication(scanBasePackages={"com.gemini8.app.repositories.ScPlanRepository"})
public class ValidateController {
    @Autowired
    private ScPlanRepository scRepo;

    @GetMapping("/validatePlan")
    public String validatePlan(Model model){
        Iterable<BaseSciencePlan> plans = scRepo.findAll();
        ArrayList<BaseSciencePlan> submittedList = new ArrayList<>();
        for(BaseSciencePlan p: plans){
            if(p.getStatus()== BaseSciencePlan.STATUS.SUBMITTED){
                submittedList.add(p);
            }
        }
        model.addAttribute("plans", submittedList);
        return "validatePlan";
    }

    @PostMapping("validated")
    public String submitted(@ModelAttribute("validate") String validate,
                            @ModelAttribute("check") String check){
        BaseSciencePlan plan = scRepo.findById(Integer.parseInt(validate)).get();
        if(check=="approve"){
            plan.setStatus(BaseSciencePlan.STATUS.RUNNING);
            validate = "This plan is approved!!";
        }else{
            validate = "This plan is not approved!!";
        }

        scRepo.save(plan);
        return "validated";
    }
}