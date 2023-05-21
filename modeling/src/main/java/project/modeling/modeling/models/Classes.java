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
    private String agregate;
    private String composed;

    public Classes() {
    }

    public Classes(int id, String name, String attributes, String functions, int package_id, String extend, String implement,String agregate,String composed ,String package_name) {
        this.id             = id;
        this.name           = name;
        this.attributes     = attributes;
        this.functions      = functions;
        this.packageId      = package_id;
        this.extend         = extend;
        this.implement      = implement;
        this.agregate       = agregate;
        this.composed       = composed;
        this.package_name   = package_name;
    }
    public String toString(){
        String myClasse = "package "+this.package_name+";\n" +
                "public class "+this.name+" ";
        if(!this.extend.equals("false")) {
            // --TODO check if classe that extended is existe

            myClasse += "extends " + this.extend + " ";
        }
        if(this.getImplementations().length > 0){
            String[] implementations = this.getImplementations();
            myClasse                += "implements ";
            int leng                 = implementations.length;
            for(String implement : implementations){
                leng--;
                if(leng == 0)myClasse += implement+"";
                else myClasse += implement+" , ";
            }
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
            String[] agregations  = this.getAgregations();
            String[] compositions = this.getCompositions();
            String argConstructor = "";
            String bodConstructor = "";
            int leng = this.getAgregations().length;
            if(this.getAgregations().length > 0){
                leng--;
                for(String agregate : agregations){
                    myClasse                        += "private "+agregate+" "+agregate.toLowerCase()+"attr;\n";
                    if(leng == 0)argConstructor     += agregate+" "+agregate.toLowerCase()+"attr";
                    else argConstructor             += agregate+" "+agregate.toLowerCase()+"attr, ";
                    bodConstructor                  += "this."+agregate.toLowerCase()+"attr = "+agregate.toLowerCase()+"attr;\n";
                }
            }
            if(this.getCompositions().length > 0){
                for(String compose : compositions){
                    myClasse        += "private "+compose+" "+compose.toLowerCase()+"attr;\n";
                    bodConstructor  += "this."+compose.toLowerCase()+"attr = new "+compose+"();\n";
                }
            }
            myClasse += "public "+this.name+"("+argConstructor +"){"+bodConstructor+"}\n";
            for(Functions func : functs){
                myClasse += func.toString() +"\n";
            }
            myClasse += " } \n";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return myClasse;
    }
    public String[] getImplementations(){
        ObjectMapper mapper = new ObjectMapper();
        String[] implementations;
        try{
            implementations = mapper.readValue(this.implement, String[].class);
            return implementations;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new String[0];
    }
    public String[] getAgregations(){
        ObjectMapper mapper = new ObjectMapper();
        String[] agregations;
        try{
            agregations = mapper.readValue(this.agregate, String[].class);
            return agregations;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new String[0];
    }
    public String[] getCompositions(){
        ObjectMapper mapper = new ObjectMapper();
        String[] compositions;
        try{
            compositions = mapper.readValue(this.composed, String[].class);
            return compositions;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new String[0];
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

    public String getAgregate() {
        return agregate;
    }

    public void setAgregate(String agregate) {
        this.agregate = agregate;
    }

    public String getComposed() {
        return composed;
    }

    public void setComposed(String composed) {
        this.composed = composed;
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
