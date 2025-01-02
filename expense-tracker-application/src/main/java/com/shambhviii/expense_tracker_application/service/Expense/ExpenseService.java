package com.shambhviii.expense_tracker_application.service.Expense;

import com.shambhviii.expense_tracker_application.dto.ExpenseDto;
import com.shambhviii.expense_tracker_application.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDto expenseDto);

    List<Expense> getAllExpnse();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, ExpenseDto expenseDto);

    void deleteExpense(Long id);

}
