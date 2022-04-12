package ru.hh.school.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "favorites_employers")
public class Employer {
    @Id
    private int id;

    private String name;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    private String comment;

    @Column(name = "views_count")
    private int viewsCount;


}
