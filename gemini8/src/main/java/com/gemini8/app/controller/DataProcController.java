package com.gemini8.app.controller;

import com.gemini8.app.repositories.DataProcRepository;
import com.gemini8.app.model.DataProcRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@SpringBootApplication(scanBasePackages={"com.gemini8.app.repositories.DataProcRepository"})
public class DataProcController {
    private DataProcRepository repo;
    @Autowired
    public DataProcController(DataProcRepository repo)
    {
        this.repo = repo;
    }

    @GetMapping("/dataProc")
    public String createDataRequirement(Model model) {
        DataProcRequirement req = new DataProcRequirement();
        model.addAttribute("req", req);
        return "dataProc";
    }

    @PostMapping("/saveReq")
    public String saveRequirement(@ModelAttribute("req") DataProcRequirement req) {
        repo.save(req);
        return "requirement";
    }

}
