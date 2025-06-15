package com.kata.spring.swagger.controller;

import com.kata.spring.swagger.service.KataService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "kata-service", description = "management APIs")
@RestController("/api")
public class KataController {
  @Autowired
  private KataService kataService;

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job importJob;

  @PostMapping("/upload")
  public ResponseEntity<String> uploadAndLaunch(@RequestParam("file") MultipartFile file) throws Exception {
    InputStreamResource resource = new InputStreamResource(file.getInputStream());

    String outputPath = "output_" + System.currentTimeMillis() + ".txt";

    JobParameters jobParameters = new JobParametersBuilder()
            .addString("outputFile", outputPath)
            .addLong("timestamp", System.currentTimeMillis())
            .addJobParameter("inputStream", resource, InputStreamResource.class)
            .toJobParameters();

    JobExecution jobExecution = jobLauncher.run(importJob, jobParameters);

    return ResponseEntity.ok("Job started. Output file: " + outputPath);
  }

  @GetMapping("/strings")
  public String parseNumbers(@RequestParam(required = false) int number) {
    return this.kataService.getCharactersFromString(number);
  }



}
