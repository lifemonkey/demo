package com.example.demo.domain;

import com.example.demo.helpers.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "metadata")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Metadata implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "desc")
    @Column(length = 1000)
    private String desc;
    @XmlElement(name = "author")
    private String author;
    @XmlElement(name = "link")
    @OneToOne(mappedBy = "metadata", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Link link;
    @XmlElement(name = "time")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate time;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gps_id")
    private Gps gps;

    public Metadata() {
    }

    public Metadata(String name, String desc, String author, Link link, LocalDate time) {
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.link = link;
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

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        if (link == null) {
            if (this.link != null) {
                this.link.setMetadata(null);
            }
        } else {
            link.setMetadata(this);
        }

        this.link = link;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", author='" + author + '\'' +
                ", link=" + link +
                ", time=" + time +
                ", gps=" + gps +
                '}';
    }
}
