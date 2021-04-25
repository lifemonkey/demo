package com.example.demo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "link")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Link implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @XmlAttribute(name = "href")
    private String href;
    @XmlElement(name = "text")
    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metadata_id")
    private Metadata metadata;

    public Link() {
    }

    public Link(String href, String text, Metadata metadata) {
        this.href = href;
        this.text = text;
        this.metadata = metadata;
    }

    public Link withHref(String href) {
        this.href = href;
        return this;
    }

    public Link withText(String text) {
        this.text = text;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", href='" + href + '\'' +
                ", text='" + text + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
