package project.modeling.modeling.services;

import org.springframework.stereotype.Service;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;

@Service
public class XmlValidator {

    public boolean validator(File xmlFile , File xsdFile){
        boolean verefied = false;
        StAXSource source = null;
        FileInputStream inputStream = null;
        try{
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            inputStream = new FileInputStream(xmlFile);
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
            source = new StAXSource(xmlStreamReader);
            validator.validate(source);
            verefied = true;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return verefied;
    }
}
