package com.shambhviii.expense_tracker_application.service.stats;

import com.shambhviii.expense_tracker_application.dto.GraphDto;
import com.shambhviii.expense_tracker_application.dto.StatsDto;

public interface StatsService {

    GraphDto getChartData();

    StatsDto getStats();
}
