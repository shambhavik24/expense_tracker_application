package com.shambhviii.expense_tracker_application.service.income;

import com.shambhviii.expense_tracker_application.dto.IncomeDto;
import com.shambhviii.expense_tracker_application.entity.Income;

import java.util.List;

public interface IncomeService {

    Income postIncome(IncomeDto incomeDto);

    List<IncomeDto> getAllIncome();

    Income updateIncome(Long id, IncomeDto incomeDto);

    IncomeDto getIncomeById(Long id);

    public void deleteIncome(Long id);
}
