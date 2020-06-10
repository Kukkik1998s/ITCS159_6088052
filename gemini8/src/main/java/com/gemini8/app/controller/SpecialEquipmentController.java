package com.gemini8.app.controller;

import com.gemini8.app.model.SpecialEquipment;
import com.gemini8.app.repositories.SpEquipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpecialEquipmentController {
    @Autowired
    private SpEquipRepository spRepo;

    @GetMapping("/addEquipment")
    public String addEquipment(Model model){
        SpecialEquipment sp = new SpecialEquipment();
        model.addAttribute("sp",sp);
        return "addEquipment";
    }

    @PostMapping("/saveEquipment")
    public String saveEquipment(@ModelAttribute("sp") SpecialEquipment sp){
        spRepo.save(sp);
        return "saveEquipment";
    }
}
