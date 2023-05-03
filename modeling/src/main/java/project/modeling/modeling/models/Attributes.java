package project.modeling.modeling.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Attributes {
    private String t;
    private String n;

    public Attributes() {
    }

    public Attributes(String type, String name) {
        this.t = type;
        this.n= name;
    }

    public String getType() {
        return t;
    }

    public void setType(String type) {
        this.t = type;
    }

    public String getName() {
        return n;
    }

    public void setName(String name) {
        this.n = name;
    }
}
