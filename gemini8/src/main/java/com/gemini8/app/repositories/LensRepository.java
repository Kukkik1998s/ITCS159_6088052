package com.gemini8.app.repositories;

import com.gemini8.app.model.Lens;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LensRepository extends CrudRepository<Lens, Integer> {
    List<Lens> findAll();
}
