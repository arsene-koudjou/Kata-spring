package com.kata.spring.swagger.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importJob;

    // exécution toutes les 5 secondes
    @Scheduled(cron = "*/5 * * * * *")
    public void runJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addDate("timestamp", new Date())
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(importJob, params);
            System.out.println("le Job est lancé à : " + execution.getStartTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
