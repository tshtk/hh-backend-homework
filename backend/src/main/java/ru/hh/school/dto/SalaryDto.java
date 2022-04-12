package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import ru.hh.school.entity.Salary;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalaryDto {

    @JsonProperty("to")
    private int to;
    @JsonProperty("from")
    private int from;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("gross")
    private boolean gross;

    public SalaryDto(final Salary salary) {
        this.to = salary.getTo();
        this.from = salary.getFrom();
        this.currency = salary.getCurrency();
        this.gross = salary.isGross();
    }

    @JsonIgnore
    public Salary getEntity() {
        return new Salary(this.to, this.from, this.currency, this.gross);
    }

}
