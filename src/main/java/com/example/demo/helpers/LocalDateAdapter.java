package com.example.demo.helpers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.*;

public class LocalDateAdapter extends XmlAdapter<String, LocalDateTime> {

    public LocalDateTime unmarshal(String v) throws Exception {
        Instant instant = Instant.parse(v);
        return LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.toString();
    }
}
