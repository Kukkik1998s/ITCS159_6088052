package edu.gemini.app.ocs.repositories;

import edu.gemini.app.ocs.model.DataProcRequirement;
import org.springframework.data.repository.CrudRepository;

public interface DataProcRepository  extends CrudRepository<DataProcRequirement, Integer> {
}
