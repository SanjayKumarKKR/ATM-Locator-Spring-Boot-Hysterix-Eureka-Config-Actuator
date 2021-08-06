package com.microservices.userservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class ApiResponseObject implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long  responseId;

    @OneToMany(targetEntity= ATMLocation.class,cascade=CascadeType.ALL)
    private List<ATMLocation> list;

    public List<ATMLocation> getList() {
        return list;
    }

    public void setList(List<ATMLocation> list) {
        this.list = list;
    }
}
