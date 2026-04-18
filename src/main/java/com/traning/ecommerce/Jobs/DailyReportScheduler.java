package com.traning.ecommerce.Jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
//Day 28: Schedulers & Async Operations
@Component
public class DailyReportScheduler {

    private static final Logger logger = LoggerFactory.getLogger(DailyReportScheduler.class);

    // fixedRate = 10000 runs this exactly every 10 seconds.
    // In the real world, we use Cron expressions like: @Scheduled(cron = "0 0 0 * * ?") for Midnight every day.
    @Scheduled(fixedRate = 10000)
    public void generateDailyReport() {
        logger.info("[SCHEDULER] Generating automated sales report at {}", new Date());
        // Here you would normally query the database and send an email!
    }
}