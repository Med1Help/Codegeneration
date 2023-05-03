package project.modeling.modeling.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Functions {
    private String returntype;
    private String nam;
    private Attributes[] attr;

    public Functions() {
    }

    public Functions(String returntype, String name, Attributes[] attr) {
        this.returntype = returntype;
        this.nam = name;
        this.attr = attr;
    }
    public String toString(){
        String myFunc = "private "+this.getReturntype()+" "+this.nam+"(";
        ObjectMapper mapper = new ObjectMapper();
        try {
            //Attributes[] attr = mapper.readValue(this.getAttr(), Attributes[].class);
            int len = attr.length;
            for(Attributes att : attr){
                myFunc += att.getT()+" "+att.getN()+"";
                len--;
                if(len > 0) myFunc+=",";

            }
            myFunc += "){}";

        }catch (Exception e){
            System.out.println(e.toString());
        }
        return myFunc;
    }
    public String getReturntype() {
        return returntype;
    }

    public void setReturntype(String returntype) {
        this.returntype = returntype;
    }

    public String getName() {
        return nam;
    }

    public void setName(String name) {
        this.nam = name;
    }

    public Attributes[] getAttr() {
        return attr;
    }

    public void setAttr(Attributes[] attr) {
        this.attr = attr;
    }

}
