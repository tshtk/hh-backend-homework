package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import ru.hh.school.entity.Vacancy;
import ru.hh.school.popularity.Popularity;


@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavVacancyDtoFull {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("date_create")
    private String dateCreate;
    @JsonProperty("area")
    private AreaDto area;
    @JsonProperty("salary")
    private SalaryDto salary;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("employer")
    private VacanciesEmployerDto employer;
    @JsonProperty("popularity")
    private Popularity popularity;
    @JsonProperty("views_count")
    private int viewsCount;
    @JsonProperty("comment")
    private String comment;

    public FavVacancyDtoFull(final Vacancy vacancy, final int threshold) {
        this.id = String.valueOf(vacancy.getId());
        this.name = vacancy.getName();
        this.dateCreate = vacancy.getDateCreate().toString();
        this.area = new AreaDto(vacancy.getArea());
        this.salary = vacancy.getSalary() == null ? null : new SalaryDto(vacancy.getSalary());
        this.createdAt = vacancy.getCreatedAt();
        this.employer = vacancy.getEmployer() == null ? null : new VacanciesEmployerDto(vacancy.getEmployer());
        this.viewsCount = vacancy.getViewsCount();
        this.popularity = this.viewsCount > threshold ? Popularity.POPULAR : Popularity.REGULAR;
        this.comment = vacancy.getComment();
    }
}
