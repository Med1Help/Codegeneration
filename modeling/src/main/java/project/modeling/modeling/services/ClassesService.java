package project.modeling.modeling.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import project.modeling.modeling.models.Attributes;
import project.modeling.modeling.models.Classes;
import project.modeling.modeling.models.Functions;
import project.modeling.modeling.repositories.ClassesRepo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.w3c.dom.Node;
@Service
public class ClassesService {
    private ClassesRepo repo;

    public ClassesService(ClassesRepo repo) {
        this.repo = repo;
    }
    public List<Classes> getClasses(int package_id){
       return this.repo.findByPackageId(package_id);
    }
    public List<Classes> getClasses(int package_id,String name){
        return this.repo.findByPackageIdAndName(package_id,name);
    }
    public boolean saveClasses(List<Classes> clss){
        List<Classes> s = (List<Classes>) this.repo.saveAll(clss);
        return true;
    }
    public void createClasse(String path , String content , String name) throws IOException {
        File myclassFile = new File(path+"\\"+name+".java");
        myclassFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(myclassFile);
        fout.write(content.getBytes(StandardCharsets.UTF_8));
        fout.close();
    }
    public Node createClassElement(List<Classes> clss, Document document , Element rootElement){
       for(Classes cls : clss){
           Element classElement = document.createElement("Classe");
           classElement.setAttribute("name",cls.getName());
           classElement.setAttribute("id",cls.getId()+"");
           classElement.setAttribute("extends",cls.getExtend());
           Element attributes = document.createElement("Attributes");
           for(Attributes attr : cls.getClassAttributes()){
               Element attributeTag = document.createElement("Attribute");
               attributeTag.setAttribute("type",attr.getType());
               attributeTag.appendChild(document.createTextNode(attr.getName()));
               attributes.appendChild(attributeTag);
           }
           Element functions = document.createElement("Functions");
           for(Functions func : cls.getClassFunctions()){
               Element functionTag = document.createElement("Function");
               functionTag.setAttribute("return",func.getReturntype());
               functionTag.setAttribute("name",func.getName());
               Attributes[] params = func.getAttr();
               for(Attributes param : params){
                   Element paramTag = document.createElement("parameter");
                   paramTag.setAttribute("type",param.getType());
                   paramTag.appendChild(document.createTextNode(param.getName()));
                   functionTag.appendChild(paramTag);
               }
               functions.appendChild(functionTag);
           }
           classElement.appendChild(attributes);
           classElement.appendChild(functions);
           rootElement.appendChild(classElement);
       }
       return rootElement;
    }
}
