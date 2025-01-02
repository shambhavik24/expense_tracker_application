package com.shambhviii.expense_tracker_application.controller;

import com.shambhviii.expense_tracker_application.dto.GraphDto;
import com.shambhviii.expense_tracker_application.service.stats.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stats")
@CrossOrigin("*")
public class StatsController {

    @Autowired
    private final StatsService statsService;

    @GetMapping("/chart")
    public ResponseEntity<GraphDto> getChartDetails(){
        return ResponseEntity.ok(statsService.getChartData());
    }

    @GetMapping
    public ResponseEntity<?> getStats(){
        return ResponseEntity.ok(statsService.getStats());
    }

}
