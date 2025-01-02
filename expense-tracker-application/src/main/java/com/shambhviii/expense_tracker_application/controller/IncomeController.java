package com.shambhviii.expense_tracker_application.controller;

import com.shambhviii.expense_tracker_application.dto.IncomeDto;
import com.shambhviii.expense_tracker_application.entity.Income;
import com.shambhviii.expense_tracker_application.service.income.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
@CrossOrigin("*")
@RequiredArgsConstructor
public class IncomeController {

    @Autowired
    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<?> postIncome(@RequestBody IncomeDto incomeDto) {
        Income createIncome = incomeService.postIncome(incomeDto);
        if (createIncome != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createIncome);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in saving income");
        }
    }



    @GetMapping("/all")
    public ResponseEntity<?> getAllIncome(){
        return ResponseEntity.ok(incomeService.getAllIncome());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody IncomeDto incomeDto){
        try{
            return ResponseEntity.ok(incomeService.updateIncome(id, incomeDto));
        }
        catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable Long id){
        try{
            return  ResponseEntity.ok(incomeService.getIncomeById(id));
        }
        catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id){
        try{
            incomeService.deleteIncome(id);
            return ResponseEntity.ok(null);
        }
        catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }













}
