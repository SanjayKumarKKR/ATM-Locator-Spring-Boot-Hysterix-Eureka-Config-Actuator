package com.microservices.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "address",
        "distance",
        "openingHours",
        "functionality",
        "type"
})
@Entity
public class ATMLocation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  ATMId;

    @JsonProperty("address")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="AddressId")
    private Address address;

    @JsonProperty("distance")
    private Integer distance;

    @JsonProperty("openingHours")
    @OneToMany(targetEntity= OpeningHours.class,cascade=CascadeType.ALL)
    private List<OpeningHours> openingHours;

    @JsonProperty("functionality")
    private String functionality;

    @JsonProperty("type")
    private String type;


    public List<OpeningHours> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<OpeningHours> openingHours) {
        this.openingHours = openingHours;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
