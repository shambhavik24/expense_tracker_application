package com.shambhviii.expense_tracker_application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shambhviii.expense_tracker_application.dto.IncomeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer amount;
    private LocalDate date;
    private String category;
    private String description;

    @JsonIgnore
    public  IncomeDto getIncomeDto(){
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setId(id);
        incomeDto.setTitle(title);
        incomeDto.setAmount(amount);
        incomeDto.setCategory(category);
        incomeDto.setDate(date);
        incomeDto.setDescription(description);
        return incomeDto;
    }
}
