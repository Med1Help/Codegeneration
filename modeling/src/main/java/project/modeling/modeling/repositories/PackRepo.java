package project.modeling.modeling.repositories;

import org.springframework.data.repository.CrudRepository;
import project.modeling.modeling.models.Packages;

public interface PackRepo extends CrudRepository<Packages,Integer> {
}
