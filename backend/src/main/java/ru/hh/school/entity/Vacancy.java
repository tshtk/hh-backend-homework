package ru.hh.school.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "favorites_vacancies")
public class Vacancy {
    @Id
    private int id;

    private String name;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "to", column = @Column(name = "salary_to")),
        @AttributeOverride(name = "from", column = @Column(name = "salary_from")),
        @AttributeOverride(name = "currency", column = @Column(name = "salary_currency")),
        @AttributeOverride(name = "gross", column = @Column(name = "salary_gross"))
    })
    private Salary salary;

    @Column(name = "created_at")
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private VacanciesEmployer employer;

    @Column(name = "views_count")
    private int viewsCount;

    private String comment;
}
