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
public class SubmitController {
    @Autowired
    private ScPlanRepository scRepo;

    @GetMapping("/submitPlan")
    public String submitPlan(Model model){
        Iterable<BaseSciencePlan> plans = scRepo.findAll();
        ArrayList<BaseSciencePlan> testedList = new ArrayList<>();
        for(BaseSciencePlan p: plans){
            if(p.getStatus()== BaseSciencePlan.STATUS.TESTED){
                testedList.add(p);
            }
        }
        model.addAttribute("testedList", testedList);
        return "submitPlan";
    }

    @PostMapping("submitted")
    public String submitted(@ModelAttribute("submit") String submit, @ModelAttribute("submitter") String submitter){
        BaseSciencePlan plan = scRepo.findById(Integer.parseInt(submit)).get();
        plan.setStatus(BaseSciencePlan.STATUS.SUBMITTED);
        plan.setSubmitter(submitter);
        scRepo.save(plan);
        return "submitted";
    }
}
