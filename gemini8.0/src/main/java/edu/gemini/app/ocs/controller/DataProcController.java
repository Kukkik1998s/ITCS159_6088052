package edu.gemini.app.ocs.controller;

import com.gemini8.app.model.BaseSciencePlan1;
import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.repositories.DataProcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@SpringBootApplication(scanBasePackages={"edu.gemini.app.ocs.repositories.DataProcRepository"})
public class DataProcController {
    @Autowired
    private DataProcRepository repo;

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
