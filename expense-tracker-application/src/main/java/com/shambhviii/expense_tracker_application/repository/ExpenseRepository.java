package com.shambhviii.expense_tracker_application.repository;

import com.shambhviii.expense_tracker_application.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double sumAllAmounts();

    Optional<Expense> findFirstByOrderByDateDesc();

}
