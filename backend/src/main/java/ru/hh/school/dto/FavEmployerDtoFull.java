package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hh.school.entity.Employer;
import ru.hh.school.popularity.Popularity;


@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavEmployerDtoFull {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("data_create")
    private String dateCreate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("area")
    private AreaDto area;

    @JsonProperty("comment")
    private String comment;

    @Setter
    @JsonProperty("popularity")
    private Popularity popularity;

    @JsonProperty("views_count")
    private int viewsCount;




    public FavEmployerDtoFull(Employer employer, int threshold) {
        this.id = employer.getId();
        this.name = employer.getName();
        this.dateCreate = employer.getDateCreate().toString();
        this.description = employer.getDescription();
        this.area = new AreaDto(employer.getArea());
        this.comment = employer.getComment();
        this.viewsCount = employer.getViewsCount();
        this.popularity = this.viewsCount > threshold ? Popularity.POPULAR : Popularity.REGULAR;

    }
}
