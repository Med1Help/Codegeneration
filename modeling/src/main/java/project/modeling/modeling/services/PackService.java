package project.modeling.modeling.services;

import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import project.modeling.modeling.models.Packages;
import project.modeling.modeling.repositories.PackRepo;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

@Service
public class PackService {
    private PackRepo repo;
    private Document document;

    public PackService(PackRepo repo) {
        this.repo = repo;
    }
    public Element getRootElement(String packName,int packId){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            this.document = dbBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Element rootElement = this.document.createElement("Package");
        rootElement.setAttribute("id",""+packId);
        rootElement.setAttribute("name",""+packName);
        this.document.appendChild(rootElement);
        return rootElement;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void savePack(String name){
        this.repo.save(new Packages(name));
    }
    public String packName(int id){
        return this.repo.findById(id).get().getName();
    }
    public File createPack(String name){
        File folder = new File(name);
        if(folder.mkdir()){
            System.out.println("Directory is created");
        }else{
            System.out.println("Directory doesn't created");
        }
        return folder;
    }

}
