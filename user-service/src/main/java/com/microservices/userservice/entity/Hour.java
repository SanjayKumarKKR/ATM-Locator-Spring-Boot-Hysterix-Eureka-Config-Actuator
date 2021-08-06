package com.microservices.userservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hourFrom",
        "hourTo"
})
@Entity
public class Hour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long HoursId;

    @JsonProperty("hourFrom")
    private String hourFrom;

    @JsonProperty("hourTo")
    private String hourTo;

    public String getHourFrom() {
        return hourFrom;
    }

    public void setHourFrom(String hourFrom) {
        this.hourFrom = hourFrom;
    }

    public String getHourTo() {
        return hourTo;
    }

    public void setHourTo(String hourTo) {
        this.hourTo = hourTo;
    }

    @Override
    public String toString() {
        return "Hour{" +
                "hourFrom='" + hourFrom + '\'' +
                ", hourTo='" + hourTo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hour)) return false;
        Hour hour = (Hour) o;
        return Objects.equals(hourFrom, hour.hourFrom) && Objects.equals(hourTo, hour.hourTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hourFrom, hourTo);
    }
}
