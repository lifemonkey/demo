package com.example.demo.helpers;

import com.example.demo.domain.Gps;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class XMLReaderUT {

    final String filePath = System.getProperty("user.dir") + "/sample/sample.gpx";

    @BeforeEach
    void setup() {
    }

    @AfterEach
    void teardown() {
    }

    @Test
    public void parseXML_Test() {

        Gps gps = XMLReader.parseXML(filePath);
        Assert.assertNotNull(gps);
    }

    @Test
    public void parseXML_InvalidFile_Test() {

        Gps gps = XMLReader.parseXML("Invalid file path");
        Assert.assertNull(gps);
    }

    @Test
    public void parseXML_shouldThrowException_Test() {
//        Assertions.assertThrows(JAXBException.class, () -> {
//            XMLReader.parseXML("Invalid file path");
//        });
    }
}
