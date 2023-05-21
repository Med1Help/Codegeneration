package project.modeling.modeling.repositories;

import org.springframework.data.repository.CrudRepository;
import project.modeling.modeling.models.Packages;

import java.util.List;
import java.util.Map;

public interface PackRepo extends CrudRepository<Packages,Integer> {
    List<Packages> findByName(String name);
}
