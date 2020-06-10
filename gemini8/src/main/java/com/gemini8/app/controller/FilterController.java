package com.gemini8.app.controller;

import com.gemini8.app.model.Filter;
import com.gemini8.app.repositories.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilterController {
    @Autowired
    private FilterRepository filterRepo;

    @GetMapping("/addFilter")
    public String addFilter(Model model){
        Filter filter = new Filter();
        model.addAttribute("filter", filter);
        return "addFilter";
    }

    @PostMapping("/saveFilter")
    public String saveFilter(@ModelAttribute("filter") Filter filter){
        filterRepo.save(filter);
        return "saveFilter";
    }

}
