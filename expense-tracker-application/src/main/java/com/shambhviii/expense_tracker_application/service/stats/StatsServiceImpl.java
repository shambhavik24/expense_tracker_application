package com.shambhviii.expense_tracker_application.service.stats;

import com.shambhviii.expense_tracker_application.dto.GraphDto;
import com.shambhviii.expense_tracker_application.dto.StatsDto;
import com.shambhviii.expense_tracker_application.entity.Expense;
import com.shambhviii.expense_tracker_application.entity.Income;
import com.shambhviii.expense_tracker_application.repository.ExpenseRepository;
import com.shambhviii.expense_tracker_application.repository.IncomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService{

    @Autowired
    private final IncomeRepository incomeRepository;

    @Autowired
    private final ExpenseRepository expenseRepository;


    public GraphDto getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

       GraphDto graphDto = new GraphDto();
       graphDto.setExpenseList(expenseRepository.findByDateBetween(startDate,endDate));
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Expenses: " + expenseRepository.findByDateBetween(startDate, endDate));


        graphDto.setIncomeList(incomeRepository.findByDateBetween(startDate,endDate));
        System.out.println("Start Date of Income: " + startDate);
        System.out.println("End Date of Income: " + endDate);
        System.out.println("Income: " + incomeRepository.findByDateBetween(startDate, endDate));

       return graphDto;

    }
    public StatsDto getStats(){
        Double totalIncome = incomeRepository.sumAllAmounts();
        Double totalExpense = expenseRepository.sumAllAmounts();

        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();

        StatsDto statsDto = new StatsDto();
        statsDto.setExpense(totalExpense);
        statsDto.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDto::setLatestIncome);

        optionalExpense.ifPresent(statsDto::setLatestExpense);

        statsDto.setBalance(totalIncome-totalExpense);

        List<Income> incomeList = incomeRepository.findAll();
        List<Expense> expenseList = expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble(): null);
        statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble(): null);

        statsDto.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble(): null);
        statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble(): null);



        return statsDto;


    }




}
