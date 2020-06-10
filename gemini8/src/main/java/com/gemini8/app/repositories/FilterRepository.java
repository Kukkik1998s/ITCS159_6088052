package com.gemini8.app.repositories;

import com.gemini8.app.model.Filter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilterRepository extends CrudRepository<Filter, Integer> {
    List<Filter> findAll();
}
