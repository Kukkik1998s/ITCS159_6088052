package com.gemini8.app.repositories;

import com.gemini8.app.model.SpecialEquipment;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface SpEquipRepository extends CrudRepository<SpecialEquipment, Integer> {
    ArrayList<SpecialEquipment> findAll();
}
