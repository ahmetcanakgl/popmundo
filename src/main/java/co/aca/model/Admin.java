package co.aca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "popusers")
@JsonIgnoreProperties("inspection")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "ingame_name")
    private String ingameName;

    @Column(name = "date")
    private Date createdAt;

    public void setIngameName(String ingameName) {
        this.ingameName = ingameName;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getIngameName() {
        return ingameName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}