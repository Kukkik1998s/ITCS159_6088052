package com.gemini8.app.repositories;


import com.gemini8.app.model.DataProcRequirement;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface DataProcRepository  extends CrudRepository<DataProcRequirement, Integer> {
    ArrayList<DataProcRequirement> findAll();
}
