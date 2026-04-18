package com.traning.ecommerce.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
//Day 28: Schedulers & Async Operations
@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Async
    public void sendWelcomeEmailBackground(String userEmail) {
        logger.info("[ASYNC] Starting to send email to {} on thread: {}", userEmail, Thread.currentThread().getName());

        try {
            // Simulate a slow 5-second process (like connecting to an SMTP server)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("[ASYNC] Successfully sent email to {}!", userEmail);
    }
}