package com.shambhviii.expense_tracker_application.controller;

import com.shambhviii.expense_tracker_application.dto.ExpenseDto;
import com.shambhviii.expense_tracker_application.dto.IncomeDto;
import com.shambhviii.expense_tracker_application.entity.Expense;
import com.shambhviii.expense_tracker_application.entity.Income;
import com.shambhviii.expense_tracker_application.service.Expense.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private final ExpenseService expenseService;

    @PostMapping
//    public ResponseEntity<?> postExpense(@RequestBody ExpenseDto dto){
//      Expense createExpense =  expenseService.postExpense(dto);
//      if(createExpense != null){
//          return ResponseEntity.status(HttpStatus.CREATED).body(createExpense);
//      }
//      else {
//          return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//      }
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDto expenseDto) {
        Expense createExpense = expenseService.postExpense(expenseDto);
        if (createExpense != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createExpense);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in saving expense");
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllExpense(){
        return ResponseEntity.ok(expenseService.getAllExpnse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id){
     try{
         return ResponseEntity.ok(expenseService.getExpenseById(id));
     }catch (EntityNotFoundException ex){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
     }
     catch (Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
     }
     }

     @PutMapping("/{id}")
     public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto dto){
        try{
            return ResponseEntity.ok(expenseService.updateExpense(id,dto));
        }
        catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
     }


     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        try{
            expenseService.deleteExpense(id);
            return ResponseEntity.ok(null);
        }
        catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
     }

}
