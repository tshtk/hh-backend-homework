package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hh.school.entity.Employer;


@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavEmployerDtoShort {

    @JsonProperty("employer_id")
    private String id;
    @JsonProperty("comment")
    private String comment;
}
