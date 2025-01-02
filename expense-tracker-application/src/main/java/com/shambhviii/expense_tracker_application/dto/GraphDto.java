package com.shambhviii.expense_tracker_application.dto;

import com.shambhviii.expense_tracker_application.entity.Expense;
import com.shambhviii.expense_tracker_application.entity.Income;
import lombok.*;

import java.util.List;


@Data
public class GraphDto {

    private List<Expense> expenseList;

    private List<Income> incomeList;


}
