package project.modeling.modeling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import project.modeling.modeling.models.Classes;

import java.util.List;

public interface ClassesRepo extends CrudRepository<Classes,Integer> {
    List<Classes> findByPackageId(int package_id);
    List<Classes> findByPackageIdAndName(int package_id , String name);
}
