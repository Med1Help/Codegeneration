package project.modeling.modeling.repositories;

import org.springframework.data.repository.CrudRepository;
import project.modeling.modeling.models.MapClasseObj;

public interface MapRepo extends CrudRepository<MapClasseObj,Integer> {
    MapClasseObj findByClasseType(String classeType);
}
