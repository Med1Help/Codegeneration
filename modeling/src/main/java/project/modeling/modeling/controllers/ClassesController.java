package project.modeling.modeling.controllers;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Element;
import project.modeling.modeling.models.Attributes;
import project.modeling.modeling.models.Classes;
import project.modeling.modeling.models.Packages;
import project.modeling.modeling.repositories.ClassesRepo;
import project.modeling.modeling.services.ClassesService;
import project.modeling.modeling.services.PackService;
import project.modeling.modeling.services.XmlValidator;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/classe/create")
public class ClassesController {
    private ClassesService classesService;
    private PackService packService;
    private XmlValidator validator;
    public ClassesController(ClassesService classesService,PackService packService,XmlValidator validator) {
        this.classesService = classesService;
        this.packService = packService;
        this.validator = validator;
    }

    @PostMapping("/postClasses")
    public boolean postClasses(@RequestBody Classes[] clss){
      boolean response = this.classesService.saveClasses(List.of(clss));
      return response;
    }
    @PostMapping
    public List<Classes> create(@RequestParam("packageName") String name) throws IOException, TransformerException {
        int  packId = packService.packName(name);
        List<Classes> clss = classesService.getClasses(packId);
        for(Classes cls : clss){
            if(!cls.getExtend().equals("false")){
                List<Classes> checkClasseExistence = classesService.getClasses(packId,cls.getExtend());
                if(checkClasseExistence.isEmpty()){
                    System.out.println("Classes to extends doesn't existes");
                    return clss;
                }
            }
        }
        //Generate XML file
        TransformerFactory transformerFact = TransformerFactory.newInstance();
        Transformer transformer = transformerFact.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        Element root =  packService.getRootElement(name,packId);
        classesService.createClassElement(clss,packService.getDocument(),root);
        DOMSource domSource = new DOMSource(packService.getDocument());
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(new File(name+".xml"));
        // write data
        transformer.transform(domSource, console);
        transformer.transform(domSource, file);
        //Generate Java Code
        ObjectMapper mapper = new ObjectMapper();
        File folder = packService.createPack(name);
        String sqlContent = "";
        for(Classes cls : clss){
            classesService.createClasse(folder.getPath(), cls.toString(),cls.getName());
            sqlContent += classesService.toStringSql(cls);

        }
        classesService.createSqlObj(folder.getPath(), sqlContent,"sql_objects");
         if(this.validator.validator(new File(name+".xml"),new File("metamodelXSD.xml"))){
             System.out.println("your model is verefied");
         }else{
             System.out.println("your model is not verefied");
         }
        return clss;
    }
}
