package com.gemini8.app.controller;

import com.gemini8.app.model.*;
import com.gemini8.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@SpringBootApplication(scanBasePackages={"com.gemini8.app.repositories.ScPlanRepository", "com.gemini8.app.repositories.DataProcRepository"})

public class CreateSciencePlanController {
    @Autowired
    private ScPlanRepository scRepo;
    @Autowired
    private DataProcRepository dataRepo;

    //@PreAuthorize("hasAuthority('ASTRONOMER')")
    @GetMapping("/createPlan")
   public String createProjectForm(Model model) {

        BaseSciencePlan plan = new BaseSciencePlan();
        plan.setStartDate(Calendar.getInstance().getTime());
        plan.setEndDate(Calendar.getInstance().getTime());
        //BaseObservingProgram ob = new BaseObservingProgram();
        //AstronomicalData astro = new AstronomicalData();
       // ob.setAstroData(astro);
        //plan.setObservingProgram(ob);
        ArrayList<DataProcRequirement> dataProcs = dataRepo.findAll();
        model.addAttribute("plan", plan);
        model.addAttribute("dataProcs", dataProcs);
        //DataProcRequirement dataprocs_Selected[] = new DataProcRequirement[10];
        //model.addAttribute("dataProcs_Selected", dataprocs_Selected);
        //ArrayList<DataProcRequirement> dataList = (ArrayList<DataProcRequirement>) model.getAttribute("dataOptions");
        //plan.setDataProcRequirements(dataList);
        return "createPlan";
    }

    @PostMapping("/savePlan")
    public String saveProjectSubmission(@ModelAttribute("plan") BaseSciencePlan plan){//, @ModelAttribute("dataProcs_Selected") DataProcRequirement[] dataProcs_Selected) {

        // TODO: save project in DB here
        scRepo.save(plan);
        return "result";
    }
}
