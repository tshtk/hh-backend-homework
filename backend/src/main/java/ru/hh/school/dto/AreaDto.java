package ru.hh.school.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hh.school.entity.Area;

@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;

    public AreaDto(final Area area) {
        this.id = area.getId();
        this.name = area.getName();
    }
    @JsonIgnore
    public Area getEntity() {
        return new Area(this.id, this.name);
    }
}
