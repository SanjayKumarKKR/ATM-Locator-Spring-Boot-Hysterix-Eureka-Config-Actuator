package com.atmlocator.atmlocator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dayOfWeek",
        "hours"
})
public class OpeningHours {

    @JsonProperty("dayOfWeek")
    private Integer dayOfWeek;

    @JsonProperty("hours")
    private List<Hour> hours;

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "OpeningHours{" +
                "dayOfWeek=" + dayOfWeek +
                ", hours=" + hours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpeningHours)) return false;
        OpeningHours that = (OpeningHours) o;
        return Objects.equals(dayOfWeek, that.dayOfWeek) && Objects.equals(hours, that.hours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, hours);
    }
}
