package com.shambhviii.expense_tracker_application.service.Expense;

import com.shambhviii.expense_tracker_application.dto.ExpenseDto;
import com.shambhviii.expense_tracker_application.entity.Expense;
import com.shambhviii.expense_tracker_application.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;

    public Expense postExpense(ExpenseDto expenseDto){
        return saveorUpdate(new Expense(), expenseDto);
    }

    private Expense saveorUpdate(Expense expense, ExpenseDto expenseDto){
        expense.setTitle(expenseDto.getTitle());
        expense.setCategory(expenseDto.getCategory());
        expense.setDescription(expenseDto.getDescription());
        expense.setDate(expenseDto.getDate());
        expense.setAmount(expenseDto.getAmount());

        return expenseRepository.save(expense);
    }
    public Expense updateExpense(Long id, ExpenseDto expenseDto){
        Optional<Expense> optional = expenseRepository.findById(id);
        if(optional.isPresent()){
            return saveorUpdate(optional.get(), expenseDto);
        }else{
            throw  new EntityNotFoundException("Id is not present"+ id);
        }

    }
    public List<Expense> getAllExpnse(){
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }
    public Expense getExpenseById(Long id){
        Optional<Expense> optional = expenseRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        else{
            throw new EntityNotFoundException("Id is not found");
        }

    }
    public void deleteExpense(Long id){
        Optional<Expense> optional = expenseRepository.findById(id);
        if(optional.isPresent()){
            expenseRepository.deleteById(id);
        }
        else {
            throw  new EntityNotFoundException("Expense is not present with Id" + id);
        }
    }











}
