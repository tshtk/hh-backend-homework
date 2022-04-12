package ru.hh.school.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Salary {


    private int to;

    private int from;

    private String currency;

    private boolean gross;

}
