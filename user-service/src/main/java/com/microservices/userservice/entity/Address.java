package com.microservices.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "street",
        "housenumber",
        "postalcode",
        "city",
        "geoLocation"
})
@Entity
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long AddressId;

    @JsonProperty("street")
    private String street;
    @JsonProperty("housenumber")
    private String housenumber;
    @JsonProperty("postalcode")
    private String postalcode;
    @JsonProperty("city")
    private String city;

    @JsonProperty("geoLocation")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="geoId")
    private GeoLocation geoLocation;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

}
