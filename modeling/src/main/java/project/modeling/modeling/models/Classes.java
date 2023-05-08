package project.modeling.modeling.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Classes")
public class Classes {
    @GeneratedValue
    @Id
    private int id;
    private String name;
    private String attributes;
    private String functions;
    private int packageId;
    private String package_name;
    private String extend;
    private String implement;

    public Classes() {
    }

    public Classes(int id, String name, String attributes, String functions, int package_id, String extend, String implement ,String package_name) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
        this.functions = functions;
        this.packageId = package_id;
        this.extend = extend;
        this.implement = implement;
        this.package_name = package_name;
    }
    public String toString(){
        String myClasse = "package "+this.package_name+";\n" +
                "public class "+this.name+" ";
        if(!this.extend.equals("false")) {
            // --TODO check if classe that extended is existe

            myClasse += "extends " + this.extend + " ";
        }
        try {
            myClasse += " { \n";
            Attributes[] attr = this.getClassAttributes();
            List<Functions> functs = new ArrayList<>();
            functs.addAll(List.of(this.getClassFunctions()));
            for(Attributes att : attr){
                Functions gets = new Functions(att.getT(),"get"+att.getN(),new Attributes[0]);
                Attributes[] attrs = new Attributes[1];
                attrs[0] = new Attributes(att.getT(),att.getN());
                Functions sets = new Functions("void","set"+att.getN(),attrs);
                functs.add(gets);
                functs.add(sets);
                myClasse += "private "+att.getT()+" "+att.getN()+";\n";
            }
            myClasse += "public "+this.name+"(){}\n";
            for(Functions func : functs){
                myClasse += func.toString() +"\n";
            }
            myClasse += " } \n";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return myClasse;
    }
    public Attributes[] getClassAttributes(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Attributes[] attr = mapper.readValue(this.getAttributes(), Attributes[].class);
            return attr;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public Functions[] getClassFunctions(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Functions[] funcs = mapper.readValue(this.getFunctions(), Functions[].class);
            return funcs;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }
    public int getPackage_id() {
        return packageId;
    }

    public void setPackage_id(int package_id) {
        this.packageId = package_id;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getImplement() {
        return implement;
    }

    public void setImplement(String implement) {
        this.implement = implement;
    }
}
