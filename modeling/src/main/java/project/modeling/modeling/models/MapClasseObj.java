package project.modeling.modeling.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="MapClasseObj")
public class MapClasseObj {
    @Id
    @GeneratedValue
    int id;
    @Column(unique = true)
    String classeType;
    @Column(unique = true)
    String objType;

    public MapClasseObj() {
    }

    public MapClasseObj(String classeType, String objType) {
        this.classeType = classeType;
        this.objType = objType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasseType() {
        return classeType;
    }

    public void setClasseType(String classeType) {
        this.classeType = classeType;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }
}
