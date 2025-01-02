package com.shambhviii.expense_tracker_application.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class IncomeDto {
    private Long id;
    private String title;
    private Integer amount;
    private LocalDate date;
    private String category;
    private String description;
}
