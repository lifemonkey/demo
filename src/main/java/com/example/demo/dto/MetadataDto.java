package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class MetadataDto implements Serializable {

    static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String desc;
    private String author;
    private String link;
    private String linkTxt;
    private LocalDate time;

    public MetadataDto() {}

    public MetadataDto(Long id, String name, String desc, String author, String link, String linkTxt, LocalDate time) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.link = link;
        this.linkTxt = linkTxt;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkTxt() {
        return linkTxt;
    }

    public void setLinkTxt(String linkTxt) {
        this.linkTxt = linkTxt;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MetadataDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", linkTxt='" + linkTxt + '\'' +
                ", time=" + time +
                '}';
    }
}
