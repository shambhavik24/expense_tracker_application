package com.shambhviii.expense_tracker_application.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class ExpenseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private String category;
    private Integer amount;
}
