package com.gemini8.app.repositories;

import com.gemini8.app.model.Filter;
import com.gemini8.app.model.SpecialEquipment;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface FilterRepository extends CrudRepository<Filter, Integer> {
    ArrayList<Filter> findAll();
}
