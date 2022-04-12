package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.entity.VacanciesEmployer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployerDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    public VacanciesEmployer getEntity() {
        return new VacanciesEmployer(Integer.parseInt(this.id), this.name);
    }
}