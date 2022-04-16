package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("area")
    private AreaDto area;
    @JsonProperty("salary")
    private SalaryDto salary;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("employer")
    private EmployerDto employer;
}
