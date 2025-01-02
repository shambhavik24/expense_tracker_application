package com.shambhviii.expense_tracker_application.dto;

import com.shambhviii.expense_tracker_application.entity.Expense;
import com.shambhviii.expense_tracker_application.entity.Income;
import lombok.Data;

@Data
public class StatsDto {

    private Double income;
    private Double expense;
    private Income latestIncome;
    private Expense latestExpense;

    private Double balance;

    private Double minIncome;
    private Double maxIncome;

    private Double minExpense;
    private Double maxExpense;




}
