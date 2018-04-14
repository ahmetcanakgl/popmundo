package co.aca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "popusers")
@JsonIgnoreProperties("inspection")
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "pop_id")
    private int popId;

    @Column(name = "ingame_name")
    private String ingameName;

    @Column(name = "date")
    private Date createdAt;

    @Column(name = "script_type")
    private String scriptType;

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

    public String getScriptType() {
        return scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getPopId() {
        return popId;
    }

    public void setPopId(int popId) {
        this.popId = popId;
    }
}