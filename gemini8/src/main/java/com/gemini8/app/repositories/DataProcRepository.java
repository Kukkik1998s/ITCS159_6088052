package com.gemini8.app.repositories;


import com.gemini8.app.model.DataProcRequirement;
import org.springframework.data.repository.CrudRepository;

public interface DataProcRepository  extends CrudRepository<DataProcRequirement, Integer> {
}
