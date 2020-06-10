package com.gemini8.app.controller;

import com.gemini8.app.model.DataProcRequirement;
import com.gemini8.app.model.Lens;
import com.gemini8.app.repositories.LensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@SpringBootApplication(scanBasePackages={"com.gemini8.app.repositories.LensRepository"})
public class LensController {

    @Autowired
    private LensRepository lenRepo;

    @GetMapping("/addLen")
    public String addLen(Model model) {
        Lens len = new Lens();
        model.addAttribute("len", len);
        return "addLen";
    }

    @PostMapping("/saveLen")
    public String saveLen(@ModelAttribute("len") Lens len) {
        lenRepo.save(len);
        return "saveLen";
    }

}
