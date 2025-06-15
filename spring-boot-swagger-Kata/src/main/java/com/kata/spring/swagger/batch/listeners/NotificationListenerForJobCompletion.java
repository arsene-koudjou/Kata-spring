package com.kata.spring.swagger.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListenerForJobCompletion implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationListenerForJobCompletion.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Le Job a démaré correctement...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("le Job s'est terminé avec succès !!!");
        } else {
            log.warn("le job s'est arreté au status: {}", jobExecution.getStatus());
        }
    }
}
