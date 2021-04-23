package com.example.demo.helpers;

import com.example.demo.domain.Gps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class XMLReader {

    static final Logger log = LoggerFactory.getLogger(XMLReader.class);

    public static Gps parseXML(String filePath) {
        Gps gps = null;

        try {
            JAXBContext jc = JAXBContext.newInstance(Gps.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // ignore namespace
            dbf.setNamespaceAware(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filePath));
            // unmarshall document
            gps = (Gps) unmarshaller.unmarshal(new DOMSource(doc));
        } catch (ParserConfigurationException | SAXException | IOException | JAXBException e) {
            log.error("An exception thrown when reading XML file: " + e.getMessage());
        }

        return gps;
    }

    public static Gps parseXML(InputStream is) {
        Gps gps = null;

        try {
            JAXBContext jc = JAXBContext.newInstance(Gps.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // ignore namespace
            dbf.setNamespaceAware(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            // unmarshall document
            gps = (Gps) unmarshaller.unmarshal(new DOMSource(doc));
        } catch (ParserConfigurationException | SAXException | IOException | JAXBException e) {
            log.error("An exception thrown when reading XML file: " + e.getMessage());
        }

        return gps;
    }
}
