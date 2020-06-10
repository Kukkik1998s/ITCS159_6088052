package com.gemini8.app.repositories;

import com.gemini8.app.model.BaseObservingProgram;
import org.springframework.data.repository.CrudRepository;

public interface BaseObservingRepository extends CrudRepository<BaseObservingProgram, Integer> {
}
